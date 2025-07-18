package edu.isistan.spellchecker.corrector;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;




import org.junit.Test;

import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

public class DictionaryTrieTest {

  
  @Test(timeout=500) public void testDictionaryContainsSimple() throws IOException {
    DictionaryTrie d = new DictionaryTrie(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
    assertTrue("'apple' -> should be true ('apple' in file)", d.isWord("apple"));
    assertTrue("'Banana' -> should be true ('banana' in file)", d.isWord("Banana"));
    assertFalse("'pineapple' -> should be false", d.isWord("pineapple"));
  }

  
  @Test(timeout=500) public void testDictionaryContainsApostrophe() throws IOException {
    DictionaryTrie d = new DictionaryTrie(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
    assertTrue("'it's' -> should be true ('it's' in file)", d.isWord("it's"));
  }

  
  @Test(timeout=500) public void testConstructorInvalidTokenScanner() throws IOException {
    try {
      TokenScanner ts = null;
      new DictionaryTrie(ts);
      fail("Expected IllegalArgumentException - null TokenScanner");
    } catch (IllegalArgumentException e){
      //Do nothing - it's supposed to throw this
    }
  }

	 

  // Do NOT add your own tests here. Put your tests in MyTest.java
}
