package edu.isistan.spellchecker.corrector;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.corrector.impl.DictionarySet;
import edu.isistan.spellchecker.corrector.impl.DictionaryTrie;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

/**
 * El diccionario maneja todas las palabras conocidas.
 * El diccionario es case insensitive 
 * 
 * Una palabra "v�lida" es una secuencia de letras (determinado por Character.isLetter) 
 * o apostrofes.
 */
public class Dictionary  {

	private DictionarySet implementacion;
	//private DictionaryTrie implementacion;
	
	/**
	 * Construye un diccionario usando un TokenScanner
	 * <p>
	 * Una palabra v�lida es una secuencia de letras (ver Character.isLetter) o apostrofes.
	 * Toda palabra no v�lida se debe ignorar
	 *
	 * <p>
	 *
	 * @param ts 
	 * @throws IOException Error leyendo el archivo
	 * @throws IllegalArgumentException el TokenScanner es null
	 */
	public Dictionary(TokenScanner ts) throws IOException, IllegalArgumentException {
		if (ts == null)
			throw new IllegalArgumentException();
		this.implementacion = new DictionarySet(ts);
		//this.implementacion = new DictionaryTrie(ts);
	}

	/**
	 * Construye un diccionario usando un archivo.
	 *
	 *
	 * @param filename 
	 * @throws FileNotFoundException si el archivo no existe
	 * @throws IOException Error leyendo el archivo
	 */
	public static Dictionary make(String filename) throws IOException {
		Reader r = new FileReader(filename);
		Dictionary d = new Dictionary(new TokenScanner(r));
		r.close();
		return d;
	}

	/**
	 * Retorna el n�mero de palabras correctas en el diccionario.
	 * Recuerde que como es case insensitive si Dogs y doGs est�n en el 
	 * diccionario, cuentan como una sola palabra.
	 * 
	 * @return n�mero de palabras �nicas
	 */
	public int getNumWords()
	{
		return this.implementacion.getNumWords();
	}

	/**
	 * Testea si una palabra es parte del diccionario. Si la palabra no est� en
	 * el diccionario debe retornar false. null debe retornar falso.
	 * Si en el diccionario est� la palabra Dog y se pregunta por la palabra dog
	 * debe retornar true, ya que es case insensitive.
	 *
	 *Llamar a este m�todo no debe reabrir el archivo de palabras.
	 *
	 * @param word verifica si la palabra est� en el diccionario. 
	 * Asuma que todos los espacios en blanco antes y despues de la palabra fueron removidos.
	 * @return si la palabra est� en el diccionario.
	 */
	public boolean isWord(String word) {
		return this.implementacion.isWord(word);
	}

	public Map<Integer, Set<String>> getByLength()
	{		
		Map<Integer, Set<String>> output = new HashMap<>();
		Set<String> dictWords = this.implementacion.getWords();
		for (String word : dictWords)
			if (output.keySet().contains(word.length()))
				output.get(word.length()).add(word);
			else
			{
				Set<String> newEntry = new HashSet<>();
				newEntry.add(word);
				output.put(word.length(), newEntry);
			}
		return output;
	}

	public static String normalizar(String word)
	{
		return word.trim().toUpperCase();
	}
}