package edu.isistan.spellchecker.corrector.impl;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

/**
 * El diccionario maneja todas las palabras conocidas.
 * El diccionario es case insensitive 
 * 
 * Una palabra "v�lida" es una secuencia de letras (determinado por Character.isLetter) 
 * o apostrofes.
 */
public class DictionarySet extends Dictionary {

	private Set<String> palabras;

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
	public DictionarySet(TokenScanner ts) throws IOException, IllegalArgumentException {
		if (ts == null)
			throw new IllegalArgumentException();
		this.palabras = new HashSet<>();
		while (ts.hasNext())
		{
			String palabra = normalizar(ts.next());
			if (TokenScanner.isWord(palabra))
				this.palabras.add(palabra);
		}
	}

	/**
	 * Construye un diccionario usando un archivo.
	 *
	 *
	 * @param filename 
	 * @throws FileNotFoundException si el archivo no existe
	 * @throws IOException Error leyendo el archivo
	 */
	public static DictionarySet make(String filename) throws IOException {
		Reader r = new FileReader(filename);
		DictionarySet d = new DictionarySet(new TokenScanner(r));
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
	@Override
	public int getNumWords()
	{
		int cont = 0;
		for (String palabra : this.palabras)
			if (TokenScanner.isWord(palabra))
				cont++;
		return cont;
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
	@Override
	public boolean isWord(String word) {
		return word != null && this.palabras.contains(normalizar(word));
	}
}