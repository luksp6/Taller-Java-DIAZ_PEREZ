package edu.isistan.spellchecker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.*;

import edu.isistan.spellchecker.corrector.Corrector;
import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.corrector.impl.FileCorrector;
import edu.isistan.spellchecker.corrector.impl.FileCorrector.FormatException;
import edu.isistan.spellchecker.corrector.impl.FileCorrectorTest;
import edu.isistan.spellchecker.corrector.impl.SwapCorrector;
import edu.isistan.spellchecker.corrector.impl.SwapCorrectorTest;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

/** Cree sus propios tests. */
public class MyTests
{
    //TokenScanner

    private Set<String> makeSet(String[] strings) {
		Set<String> mySet = new TreeSet<String>();
		for (String s : strings) {
			mySet.add(s);
		}
		return mySet;
	}

    @Test public void testEntradaVacia()  throws IOException
    {
        Reader in = new StringReader("");
        TokenScanner d = new TokenScanner(in);        
        assertFalse("has next", d.hasNext());
        try {
            d.next();
            fail("Expected NoSuchElementException");
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e);
        }
        finally {
            in.close();
        }
    }

    @Test public void testTokenPalabra() throws IOException
    {
        Reader in = new StringReader("palabra");
        TokenScanner d = new TokenScanner(in);        
        assertTrue("has next", d.hasNext());
        assertEquals("palabra",d.next());
        assertFalse("final", d.hasNext());
    }

    @Test public void testTokenNoPalabra() throws IOException
    {
        Reader in = new StringReader("$");
        TokenScanner d = new TokenScanner(in);        
        assertTrue("has next", d.hasNext());
        assertEquals("$",d.next());
        assertFalse("final", d.hasNext());
    }

    @Test public void testTokenNoPalabraPalabra() throws IOException
    {
        Reader in = new StringReader("$palabra");
        TokenScanner d = new TokenScanner(in);        
        assertTrue("has next", d.hasNext());
        assertEquals("$",d.next());
        assertEquals("palabra",d.next());
        assertFalse("final", d.hasNext());
    }

    @Test public void testTokenPalabraNoPalabra() throws IOException
    {
        Reader in = new StringReader("palabra$");
        TokenScanner d = new TokenScanner(in);
        assertTrue("has next", d.hasNext());
        assertEquals("palabra",d.next());
        assertEquals("$",d.next());
        assertFalse("final", d.hasNext());
    }

    //Dictionary

    @Test public void testPalabraPresente() throws IOException
    {
        Dictionary d = new Dictionary(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
        assertTrue("'day' -> deberia ser verdadero ('day' esta en el archivo)", d.isWord("day"));
    }

    @Test public void testPalabraNoPresente() throws IOException
    {
        Dictionary d = new Dictionary(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
        assertFalse("'palabra' -> deberia ser falso ('palabra' no esta en el archivo)", d.isWord("palabra"));
    }

    @Test public void testNumeroPalabras() throws IOException
    {
        Dictionary d = new Dictionary(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
        assertEquals(32, d.getNumWords());
    }

    @Test public void testStringVacio() throws IOException
    {
        Dictionary d = new Dictionary(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
        assertFalse("'' -> deberia ser falso ('' no es una palabra)", d.isWord(""));
    }

    @Test public void testPalabraPresenteCase() throws IOException
    {
        Dictionary d = new Dictionary(new TokenScanner(new FileReader("src\\test\\resources\\smallDictionary.txt")));
        assertTrue("'day' -> deberia ser verdadero ('day' esta en el archivo)", d.isWord("day"));
        assertTrue("'DaY' -> deberia ser verdadero ('DaY' esta en el archivo)", d.isWord("DaY"));
        assertTrue("'dAy' -> deberia ser verdadero ('dAy' esta en el archivo)", d.isWord("dAy"));
    }

    //FileCorrector

    @Test public void testEspaciosExtra() throws IOException, FormatException {
      Corrector c = FileCorrector.make("src\\test\\resources\\fileCorrectorEspaciosDict.txt");
    }

    @Test public void testPalabraSinCorreccion() throws IOException, FormatException  {
        Corrector c = FileCorrector.make("src\\test\\resources\\fileCorrectorEspaciosDict.txt");
        assertEquals("perro -> ", makeSet(new String[]{}), c.getCorrections("perro"));    
    }

    @Test public void testPalabraMultiplesCorrecciones() throws IOException, FormatException  {
        Corrector c = FileCorrector.make("src\\test\\resources\\fileCorrectorEspaciosDict.txt");
        assertEquals("rojp -> roja , rojo", makeSet(new String[]{"rojo", "roja"}), c.getCorrections("rojp"));    
    }

    @Test public void testPalabraMultiplesCapitalizaciones() throws IOException, FormatException  {
        Corrector c = FileCorrector.make("src\\test\\resources\\fileCorrectorEspaciosDict.txt");
        assertEquals("amarikko -> amarillo", makeSet(new String[]{"amarillo"}), c.getCorrections("amarikko"));
        assertEquals("AMARIKKO -> amarillo", makeSet(new String[]{"Amarillo"}), c.getCorrections("AMARIKKO"));  
        assertEquals("aMARIkko -> amarillo", makeSet(new String[]{"amarillo"}), c.getCorrections("aMARIkko"));  
        assertEquals("aMaRiKko -> amarillo", makeSet(new String[]{"amarillo"}), c.getCorrections("aMaRiKko"));  
        assertEquals("AmarikkO -> amarillo", makeSet(new String[]{"Amarillo"}), c.getCorrections("AmarikkO"));  
    }

    //SwapCorrector

    @Test public void testDiccionarioNull()
    {
        try {
            SwapCorrector swap = new SwapCorrector(null);            
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    @Test public void testPalabraPresenteSwap() throws IOException {
		Reader reader = new FileReader("src\\test\\resources\\swapDictionary.txt");
		try {
			Dictionary d = new Dictionary(new TokenScanner(reader));
			SwapCorrector swap = new SwapCorrector(d);
			assertEquals("rooj -> {rojo}", makeSet(new String[]{"rojo"}), swap.getCorrections("rooj"));
		} finally {
			reader.close();
		}
	}

    @Test public void testPalabraPresenteSwapCapitalizada() throws IOException {
		Reader reader = new FileReader("src\\test\\resources\\swapDictionary.txt");
		try {
			Dictionary d = new Dictionary(new TokenScanner(reader));
			SwapCorrector swap = new SwapCorrector(d);
			assertEquals("rooJ -> {rojo}", makeSet(new String[]{"rojo"}), swap.getCorrections("rooJ"));
			assertEquals("Rooj -> {Rojo}", makeSet(new String[]{"Rojo"}), swap.getCorrections("Rooj"));
			assertEquals("RoOj -> {Rojo}", makeSet(new String[]{"Rojo"}), swap.getCorrections("RoOj"));
			assertEquals("ROOJ -> {Rojo}", makeSet(new String[]{"Rojo"}), swap.getCorrections("ROOJ"));
		} finally {
			reader.close();
		}
	}


}