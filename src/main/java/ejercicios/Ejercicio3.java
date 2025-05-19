package main.java.ejercicios;

import java.util.Scanner;
import main.java.utils.Node;

public class Ejercicio3 extends Ejercicio
{

    public Ejercicio3(String nombre)
    {
        super(nombre);
    }

    @Override
    public void resolver(Scanner scanner)
    {
        System.out.println("\n--- Construccion del arbol binario ---");
        Node raiz = this.crearArbol(scanner);
        System.out.println("Se ha construido el siguiente arbol:");
        System.out.println(raiz);
        if (this.esABB(raiz))
            System.out.println("Es un arbol binario de busqueda");
        else
            System.out.println("No es un arbol binario de busqueda");
        esperarEnter(scanner);
    }

    private boolean esABB(Node arbol)
    {
        if (arbol == null)
            return true;
        else
        {
            return false;
        }
    }

    private Node crearArbol(Scanner scanner)
    {
        System.out.print("Ingrese el valor del nodo: ");
        int valor = scanner.nextInt();
        Node nodo = new Node(valor);
        scanner.nextLine();

        System.out.print("¿El nodo " + valor + " tiene hijo izquierdo? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s"))
            nodo.setLeft(crearArbol(scanner));

        System.out.print("¿El nodo " + valor + " tiene hijo derecho? (s/n): ");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s"))
            nodo.setRight(crearArbol(scanner));

        return nodo;
    }

    @Override
    public void test()
    {
        Node arbol = new Node(15);
        arbol.setLeft(new Node(10));
        arbol.setRight(new Node(20));
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol));
    }
    
}
