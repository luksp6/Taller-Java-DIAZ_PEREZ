package edu.isistan.spellchecker;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import edu.isistan.spellchecker.corrector.impl.DictionarySet;
import edu.isistan.spellchecker.corrector.impl.DictionaryTrie;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BenchmarksRunner {

    private DictionarySet dictSetGrande;
    private DictionaryTrie dictTrieGrande;

    private DictionarySet dictSetChico;
    private DictionaryTrie dictTrieChico;

    private String[] palabrasPresentes;
    private String[] palabrasAusentes;


    @Setup
    public void setup() throws IOException {
        dictSetGrande = new DictionarySet(new TokenScanner(new FileReader("dictionary.txt")));
        dictTrieGrande = new DictionaryTrie(new TokenScanner(new FileReader("dictionary.txt")));

        
        dictSetChico = new DictionarySet(new TokenScanner(new FileReader("smallDictionary.txt")));
        dictTrieChico = new DictionaryTrie(new TokenScanner(new FileReader("smallDictionary.txt")));

        palabrasPresentes = new String[]{ "dog", "cat", "mouse", "apple", "banana", "keyboard"};
        palabrasAusentes = new String[]{ "messi", "otorrinolaringologo", "supercalifragilisticoespialidoso", "lewandowski", "kvaratskhelia", "zonzorroneria"};
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkSetNewGrande(Blackhole bh) throws IllegalArgumentException, IOException
    {
        bh.consume(new DictionarySet(new TokenScanner(new FileReader("dictionary.txt"))));
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkTrieNewGrande(Blackhole bh) throws IllegalArgumentException, IOException
    {
        bh.consume(new DictionaryTrie(new TokenScanner(new FileReader("dictionary.txt"))));
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkSetNewChico(Blackhole bh) throws IllegalArgumentException, IOException
    {
        bh.consume(new DictionarySet(new TokenScanner(new FileReader("smallDictionary.txt"))));
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkTrieNewChico(Blackhole bh) throws IllegalArgumentException, IOException
    {
        bh.consume(new DictionaryTrie(new TokenScanner(new FileReader("smallDictionary.txt"))));
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkSetIsWordGrandePresente() {
        return testIsWord(dictSetGrande, "SET", palabrasPresentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkTrieIsWordGrandePresente() {
        return testIsWord(dictTrieGrande, "TRIE", palabrasPresentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkSetIsWordGrandeAusente() {
        return testIsWord(dictSetGrande, "SET", palabrasAusentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkTrieIsWordGrandeAusente() {
        return testIsWord(dictTrieGrande, "TRIE", palabrasAusentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkSetIsWordChicoPresente() {
        return testIsWord(dictSetChico, "SET", palabrasPresentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkTrieIsWordChicoPresente() {
        return testIsWord(dictTrieChico, "TRIE", palabrasPresentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkSetIsWordChicoAusente() {
        return testIsWord(dictSetChico, "SET", palabrasAusentes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkTrieIsWordChicoAusente() {
        return testIsWord(dictTrieChico, "TRIE", palabrasAusentes);
    }

    static private int testIsWord(Object dict, String type, String[] words) {
        int found = 0;
        for (int i = 0; i < 1000; i++)
            for (String word : words)
                if (type.equals("SET"))
                {
                    if (((DictionarySet) dict).isWord(word))
                        found++;
                }
                else if (type.equals("TRIE"))
                {
                    if (((DictionaryTrie) dict).isWord(word))
                        found++;
                }
        return found;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkGetWordsSetChico(Blackhole bh)
    {
        for (int i = 0; i < 1000; i++)
        {
            Set<String> words = dictSetChico.getWords();
            bh.consume(words);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkGetWordsTrieChico(Blackhole bh)
    {
        for (int i = 0; i < 1000; i++)
        {
            Set<String> words = dictTrieChico.getWords();
            bh.consume(words);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkGetWordsSetGrande(Blackhole bh)
    {
        Set<String> words = dictSetGrande.getWords();
        bh.consume(words);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void benchmarkGetWordsTrieGrande(Blackhole bh)
    {
        Set<String> words = dictTrieGrande.getWords();
        bh.consume(words);
    }

    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
