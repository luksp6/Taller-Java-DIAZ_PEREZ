package edu.isistan.spellchecker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import edu.isistan.spellchecker.corrector.impl.DictionarySet;
import edu.isistan.spellchecker.corrector.impl.DictionaryTrie;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 1, warmups = 1)
public class BenchmarksRunner {

    private DictionarySet dictSet;
    private DictionaryTrie dictTrie;

    @Setup
    public void setup() throws IOException {
        dictSet = new DictionarySet(new TokenScanner(new FileReader("dictionary.txt")));
        dictTrie = new DictionaryTrie(new TokenScanner(new FileReader("dictionary.txt")));
    }

    @Benchmark
    public int benchmarkSetLookup() {
        return testLookups(dictSet, "SET");
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public int benchmarkTrieLookup() {
        return testLookups(dictTrie, "TRIE");
    }

    static private int testLookups(Object dict, String type) {
        String[] words = { "dog", "cat", "mouse", "apple", "banana", "keyboard"};
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

    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
        /*
        Options opt = new OptionsBuilder()
                .include(BenchmarksRunner.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run(); */
    }
}
