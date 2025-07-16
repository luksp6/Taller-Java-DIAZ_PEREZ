package edu.isistan.spellchecker.corrector.impl;

import java.util.HashSet;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Corrector;
import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.tokenizer.TokenScanner;
/**
 * Este corrector sugiere correciones cuando dos letras adyacentes han sido cambiadas.
 * <p>
 * Un error com�n es cambiar las letras de orden, e.g.
 * "with" -> "wiht". Este corrector intenta dectectar palabras con exactamente un swap.
 * <p>
 * Por ejemplo, si la palabra mal escrita es "haet", se debe sugerir
 * tanto "heat" como "hate".
 * <p>
 * Solo cambio de letras contiguas se considera como swap.
 */
public class SwapCorrector extends Corrector {
	private LSH swap;
	private final Dictionary dict;
	/**
	 * Construcye el SwapCorrector usando un Dictionary.
	 *
	 * @param dict 
	 * @throws IllegalArgumentException si el diccionario provisto es null
	 */
	public SwapCorrector(Dictionary dict) {
		if (dict == null)
			throw new IllegalArgumentException();
		this.swap=new LSH(dict,"SWAP");
		this.dict = dict;
	}

	/**
	 * 
	 * Este corrector sugiere correciones cuando dos letras adyacentes han sido cambiadas.
	 * <p>
	 * Un error com�n es cambiar las letras de orden, e.g.
	 * "with" -> "wiht". Este corrector intenta dectectar palabras con exactamente un swap.
	 * <p>
	 * Por ejemplo, si la palabra mal escrita es "haet", se debe sugerir
	 * tanto "heat" como "hate".
	 * <p>
	 * Solo cambio de letras contiguas se considera como swap.
	 * <p>
	 * Ver superclase.
	 *
	 * @param wrong 
	 * @return retorna un conjunto (potencialmente vac�o) de sugerencias.
	 * @throws IllegalArgumentException si la entrada no es una palabra v�lida 
	 */
	public Set<String> getCorrections(String wrong) {
		if (!TokenScanner.isWord(wrong))
			throw new IllegalArgumentException("Palabra invalida: " + wrong);
		Set<String> posiblesSwaps = new HashSet<String>();
		posiblesSwaps=swap.getPosiblesSwaps(wrong);
		return posiblesSwaps;
	}
}
