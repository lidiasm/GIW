/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * Class which contains the methods to preprocess text applying techniques such
 * as deleting stop words, Stemming, among others.
 * 
 * @author Lidia Sánchez Mérida
 */
public class TextPreprocessing {
    
    /**
     * Stemming object.
     */
    private Stemming stemm;
    
    /**
     * Constructor which creates an object to apply stemming
     * to the doc collection.
     */
    public TextPreprocessing() {
        stemm = new Stemming();
    }
    
    /**
     * Removes punctuation marks of a doc.
     * @param doc doc to preprocess.
     * @return doc without punctuation marks.
     */
    public String deletePunctuation(String doc) {
        return doc.replaceAll("[\\p{Punct}\\p{IsPunctuation}]", "");
    }
    
    /**
     * Removes numbers of a doc.
     * @param doc doc to preprocess.
     * @return doc without numbers.
     */
    public String deleteDigits(String doc) {
        return doc.replaceAll("[0-9]", "");
    }
    
    /**
     * Removes english stop words of a doc and turns every letter to
     * small letters.
     * @param file path to the stop words file.
     * @param doc doc to preprocess.
     * @return preprocessed doc.
     * @throws IOException 
     */
    public String deleteStopWords(String file, String doc) throws IOException {
        // Read the stop words file
        List<String> stopWords = Files.readAllLines(Paths.get(file));
        // Stop words set
        final CharArraySet stopSet = new CharArraySet(stopWords, true);
        Analyzer analyzer = new StandardAnalyzer();
        // Get the words separately
        TokenStream result = analyzer.tokenStream(null, doc.toLowerCase());
        // Filter the stop words
        result = new StopFilter(result, stopSet);
        CharTermAttribute resultAttr = result.addAttribute(CharTermAttribute.class);
        result.reset();
        // Get the no stop words 
        List<String> tokens = new ArrayList<>();
        while (result.incrementToken()) {
            tokens.add(resultAttr.toString());
        }
        // Getting words together to form the text
        String lemtDoc = tokens.stream().collect(Collectors.joining(" "));
        // Removes words with only one letter
        String prepDoc = lemtDoc.replaceAll("\\b\\w{1,1}\\b\\s?", "");
        
        return prepDoc;
    }
        
    /**
     * Preprocesses doc collection removing punctuation marks, digits, stop words
     * and turn every letter to small letter.
     * @param stopWordsFile path to the stop words file.
     * @param docs doc collection to preprocess.
     * @return preprocessed doc collection.
     * @throws java.io.IOException
     */
    public ArrayList<String> docPreprocessing(String stopWordsFile, 
            ArrayList<String> docs) throws IOException, Exception {
        ArrayList<String> preprocessedDocs = new ArrayList();
        for (String doc: docs) {
            // Punctuation marks
            String preprocDoc = deletePunctuation(doc);
            // Digits
            preprocDoc = deleteDigits(preprocDoc);
            // Stop words
            preprocDoc = deleteStopWords(stopWordsFile, preprocDoc);
            // Stemming
            preprocDoc = stemm.stemming(preprocDoc);
            // Preprocessed doc
            preprocessedDocs.add(preprocDoc);
        }
        
        return preprocessedDocs;
    }
    
    /**
     * Preprocesses query removing punctuation marks, digits, stop words
     * and turn every letter to small letter.
     * @param stopWordsFile path to the stop words file.
     * @param query user query.
     * @return preprocessed user query.
     * @throws IOException
     * @throws Exception 
     */
    public String queryPreprocessing(String stopWordsFile, String query) 
        throws IOException, Exception {
        // Punctuation marks
        String preprocQuery = deletePunctuation(query);
        // Digits
        preprocQuery = deleteDigits(preprocQuery);
        // Stop words
        preprocQuery = deleteStopWords(stopWordsFile, preprocQuery);
        // Stemming
        preprocQuery = stemm.stemming(preprocQuery);
        
        return preprocQuery;
    }
}
