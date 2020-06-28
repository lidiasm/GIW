/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * Class which applies a technique called Stemming. It looks for the roots of the
 * words and deletes the rest of the letters.
 * 
 * @author Lidia Sánchez Mérida
 */
public class Stemming {
    
    /**
     * Constructor.
     */
    public Stemming() {}
    
    /**
     * Gets the root of each word of a document.
     * @param doc document to apply stemming.
     * @return stemmed document.
     * @throws Exception 
     */
    public String stemming(String doc) throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        TokenStream result = analyzer.tokenStream(null, doc);
        result = new PorterStemFilter(result);
        CharTermAttribute resultAttr = result.addAttribute(CharTermAttribute.class);
        result.reset();

        List<String> tokens = new ArrayList<>();
        while (result.incrementToken()) {
            tokens.add(resultAttr.toString());
        }
        //System.out.println(tokens.toString());
        return tokens.stream().collect(Collectors.joining(" "));
    }
    
    /**
     * Stemming process applies to a doc collection,
     * @param docs doc collection.
     * @return stemmed doc collection.
     * @throws Exception 
     */
    public ArrayList<String> docsStemming(ArrayList<String> docs) throws Exception {
        ArrayList<String> stemmedDocs = new ArrayList();
        for (String d: docs) {
            stemmedDocs.add(this.stemming(d));
        }
        return stemmedDocs;
    }
}
