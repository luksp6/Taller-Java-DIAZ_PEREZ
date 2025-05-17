package main.java.ejercicios;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 extends Ejercicio
{
    public Ejercicio2(String nombre)    
    {
        super(nombre);
    }


    @Override
    public void resolver(Scanner scanner) 
    {
        int num = leerEnteroPositivo("Indique el número para el que desea obtener su Fibonacci: ", scanner);
        List<BigInteger> resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Fibonacci(" + num + ") = " + fibonacci(num, resultados));        
        esperarEnter(scanner);
    }


    private BigInteger fibonacci(int num, List<BigInteger> resultados)
    {
        if (num == 0 || num == 1)
            return BigInteger.valueOf(num);
        else
        {
            if (resultados.get(num - 1) == BigInteger.ZERO)
                resultados.set(num - 1, fibonacci(num - 1, resultados));
            if (resultados.get(num - 2) == BigInteger.ZERO)
                resultados.set(num - 2, fibonacci(num - 2, resultados));
            return resultados.get(num - 1).add(resultados.get(num - 2));
        }
    }


    @Override
    public void test()
    {
        int num = 1;
        List<BigInteger> resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(1) = 1");        
        System.out.println("Resultado obtenido: " + "Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        resultados.clear();
        System.out.println();

        num = 0;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(0) = 0");        
        System.out.println("Resultado obtenido: " + "Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        resultados.clear();
        System.out.println();        
    }
}
