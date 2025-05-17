package main.java.ejercicios;

import java.util.Scanner;

public abstract class Ejercicio
{
    private String nombre;


    public Ejercicio(String nombre)
    {
        this.nombre = nombre;
    }


    public String getNombre()
    {
        return this.nombre;
    }


    public void start(Scanner scanner)
    {        
        boolean salir = false;
        while (!salir)
        {             
            System.out.println("\n--- " + this.nombre + " ---");
            System.out.println("1. Nueva ejecucion");
            System.out.println("2. Ejecutar los tests predefinidos");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");           
            int opcion = scanner.nextInt();
            switch (opcion)
            {
                case 1:
                    scanner.nextLine();
                    this.resolver(scanner);
                    break;
                case 2:
                    this.test();
                    scanner.nextLine();
                    System.out.println("Presione Enter para regresar al sub-menú");
                    scanner.nextLine();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }


    protected int leerEnteroPositivo(String mensaje, Scanner scanner)
    {
        int num = -1;
        while (num < 0)
        {
            System.out.print(mensaje);
            if (scanner.hasNextInt())
            {
                num = scanner.nextInt();
                if (num < 0)
                    System.out.println("El número debe ser positivo. Intente nuevamente.");
            }
            else
            {
                System.out.println("Entrada inválida. Debe ingresar un número entero positivo.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return num;
    }


    protected void esperarEnter(Scanner scanner)
    {
        System.out.println("Presione Enter para regresar al sub-menú");
        scanner.nextLine();
    }


    public abstract void resolver(Scanner scanner);


    public abstract void test(); 
}
