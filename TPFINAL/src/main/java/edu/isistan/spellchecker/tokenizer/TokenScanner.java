package edu.isistan.spellchecker.tokenizer;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Dado un archivo provee un m�todo para recorrerlo.
 */
public class TokenScanner implements Iterator<String> {

  private BufferedReader in;

  /**
   * Crea un TokenScanner.
   * <p>
   * Como un iterador, el TokenScanner solo debe leer lo justo y
   * necesario para implementar los m�todos next() y hasNext(). 
   * No se debe leer toda la entrada de una.
   * <p>
   *
   * @param in fuente de entrada
   * @throws IOException si hay alg�n error leyendo.
   * @throws IllegalArgumentException si el Reader provisto es null
   */
  public TokenScanner(java.io.Reader in) throws IOException, IllegalArgumentException {
    if (in == null)
      throw new IllegalArgumentException();
    this.in = new BufferedReader(in);
  }

  /**
   * Determina si un car�cer es una caracter v�lido para una palabra.
   * <p>
   * Un caracter v�lido es una letra (
   * Character.isLetter) o una apostrofe '\''.
   *
   * @param c 
   * @return true si es un caracter
   */
  public static boolean isWordCharacter(int c) {
    return Character.isLetter(c) || c == (int) '\'';
  }

   /**
   * Determina si un string es una palabra v�lida.
   * Null no es una palabra v�lida.
   * Un string que todos sus caracteres son v�lidos es una 
   * palabra. Por lo tanto, el string vac�o es una palabra v�lida.
   * @param s 
   * @return true si el string es una palabra.
   */
  public static boolean isWord(String s)
  {
    if (s == null)
      return false;
    for (int i = 0; i < s.length(); i++)
      if (!isWordCharacter(s.charAt(i)))
		    return false;
    return true;
  }

  /**
   * Determina si hay otro token en el reader.
   */
  public boolean hasNext(){
    try {
      return this.in.ready();
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Retorna el siguiente token.
   *
   * @throws NoSuchElementException cuando se alcanz� el final de stream
   */
  /*
  public String next() throws NoSuchElementException{
    if (hasNext())
    {
      String token = "";
      try
      {
        /*
        palabraSaltolinea -> "hola\n"
        espacioSaltolinea -> " \n"
        espacio -> " "
        saltolinea -> "\n"
        caracter -> "!"     
        while (true)        
        {
          int ch = this.in.read();
          this.in.mark(0);
          if (ch == (int) '\n' || ch == (int) '\r')
            token += (char) ch;
          if (!isWordCharacter(ch))
          {            
            if (!isWord(token))
              token += (char) ch;
              break;
          }
          else
          {
            if (isWord(token))
              token += (char) ch;
          }
          if (ch == (int) ' ')
            if (isWord(token))
              this.in.reset();
            else
              token += (char) ch;
        }
        return token;
      }
      catch (IOException e)
      {
        return null;
      }
    }
    else
      throw new NoSuchElementException();
  }*/

  public String next() throws NoSuchElementException{
    if (hasNext())
    {
      String token = "";
      try
      {
        int ch = this.in.read(); 
        while (isWordCharacter(ch) || ch == (int) '\n' || ch == (int) '\r')
        {
          this.in.mark(0);
          token += (char) ch;
          ch = this.in.read();
        }
        if(token.equals("")){          
            token += (char) ch;
            this.in.mark(0);
            ch = this.in.read(); 
            while ( ch == (int) '\n' || ch == (int) '\r')
            {
              token += (char) ch;
              this.in.mark(0);
              ch = this.in.read();
            }
        }
        this.in.reset();
        System.out.println("token: ->" +token + "<-");
        return token;
      }
      catch (IOException e)
      {
        return null;
      }
    }
    else
      throw new NoSuchElementException();
  }
}
