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
        int num = leerEnteroPositivo("Indique el numero para el que desea obtener su Fibonacci: ", scanner);
        List<BigInteger> resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Fibonacci(" + num + ") = " + fibonacci(num, resultados));        
        esperarEnter(scanner);
    }


    private BigInteger fibonacci(int num, List<BigInteger> resultados)
    {
        //Inicialmente verificamos si estamos calculando los valores 0 o 1, ya que en ese caso se debe retornar su mismo valor
        if (num == 0 || num == 1)
            return BigInteger.valueOf(num);
        else
        {
            //Si calculamos un número distinto a 0 o 1, verificamos si los dos fibonaccis anteriores ya fueron calculados
            if (resultados.get(num - 1) == BigInteger.ZERO)
                //Si todavía no fueron calculados, se calculan recursivamente y se almacena su valor
                resultados.set(num - 1, fibonacci(num - 1, resultados));
            if (resultados.get(num - 2) == BigInteger.ZERO)
                resultados.set(num - 2, fibonacci(num - 2, resultados));
            //Finalmente se retorna la suma de los dos fibonaccis anteriores
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

        num = 5;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(5) = 5");
        System.out.println("Resultado obtenido: Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        System.out.println();

        num = 10;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(10) = 55");
        System.out.println("Resultado obtenido: Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        System.out.println();

        num = 20;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(20) = 6765");
        System.out.println("Resultado obtenido: Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        System.out.println();

        num = 50;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(50) = 12586269025");
        System.out.println("Resultado obtenido: Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        System.out.println();

        num = 100;
        resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        System.out.println("Resultado esperado: Fibonacci(100) = 354224848179261915075");
        System.out.println("Resultado obtenido: Fibonacci(" + num + ") = " + fibonacci(num, resultados));
        System.out.println();
    }
}
