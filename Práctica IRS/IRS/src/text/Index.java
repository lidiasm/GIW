/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

/**
 *
 * @author Lidia Sánchez Mérida
 */
public class Index {
    /**
     * Path to the index.
     */
    private static String indexPath = "";
    /**
     * Lucene Analyzer to create and search in the index.
     */
    private static Analyzer indexAnalyzer;
    
    /**
     * Constructor.
     * @param indexPath
     */
    public Index(String indexPath) {
        this.indexPath = indexPath;
        indexAnalyzer = new StandardAnalyzer();
    }
    
    /**
     * Creates the index from the preprocessed doc collection and save the index
     * files into a folder.
     * @param docs preprocessed doc collection.
     * @param titles titles of the docs.
     * @throws IOException 
     */
    public void createIndex(ArrayList<String> docs, ArrayList<String> titles) throws IOException {
        if (!"".equals(indexPath)) {
            // Clean the directory
            Arrays.stream(new File(indexPath).listFiles()).forEach(File::delete);
            for (int i=0; i<docs.size(); i++) {
                // Set the directory for the index files
                Directory directory = FSDirectory.open(Paths.get(indexPath));
                IndexWriterConfig config = new IndexWriterConfig(indexAnalyzer);
                IndexWriter iwriter = new IndexWriter(directory, config);
                Document d = new Document();
                d.add(new Field("title", titles.get(i), TextField.TYPE_STORED));
                d.add(new Field("doc", docs.get(i), TextField.TYPE_STORED));
                iwriter.addDocument(d);
                iwriter.close();
            }
        }
    }
    /**
     * Searchs the user query in the index and gets the more important documents
     * related to the query. It can do the search based in the title or in the
     * text of the book.
     * @param userQuery preprocessed user query.
     * @param titles titles of the books.
     * @param titleSearch true if it's a title search, false if it's not.
     * @return indexes of the more important documents related to the query.
     * @throws IOException
     * @throws ParseException 
     */
    public ArrayList<Integer> searchInIndex(String userQuery, 
        ArrayList<String> titles, boolean titleSearch) throws IOException, ParseException {   
        // Search docs
        ArrayList<Integer> result = new ArrayList();
        if (!"".equals(indexPath)) {
            // Set the directory which includes the index files
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            // Open the folder
            IndexReader ireader = DirectoryReader.open(dir);
            // Do the search
            IndexSearcher isearcher = new IndexSearcher(ireader);
            // Parse a simple query that searches for "doc"
            String searchType = "doc";
            if (titleSearch) searchType = "title";
            QueryParser parser = new QueryParser(searchType, indexAnalyzer);
            Query query = parser.parse(userQuery);
            // 30 docs
            ScoreDoc[] hits = isearcher.search(query, 30).scoreDocs;
            // Store the 30 more important docs
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                System.out.println("File: " + hitDoc.get("title") + " - " + hits[i]);
                result.add(titles.indexOf(hitDoc.get("title")));
            }
            // Close reader and the folder
            ireader.close();
            dir.close();
        }
        return result;
    }
}
