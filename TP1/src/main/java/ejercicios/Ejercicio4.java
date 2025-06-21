package main.java.ejercicios;

import java.util.Scanner;

public class Ejercicio4 extends Ejercicio
{

    public Ejercicio4(String nombre)
    {
        super(nombre);
    }

    @Override
    public void resolver(Scanner scanner)
    {
        System.out.print("Ingrese el arreglo de fotografos: ");
        String arreglo = scanner.nextLine();
        int x = this.leerEnteroPositivo("Indique X: ", scanner);
        int y = this.leerEnteroPositivo("Indique Y: ", scanner);
        System.out.println("Fotografias artisticas posibles: " + this.fotografiasArtisticas(arreglo.toCharArray(), x, y));
        this.esperarEnter(scanner);
    }

    private int fotografiasArtisticas(char[] arr, int x, int y)
    {
        int cont = 0;
        for (int i = 0; i < arr.length; i++)
            //Recorremos el arreglo hasta encontrar un fotógrafo
            if (arr[i] == 'f')
            {
                //En este punto inicia la búsqueda de un artista tanto en sentido positivo como negativo,
                //acotando el rango a los valores X e Y recibidos por parámetro
                cont += recorridoPositivo(arr, 'a', i + x, i + y, x, y);
                cont += recorridoNegativo(arr, 'a', i - x, i - y, x, y);
            }
        return cont;
    }

    private int recorridoPositivo(char[] arr, char target, int pos_i, int pos_f, int x, int y)
    {
        //Se verifica que la posición de inicio de recorrido no esté fuera de los límites del arreglo
        if (pos_i >= arr.length)
            return 0;

        //Se verifica que la posición final de recorrido no sea menor que la inicial
        if (pos_f < pos_i)
            return 0;

        //Se verifica que la posición final de recorrido no sea mayor que los límites del arreglo
        if (pos_f >= arr.length)
            pos_f = arr.length - 1;

        int cont = 0;
        for (int i = pos_i; i <= pos_f; i++)
            //Se recorre el arreglo dentro del rango dado hasta encontrar el target de búsqueda
            if (arr[i] == target)            
                if (target == 'a')
                    //Si se estaba buscando un artista, se realiza un llamado recursivo
                    //al método con un nuevo rango de exploración y un escenario como objetivo
                    cont += recorridoPositivo(arr, 'e', i + x, i + y, x, y);
                else if (target == 'e')
                    //Si se estaba buscando un escenario se incrementa el contador de fotografías artísticas
                    cont++;
        return cont;
    }

    private int recorridoNegativo(char[] arr, char target, int pos_i, int pos_f, int x, int y)
    //Su funcionamiento es análogo al método recorridoPositivo, con la particularidad de que se recorre
    //el arreglo en sentido contrario, decrementando posiciones a partir de la posición inicial
    {
        if (pos_i < 0)
            return 0;

        if (pos_f > pos_i)
            return 0;

        if (pos_f < 0)
            pos_f = 0;

        int cont = 0;
        for (int i = pos_i; i >= pos_f; i--)
            if (arr[i] == target)
                if (target == 'a')
                    cont += recorridoNegativo(arr, 'e', i - x, i - y, x, y);
                else if (target == 'e')
                    cont++;
        return cont;
    }

    @Override
    public void test()
    {
        System.out.println("Entrada:\nafaea\n" + "X = 1\n" + "Y = 2");    
        char[] arr = "afaea".toCharArray();
        System.out.println("Respuesta esperada: 1");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 2));
        System.out.println();

        System.out.println("Entrada:\nafaea\n" + "X = 2\n" + "Y = 3");
        System.out.println("Respuesta esperada: 0");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 2, 3));
        System.out.println();

        System.out.println("Entrada:\n.feaaf.e\n" + "X = 1\n" + "Y = 3");
        arr = ".feaaf.e".toCharArray();
        System.out.println("Respuesta esperada: 3");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 3));
        System.out.println();
    
        System.out.println("Entrada:\nfaeaeafae\nX = 1\nY = 2");
        arr = "faeaeafae".toCharArray();
        System.out.println("Respuesta esperada: 3");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 2));
        System.out.println();

        System.out.println("Entrada:\nfaaeeafaeae\nX = 1\nY = 3");
        arr = "faaeeafaeae".toCharArray();
        System.out.println("Respuesta esperada: 9");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 3));
        System.out.println();

        System.out.println("Entrada:\naaaaa\nX = 1\nY = 2");
        arr = "aaaaa".toCharArray();
        System.out.println("Respuesta esperada: 0");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 2));
        System.out.println();

        System.out.println("Entrada:\nfaeaeaeaeaeaeaeaeaeae\nX = 1\nY = 10");
        arr = "faeaeaeaeaeaeaeaeaeae".toCharArray();
        System.out.println("Respuesta esperada: 25");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 10));
        System.out.println();

        System.out.println("Entrada:\nfeaeafaeaeafeafaeafae\nX = 2\nY = 3");
        arr = "feaeafaeaeafeafaeafae".toCharArray();
        System.out.println("Respuesta esperada: 3");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 2, 3));
        System.out.println();

        System.out.println("Entrada:\nfeaeafeafeafaeafeafae\nX = 1\nY = 2");
        arr = "feaeafeafeafaeafeafae".toCharArray();
        System.out.println("Respuesta esperada: 9");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 2));
        System.out.println();

        System.out.println("Entrada:\nfffaaaeee\nX = 1\nY = 1");
        arr = "fffaaaeee".toCharArray();
        System.out.println("Respuesta esperada: 0");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 1));
        System.out.println();
    
    }
    
}
