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

    public int fotografiasArtisticas(char[] arr, int x, int y)
    {
        int cont = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 'f')
            {
                cont += recorridoPositivo(arr, 'a', i + x, i + y, x, y);
                cont += recorridoNegativo(arr, 'a', i - x, i - y, x, y);
            }
        return cont;
    }

    private int recorridoPositivo(char[] arr, char target, int pos_i, int pos_f, int x, int y)
    {
        if (pos_i >= arr.length)
            return 0;

        if (pos_f < pos_i)
            return 0;

        if (pos_f >= arr.length)
            pos_f = arr.length - 1;

        int cont = 0;
        for (int i = pos_i; i <= pos_f; i++)
            if (arr[i] == target)
                if (target == 'a')
                    cont += recorridoPositivo(arr, 'e', i + x, i + y, x, y);
                else if (target == 'e')
                        cont++;
        return cont;
    }

    private int recorridoNegativo(char[] arr, char target, int pos_i, int pos_f, int x, int y)
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
        System.out.println("---------------");
        char[] arr = {'a', 'f', 'a', 'e', 'a'};
        System.out.println("Respuesta esperada: 1");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 1, 2));
        System.out.println();

        System.out.println("Entrada:\nafaea\n" + "X = 2\n" + "Y = 3");        
        System.out.println("---------------");
        System.out.println("Respuesta esperada: 0");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr, 2, 3));
        System.out.println();

        System.out.println("Entrada:\n.feaaf.e\n" + "X = 1\n" + "Y = 3");        
        System.out.println("---------------");
        char[] arr_1 = {'.', 'f', 'e', 'a', 'a', 'f', '.', 'e'};
        System.out.println("Respuesta esperada: 3");
        System.out.println("Respuesta obtenida: " + this.fotografiasArtisticas(arr_1, 1, 3));
        System.out.println();
    }
    
}
