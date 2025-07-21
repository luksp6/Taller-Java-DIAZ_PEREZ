package edu.isistan.spellchecker.corrector.impl;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
public class DictionaryTrie
{

	private Trie palabras;

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
	public DictionaryTrie(TokenScanner ts) throws IOException, IllegalArgumentException {
		if (ts == null)
			throw new IllegalArgumentException();
		this.palabras = new Trie();
		while (ts.hasNext())
		{
			String palabra = Dictionary.normalizar(ts.next());
			if (TokenScanner.isWord(palabra))
				this.palabras.insert(palabra);
		}
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
		return this.palabras.cantidadElementos;
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
		return word != null && this.palabras.find(Dictionary.normalizar(word));
	}

	public Set<String> getWords()
	{
		return this.palabras.getWords();
	}

	//Nodo
	private class TrieNode 
	{
		//Los atributos se dejaron como publicos para mejorar legibilidad, ya que la clase es privada
		//y los accesos estan controlados dentro del diccionario
		Map<Character, TrieNode> children;
		boolean isWord;

		public TrieNode()
		{
			this.children = new HashMap<>();
		}

	}
	
	//Arbol
	private class Trie
	{
		private TrieNode root;
		int cantidadElementos;

		public Trie()
		{
			this.root = new TrieNode();
			this.cantidadElementos = 0;
		}

		public Set<String> getWords()
		{
			Set<String> palabras = new HashSet<>();
			getWordsHelper(root, "", palabras);
			return palabras;
		}

		private void getWordsHelper(TrieNode node, String currentWord, Set<String> palabras)
		{
			if (node.isWord)
				palabras.add(currentWord);

			for (Entry<Character, TrieNode> entry : node.children.entrySet())
				getWordsHelper(entry.getValue(), currentWord + entry.getKey(), palabras);
		}

		public void insert(String word)
		{
			TrieNode current = root;
			for (char l: word.toCharArray())
			{
				if (!current.children.containsKey(l))
				{
					current.children.put(l, new TrieNode());
					current = current.children.get(l);
				}
				else
					current = current.children.get(l);
			}
			if (!current.isWord)
			{
				current.isWord = true;
				this.cantidadElementos++;
			}
		}

		public boolean find(String word)
		{
			TrieNode current = root;
			for (int i = 0; i < word.length(); i++)
			{
				char ch = word.charAt(i);
				TrieNode node = current.children.get(ch);
				if (node == null) {
					return false;
				}
				current = node;
			}
			return current.isWord;
		}
	}
}