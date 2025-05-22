package main.java.ejercicios;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.utils.Portal;

public class Ejercicio5 extends Ejercicio
 {
    private int entradaF;
    private int entradaC;

    public Ejercicio5(String nombre)
    {
        super(nombre);
    }

    @Override
    public void resolver(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolver'");
    }

    public int getCamino(char[][] mapa, int f, int c)
    {
        Map<Character, Portal> portales = this.reconocerMapa(mapa, f, c);
        return -1;
    }

    private int getCamino_aux(char[][] mapa, int limit_f, int limit_c, int posF, int posC, Map<String, Portal> portales)
    {
        return 0;
    }

    private Map<Character, Portal> reconocerMapa(char[][] mapa, int limit_f, int limit_c)
    {
        Map<Character, Portal> portales = new HashMap<>();
        for (int i = 0; i < limit_f; i++)
        {
            for (int j = 0; j < limit_c; j++)
            {
                if (Character.isLowerCase(mapa[i][j]))
                {
                    if (portales.keySet().contains(mapa[i][j]))
                    {
                        portales.get(mapa[i][j]).setX2(i);
                        portales.get(mapa[i][j]).setY2(j);
                    }
                    else
                    {
                        Portal p = new Portal(i, j);
                        portales.put(mapa[i][j], p);
                    }
                }
                else if (mapa[i][j] == 'E')
                {
                    this.entradaF = i;
                    this.entradaC = j;
                }
            }
        }
        return portales;
    }

    @Override
    public void test() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }
    
}
