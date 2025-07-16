package edu.isistan.spellchecker.corrector.impl;
import java.util.HashSet;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Corrector;
import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

/**
 *
 * Un corrector inteligente que utiliza "edit distance" para generar correcciones.
 * 
 * La distancia de Levenshtein es el n�mero minimo de ediciones que se deber
 * realizar a un string para igualarlo a otro. Por edici�n se entiende:
 * <ul>
 * <li> insertar una letra
 * <li> borrar una letra
 * <li> cambiar una letra
 * </ul>
 *
 * Una "letra" es un caracter a-z (no contar los apostrofes).
 * Intercambiar letras (thsi -> this) <it>no</it> cuenta como una edici�n.
 * <p>
 * Este corrector sugiere palabras que esten a edit distance uno.
 */
public class Levenshtein extends Corrector {


	private final Dictionary dict;

	/**
	 * Construye un Levenshtein Corrector usando un Dictionary.
	 * Debe arrojar <code>IllegalArgumentException</code> si el diccionario es null.
	 *
	 * @param dict
	 */
	public Levenshtein(Dictionary dict) throws IllegalArgumentException {
		if (dict == null)
			throw new IllegalArgumentException();
		this.dict = dict;
	}

	/**
	 * @param s palabra
	 * @return todas las palabras a erase distance uno
	 */
	Set<String> getDeletions(String wrong)
	{
		Set<String> output = new HashSet<>();
		String normalizada = normalizar(wrong);
		for (int i = 0; i < normalizada.length(); i++)
		{
			String suggestion = normalizada.substring(0, i) + normalizada.substring(i + 1);
			if (this.dict.isWord(suggestion))
				output.add(suggestion);
		}
		return matchCase(wrong, output);
	}

	/**
	 * @param s palabra
	 * @return todas las palabras a substitution distance uno
	 */
	public Set<String> getSubstitutions(String wrong)
	{
		Set<String> output = new HashSet<>();
		String normalizada = normalizar(wrong);
		for (int i = 0; i < normalizada.length(); i++)
			for (char c = 'A'; c <= 'Z'; c++)
			{
				String suggestion = normalizada.substring(0, i) + c + normalizada.substring(i + 1);
				if (this.dict.isWord(suggestion) && !suggestion.equalsIgnoreCase(wrong))
					output.add(suggestion);
			}
		return matchCase(wrong, output);
	}

	/**
	 * @param s palabra
	 * @return todas las palabras a insert distance uno
	 */
	public Set<String> getInsertions(String wrong)
	{
		Set<String> output = new HashSet<>();
		String normalizada = normalizar(wrong);
		for (int i = 0; i <= normalizada.length(); i++)
			for (char c = 'A'; c <= 'Z'; c++)
			{
				String suggestion = normalizada.substring(0, i) + c + normalizada.substring(i);
				if (this.dict.isWord(suggestion))
					output.add(suggestion);
			}
		return matchCase(wrong, output);
	}

	public Set<String> getCorrections(String wrong) {
		if (!TokenScanner.isWord(wrong))
			throw new IllegalArgumentException("Palabra invalida: " + wrong);
		Set<String> salida = new HashSet<String>();
		if (!this.dict.isWord(wrong))
		{
			salida.addAll(getSubstitutions(wrong));
			salida.addAll(getInsertions(wrong));
			salida.addAll(getDeletions(wrong));
		}
		return salida;
	}

}
