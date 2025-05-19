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
        if (this.esABB(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE))
            System.out.println("Es un arbol binario de busqueda");
        else
            System.out.println("No es un arbol binario de busqueda");
        esperarEnter(scanner);
    }

    private boolean esABB(Node arbol, int min, int max)
    {
        if (arbol == null)
            return true;
        else
        {
            boolean cumpleDato = arbol.getData() > min && arbol.getData() < max;
            boolean cumpleLeft = true;
            boolean cumpleRight = true;
            if (arbol.getLeft() != null)
                cumpleLeft = this.esABB(arbol.getLeft(), min, arbol.getData());
            if (arbol.getRight() != null)
                cumpleRight = this.esABB(arbol.getRight(), arbol.getData(), max);
            return cumpleDato && cumpleLeft && cumpleRight;
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
        Node arbol_1 = new Node(50,
                         new Node(40,
                            new Node(30,
                                new Node(25),
                                new Node(35)),
                            new Node(45)),
                        new Node(60,
                            new Node(55),
                            new Node(65)));
        System.out.println("\n Arbol: \n" + arbol_1);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_1, Integer.MIN_VALUE, Integer.MAX_VALUE));
        
        Node arbol_2 = new Node(50);
        System.out.println("\n Arbol: \n" + arbol_2);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_2, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_3 = null;
        System.out.println("\n Arbol: \n" + arbol_3);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_3, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_4 = new Node(100,
                                new Node(200),
                                new Node(300));
        System.out.println("\n Arbol: \n" + arbol_4);
        System.out.println("Respuesta esperada: false");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_4, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
    
}
