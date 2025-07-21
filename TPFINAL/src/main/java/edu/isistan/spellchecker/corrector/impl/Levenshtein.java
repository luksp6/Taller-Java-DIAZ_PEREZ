package edu.isistan.spellchecker.corrector.impl;
import java.util.HashSet;
import java.util.Map;
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
	private final Map<Integer, Set<String>> wordsByLength;

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
		this.wordsByLength = dict.getByLength();
	}

	/**
	 * @param s palabra
	 * @return todas las palabras a erase distance uno
	 */
	Set<String> getDeletions(String wrong)
	{
		Set<String> output = new HashSet<>();
		String normalizada = normalizar(wrong);
		Set<String> candidates = this.wordsByLength.get(normalizada.length() - 1);
		if (candidates != null)
			for (String candidate : candidates)
			{
				int distance = 0, wrongIndex = 0, candidateIndex = 0;
				while (wrongIndex < normalizada.length() && candidateIndex < candidate.length())
				{
					if (normalizada.charAt(wrongIndex) != candidate.charAt(candidateIndex))
						distance++;
					else
						candidateIndex++;
					wrongIndex++;
				}
				if (distance <= 1)
					output.add(candidate);
			}
		return matchCase(wrong, output);
	}

	/**
	 * @param s palabra
	 * @return todas las palabras a substitution distance uno
	 */
	Set<String> getSubstitutions(String wrong)
	{
		Set<String> output = new HashSet<>();
		String normalizada = normalizar(wrong);
		Set<String> candidates = this.wordsByLength.get(normalizada.length());
		if (candidates != null)
			for (String candidate : candidates)
			{
				int distance = 0, wrongIndex = 0;
				while (wrongIndex < normalizada.length())
				{
					if (normalizada.charAt(wrongIndex) != candidate.charAt(wrongIndex))
						distance++;
					wrongIndex++;
				}
				if (distance == 1)
					output.add(candidate);
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
		Set<String> candidates = this.wordsByLength.get(normalizada.length() + 1);
		if (candidates != null)
			for (String candidate : candidates)
			{
				int distance = 0, wrongIndex = 0, candidateIndex = 0;
				while (wrongIndex < normalizada.length() && candidateIndex < candidate.length())
				{
					if (normalizada.charAt(wrongIndex) != candidate.charAt(candidateIndex))
						distance++;
					else
						wrongIndex++;
					candidateIndex++;
				}
				if (distance <= 1)
					output.add(candidate);
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
