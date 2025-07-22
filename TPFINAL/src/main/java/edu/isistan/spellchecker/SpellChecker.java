package edu.isistan.spellchecker;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.isistan.spellchecker.corrector.Corrector;
import edu.isistan.spellchecker.corrector.Dictionary;
import edu.isistan.spellchecker.tokenizer.TokenScanner;

/**
 * El SpellChecker usa un Dictionary, un Corrector, and I/O para chequear
 * de forma interactiva un stream de texto. Despues escribe la salida corregida
 * en un stream de salida. Los streams pueden ser archivos, sockets, o cualquier
 * otro stream de Java.
 * <p>
 * Nota:
 * <ul>
 * <li> La implementaci�n provista provee m�todos utiles para implementar el SpellChecker.
 * <li> Toda la salida al usuario deben enviarse a System.out (salida estandar)
 * </ul>
 * <p>
 * El SpellChecker es usado por el SpellCkecherRunner. Ver:
 * @see SpellCheckerRunner
 */
public class SpellChecker {
	private Corrector corr;
	private Dictionary dict;

	/**
	 * Constructor del SpellChecker
	 * 
	 * @param c un Corrector
	 * @param d un Dictionary
	 */
	public SpellChecker(Corrector c, Dictionary d) {
		corr = c;
		dict = d;
	}

	/**
	 * Returna un entero desde el Scanner provisto. El entero estar� en el rango [min, max].
	 * Si no se ingresa un entero o este est� fuera de rango, repreguntar�.
	 *
	 * @param min
	 * @param max
	 * @param sc
	 */
	private int getNextInt(int min, int max, Scanner sc) {
		while (true) {
			try {
				int choice = Integer.parseInt(sc.next());
				if (choice >= min && choice <= max) {
					return choice;
				}
			} catch (NumberFormatException ex) {
			}
			System.out.println("Entrada invalida. Pruebe de nuevo!");
		}
	}

	/**
	 * Retorna el siguiente String del Scanner.
	 *
	 * @param sc
	 */
	private String getNextString(Scanner sc) {
		return sc.next();
	}



	/**
	 * checkDocument interactivamente chequea un archivo de texto..  
	 * Internamente, debe usar un TokenScanner para parsear el documento.  
	 * Tokens de tipo palabra que no se encuentran en el diccionario deben ser corregidos
	 * ; otros tokens deben insertarse tal cual en en documento de salida.
	 * <p>
	 *
	 * @param in stream donde se encuentra el documento de entrada.
	 * @param input entrada interactiva del usuario. Por ejemplo, entrada estandar System.in
	 * @param out stream donde se escribe el documento de salida.
	 * @throws IOException si se produce alg�n error leyendo el documento.
	 */
	public void checkDocument(Reader in, InputStream input, Writer out) throws IOException {
		Scanner sc = new Scanner(input);
		corregirDocumento(in, sc, out);
		System.out.println("Correccion finalizada.");
	}

	private void corregirDocumento(Reader in, Scanner sc, Writer out) throws IllegalArgumentException, IOException
	{
		TokenScanner ts = new TokenScanner(in);
		BufferedWriter output = new BufferedWriter(out);

		try
		{
			while (ts.hasNext())
			{
				String token = ts.next();
				if (TokenScanner.isWord(token) && !this.dict.isWord(token))
				{
					List<String> correcciones = new ArrayList<>(this.corr.getCorrections(token));
					System.out.println("Se detecto un error en la palabra \"" + token + "\". Puede tomar una de las siguientes opciones:");
					System.out.println("[0] Conservar");
					System.out.println("[1] Ingresar corrección");
					for (int i = 0; i < correcciones.size(); i++)
						System.out.println("[" + (i + 2) + "] " + correcciones.get(i));
					System.out.print("Seleccione el índice de la opción deseada: ");
					int entradaUsuario = getNextInt(0, correcciones.size() + 1, sc);
					switch (entradaUsuario)
					{
						case 0:								
							break;
						case 1:
						System.out.print("Ingrese su correccion: ");
							token = getNextString(sc);
							System.out.println("Corrección ingresada: " + token);
							break;					
						default:
							token = correcciones.get(entradaUsuario - 2);
							break;
					}
				}
				output.write(token);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			output.flush();
		}
	}
}
