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
        System.out.println("Resultado esperado: pablito");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(cadena, 4));
        System.out.println();

        
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("", 1));
        System.out.println();
        
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada(",,,;;;!!!", 1));
        System.out.println();
       
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("Hola", 1));
        System.out.println();
        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("hola hola mundo mundo hola", 4));
        System.out.println();

        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("a bb ccc dddd", 5));
        System.out.println();
            
        System.out.println("Resultado esperado: apple");        
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("apple banana apple banana pear", 5));
        System.out.println();
        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("Hola123 456Hola adios! hola_hola", 4));
        System.out.println();
        
        System.out.println("Resultado esperado: Supercalifragilisticoespialidoso");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("Supercalifragilisticoespialidoso otra supercalifragilisticoespialidoso", 10));
        System.out.println();
        
        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("Hola hola HOLA", 4));
        System.out.println();
        
        System.out.println("Resultado esperado: ");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("perro gato elefante", 20));
        System.out.println();

        System.out.println("Resultado esperado: Hola");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("Hola hOla hoLa holA HOLA hola chau chau", 4));
        System.out.println();
        
        System.out.println("Resultado esperado: aaaaaaaaaa... (100 'a's)");
        System.out.println("Resultado obtenido: " + this.palabraMasUsada("a".repeat(100), 99));
        System.out.println();
    }
}