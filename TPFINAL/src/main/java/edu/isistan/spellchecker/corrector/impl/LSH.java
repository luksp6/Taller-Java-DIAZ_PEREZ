package edu.isistan.spellchecker.corrector.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Dictionary;

public class LSH {
    private Map<String, Set<String>> corrector;  

    public LSH(Dictionary dic, String forma){
        this.corrector = new HashMap<>();
        if (forma.equals("SWAP")){
            cargarConSwaps(dic);
        }else{
            cargarConLevenshtein(dic);
        }
    }

    public void cargarConSwaps (Dictionary dic){
        Set<String> dicAux = dic.getPalabras();
        for (String word : dicAux) { 
            for (int i = 0; i < word.length() - 1; i++) {
            char[] caracteres = word.toCharArray();
            // swap entre i e i+1
            char actual = caracteres[i];
            caracteres[i] = caracteres[i + 1];
            caracteres[i + 1] = actual;
            String palabraSwapeada = new String(caracteres).toLowerCase();
            // Insertar 
            if (corrector.containsKey(palabraSwapeada)) { //Si la palabra swapeada esta cargada, solamente agrego la nueva palabra, sino creo el nuevo indice
                corrector.get(palabraSwapeada).add(word);
            }else{
                Set<String> palabraOriginal = new HashSet<String>(); //Creo el set para agregar la palabra bien escrita
                palabraOriginal.add(word);  
                corrector.put(palabraSwapeada, palabraOriginal);
            }            
            }
        }
    }

    public void cargarConLevenshtein (Dictionary dic){
        
    }

    public Set<String> getPosiblesSwaps(String word){
        Set<String> posiblesSwaps = new HashSet<String>();
        posiblesSwaps.addAll(corrector.get(word));
        return posiblesSwaps;
    }

}
