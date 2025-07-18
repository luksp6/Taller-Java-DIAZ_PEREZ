package edu.isistan.spellchecker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;

import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.corrector.impl.DictionarySet;
import edu.isistan.spellchecker.corrector.impl.DictionaryTrie;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

public class BenchmarksRunner
{
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void newDictionarySet() throws IllegalArgumentException, FileNotFoundException, IOException {
        DictionarySet d = new DictionarySet(new TokenScanner(new FileReader("dictionary.txt")));
    }

    @Benchmark
    public void newDictionaryTrie() throws IllegalArgumentException, FileNotFoundException, IOException {
        DictionaryTrie d = new DictionaryTrie(new TokenScanner(new FileReader("dictionary.txt")));
    }
}
