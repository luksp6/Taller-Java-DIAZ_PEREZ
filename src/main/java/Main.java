package main.java;

import java.util.Arrays;

import main.java.ejercicio1.Ejercicio1;
import main.java.ejercicio2.Ejercicio2;

public class Main 
{
    public static void main(String[] args)
    {
        Ejercicio1 ej1 = new Ejercicio1();
        //ej1.test();

        Ejercicio2 ej2 = new Ejercicio2();
        System.out.println(ej2.fibonacci(1000));
    }
}
