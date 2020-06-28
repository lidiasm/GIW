/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to read the documents which are contained in a specific folder.
 * 
 * @author Lidia Sánchez Mérida
 */
public class DocsReader {
    /**
     * Folder which contains the doc collection.
     */
    private String folder;
    /**
     * Texts of the books.
     */
    private ArrayList<String> docsTexts;
    /**
     * Titles of the books.
     */
    private ArrayList<String> docsTitles;
    
    /**
     * Constructor to set the folder which contains the doc collection.
     * @param folder path to the doc collection.
     */
    public DocsReader(String folder) {
        this.folder = folder;
    }
    /**
     * Gets the folder which contains the doc collection.
     * @return path to the doc collection.
     */
    public String getFolder() {
        return folder;
    }
    /**
     * Gets the texts of the docs.
     * @return books.
     */
    public ArrayList<String> getDocsTexts() {
        return docsTexts;
    }
    /**
     * Gets the titles of the docs.
     * @return titles of the books.
     */
    public ArrayList<String> getDocsTitles() {
        return docsTitles;
    }
    /**
     * Reads the doc collection from Project Gutenberg.
     * @throws IOException 
     */
    public void readGutenbergDocs() throws IOException {
        docsTexts = new ArrayList();
        docsTitles = new ArrayList();
        File f = new File(folder);
        String docs[] = f.list();
        for (String doc: docs) {
            String docText = new String(Files.readAllBytes(Paths.get(folder+doc))); 
            docsTexts.add(docText);
            // Get the title of the book
            File file = new File(folder+"/"+doc);
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Title:")) {
                    docsTitles.add(line.substring(line.indexOf(":")+1));
                    break;
                }
            }
        }
    }
}
