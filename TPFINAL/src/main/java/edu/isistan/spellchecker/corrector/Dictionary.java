package edu.isistan.spellchecker.corrector;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import edu.isistan.spellchecker.tokenizer.TokenScanner;

/**
 * El diccionario maneja todas las palabras conocidas.
 * El diccionario es case insensitive 
 * 
 * Una palabra "v�lida" es una secuencia de letras (determinado por Character.isLetter) 
 * o apostrofes.
 */
public abstract class Dictionary {

	
	/**
	 * Retorna el n�mero de palabras correctas en el diccionario.
	 * Recuerde que como es case insensitive si Dogs y doGs est�n en el 
	 * diccionario, cuentan como una sola palabra.
	 * 
	 * @return n�mero de palabras �nicas
	 */
	public abstract int getNumWords();

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
	public abstract boolean isWord(String word);

	protected String normalizar(String word)
	{
		return word.toUpperCase().replace("’", "'").replace("‘", "'");
	}
}