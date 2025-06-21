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
        //Inicialmente verificamos si el árbol es null, caso para el cuál retornamos verdadero
        if (arbol == null)
            return true;
        else
        {
            //Si el árbol no es nulo, verificamos que el nodo actual cumpla con la condición
            boolean cumpleDato = arbol.getData() > min && arbol.getData() < max;
            boolean cumpleLeft = true;
            boolean cumpleRight = true;
            if (arbol.getLeft() != null)
                //Si el árbol tiene un hijo izquierdo, verificamos que este también cumpla con la condición realizando una invocación recursiva
                //y utilizando el dato del nodo actual como valor máximo
                cumpleLeft = this.esABB(arbol.getLeft(), min, arbol.getData());
            if (arbol.getRight() != null)
                //Realizamos lo mismo con el hijo derecho, ahora utilizando el dato actual como valor mínimo
                cumpleRight = this.esABB(arbol.getRight(), arbol.getData(), max);
            //Finalmente retornamos un AND entre la evaluación de la función en el nodo actual y en sus hijos
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

        Node arbol_5 = new Node(100,
                                new Node(100),
                                new Node(100));
        System.out.println("\n Arbol: \n" + arbol_5);
        System.out.println("Respuesta esperada: false");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_5, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_6 = new Node(10,
                                new Node(5,
                                    null,
                                    new Node(16)),
                                new Node(15));
        System.out.println("\n Arbol: \n" + arbol_6);
        System.out.println("Respuesta esperada: false");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_6, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_7 = new Node(50,
                                new Node(40),
                                new Node(60,
                                    new Node(55),
                                    new Node(70)));
        System.out.println("\n Arbol: \n" + arbol_7);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_7, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_8 = new Node(20,
                            new Node(10,
                                new Node(5),
                                new Node(15)),
                            new Node(30,
                                new Node(25,
                                    new Node(22),
                                    null),
                                new Node(35)));
        System.out.println("\n Arbol: \n" + arbol_8);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_8, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_9 = new Node(20,
                            new Node(10,
                                new Node(5),
                                new Node(25)),
                            new Node(30));
        System.out.println("\n Arbol: \n" + arbol_9);
        System.out.println("Respuesta esperada: false");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_9, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_10 = new Node(10,
                            new Node(5,
                                new Node(2),
                                new Node(7)),
                            new Node(20,
                                new Node(15,
                                    new Node(12),
                                    new Node(17)),
                                new Node(30)));
        System.out.println("\n Arbol: \n" + arbol_10);
        System.out.println("Respuesta esperada: true");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_10, Integer.MIN_VALUE, Integer.MAX_VALUE));

        Node arbol_11 = new Node(10,
                            new Node(5,
                                new Node(2),
                                new Node(7)),
                            new Node(20,
                                new Node(9),
                                new Node(30)));
        System.out.println("\n Arbol: \n" + arbol_11);
        System.out.println("Respuesta esperada: false");
        System.out.println("Respuesta obtenida: " + this.esABB(arbol_11, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
    
}
