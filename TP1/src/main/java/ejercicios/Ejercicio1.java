package main.java.ejercicios;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Ejercicio1 extends Ejercicio
{

    public Ejercicio1(String nombre)    
    {
        super(nombre);
    }


    @Override
    public void resolver(Scanner scanner) 
    {
        System.out.print("Ingrese la cadena a procesar (sin saltos de línea): ");
        String c = scanner.nextLine();
        int num = this.leerEnteroPositivo("Indique la longitud minima de una palabra para ser contabilizada: ", scanner);
        System.out.println("Palabra mas usada: " + this.palabraMasUsada(c, num));
        this.esperarEnter(scanner);
    }


    private String palabraMasUsada(String cadena, int delimitador) 
    {
        //Preprocesamos la cadena de entrada separando las palabras por los caracteres que no sean letras
        String[] palabras = cadena.split("[^a-zA-Z]+");

        //Inicializamos la estructura auxiliar. Usamos un Map para acceder directamente a la entrada con la cantidad de apariciones de la palabra
        Map<String, Integer> contador = new HashMap<>();        
        String masUsada = "";
        contador.put(masUsada, 0);

        //Recorremos las palabras de la cadena
        for (String palabra : palabras)
        {
            //Nos quedamos con las palabras cuya longitud sea mayor al delimitador
            if (palabra.length() >= delimitador)
            {
                Integer cantidad = contador.get(palabra);

                if (cantidad != null)
                    //Si la palabra existe en el mapa, incrementamos su contador
                    contador.put(palabra.toLowerCase(), cantidad + 1);
                else
                    //Si no existe la insertamos con valor de 1
                    contador.put(palabra.toLowerCase(), 1);

                // Verificamos si encontramos una nueva palabra más usada
                if (contador.get(masUsada.toLowerCase()) < contador.get(palabra.toLowerCase()))
                    masUsada = palabra;
            }
        }
        return masUsada;
    }
    
    
    @Override
    public void test()
    {
        String cadena = "pablito clavo un clavito, que clavito clavo pablito pablito,que,que,que;que%que,que";
        int delimitador = 4;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Resultado esperado: pablito");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "";
        delimitador = 1;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = ",,,;;;!!!";
        delimitador = 1;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "Hola";
        delimitador = 1;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);       
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "hola hola mundo mundo hola";
        delimitador = 4;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "a bb ccc dddd";
        delimitador = 5;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "apple banana apple banana pear";
        delimitador = 5;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);            
        System.out.println("Resultado esperado: apple");        
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "Hola123 456Hola adios! hola_hola";
        delimitador = 4;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "Supercalifragilisticoespialidoso otra supercalifragilisticoespialidoso";
        delimitador = 10;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: Supercalifragilisticoespialidoso");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "Hola hola HOLA";
        delimitador = 4;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "perro gato elefante";
        delimitador = 20;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "Hola hOla hoLa holA HOLA hola chau chau";
        delimitador = 4;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();

        cadena = "a".repeat(100);
        delimitador = 99;
        System.out.println("Entrada: ");
        System.out.println(cadena);
        System.out.println("Delimitador: " + delimitador);        
        System.out.println("Resultado esperado: aaaaaaaaaa... (100 'a's)");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, delimitador));
        System.out.println();
    }
}