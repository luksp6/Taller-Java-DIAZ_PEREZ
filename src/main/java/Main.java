package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.ejercicios.*;

public class Main 
{
    public static void main(String[] args)
    {
        List<Ejercicio> ejercicios = new ArrayList<>();
        Ejercicio ej1 = new Ejercicio1("Obtener palabra mas usada");
        Ejercicio ej2 = new Ejercicio2("Calcular Fibonacci");
        ejercicios.add(ej1);
        ejercicios.add(ej2);


        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir)
        {
            System.out.println("\n--- MENU PRINCIPAL ---");
            for (int i = 0; i < ejercicios.size(); i++)
                System.out.println((i + 1) + ". " + ejercicios.get(i).getNombre());
            System.out.println((ejercicios.size() + 1) + ". Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            if (opcion <= ejercicios.size())
                ejercicios.get(opcion - 1).start(scanner);
            else if (opcion == ejercicios.size() + 1)
                salir = true;
            else
                System.out.println("Opción invalida. Intente nuevamente.");
        }
        scanner.close();
    }
}
