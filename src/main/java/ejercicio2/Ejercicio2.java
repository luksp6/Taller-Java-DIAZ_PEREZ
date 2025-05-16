package main.java.ejercicio2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ejercicio2 
{
    public Ejercicio2()
    {

    }


    private BigInteger calcular(int num, List<BigInteger> resultados)
    {
        if (num == 0 || num == 1)
            return BigInteger.valueOf(num);
        else
        {
            if (resultados.get(num - 1) == BigInteger.ZERO)
                resultados.set(num - 1, calcular(num - 1, resultados));
            if (resultados.get(num - 2) == BigInteger.ZERO)
                resultados.set(num - 2, calcular(num - 2, resultados));
            return resultados.get(num - 1).add(resultados.get(num - 2));
        }
    }

    public BigInteger fibonacci(int num)
    {
        List<BigInteger> resultados = new ArrayList<>(Collections.nCopies(num, BigInteger.ZERO));
        return calcular(num, resultados);
    }
}
