package main.java.ejercicios;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.utils.Portal;

public class Ejercicio5 extends Ejercicio
{
    private int entradaF;
    private int entradaC;
    private int contador;
    private int mejorContador;
    private boolean esSalida;

    public Ejercicio5(String nombre)
    {
        super(nombre);
        this.esSalida = false;
    }

    @Override
    public void resolver(Scanner scanner)
    {
        int f = leerEnteroPositivo("Ingrese la cantidad de filas del laberinto: ", scanner);
        int c = leerEnteroPositivo("Ingrese la cantidad de columnas del laberinto: ", scanner);
        char[][] mapa = new char[f][c];
        System.out.println("Ingrese el laberinto (una linea con " + c + " caracteres sin espacios por fila):");
        boolean tieneEntrada = false;
        while (!tieneEntrada)
        {            
            for (int i = 0; i < f; i++)
            {
                boolean lineaValida = false;
                while (!lineaValida)
                {
                    System.out.print("Fila " + (i + 1) + ": ");
                    String linea = scanner.nextLine();
                    if (linea.length() == c)
                    {
                        mapa[i] = linea.toCharArray();
                        lineaValida = true;
                    }
                    else
                        System.out.println("La linea debe tener exactamente " + c + " caracteres.");
                }
                if (!tieneEntrada)
                    for (char ch : mapa[i])
                        if (ch == 'E')
                            tieneEntrada = true;
            }
            if (!tieneEntrada)
                System.out.println("El laberinto debe tener una entrada 'E'. Ingrese una linea con " + c + " caracteres sin espacios por fila):");
        }

        int pasos = obtenerPasos(mapa, f, c);
        if (pasos == -1)
            System.out.println("No se encontro un camino desde la entrada hasta la salida.");
        else
            System.out.println("Cantidad minima de pasos hasta la salida: " + pasos);

    }

    private int obtenerPasos(char[][] mapa, int f, int c)
    {
        //Recorrer el mapa para obtener la posición de entrada y los traslados de los portales
        Map<Character, Portal> portales = this.reconocerMapa(mapa, f, c);

        //Inicializar en falso una matriz de celdas visitadas
        boolean[][] visitados = new boolean[f][c];
        for (int i = 0; i < f; i++)
            Arrays.fill(visitados[i], false);
        this.contador = 0;
        this.mejorContador = Integer.MAX_VALUE;
        this.esSalida = false;        
        if (tieneSalida(mapa, f, c, this.entradaF, this.entradaC, visitados, portales))
            return this.mejorContador;
        else
            return -1;
    }

    private boolean tieneSalida(char[][] mapa, int limit_f, int limit_c, int posF, int posC, boolean[][] visitados, Map<Character, Portal> portales)
    {
        if (mapa[posF][posC] == 'S')
        {
            //Si la posición actual es la salida
            if (this.contador < this.mejorContador)
                //Incrementamos el contador de pasos si se encontró un camino más corto
                this.mejorContador = this.contador;
            //Retornamos verdadero
            return true;
        }

        if (mapa[posF][posC] == '#')
            //Si la posición actual es una pared retornamos falso
            return false;
        else
        {
            //Si la posición actual es una celda válida distinta a la salida, incrementamos el contador
            //de pasos y marcamos la celda como visitada
            this.contador++;
            visitados[posF][posC] = true;

            //Portal
            if(Character.isLowerCase(mapa[posF][posC]))
            {
                //Si la celda actual es un portal, calculamos nuestra nueva posicion, verificamos no haberla visitado y nos transportamos
                int posF_nueva = portales.get(mapa[posF][posC]).getF(posF);
                int posC_nueva = portales.get(mapa[posF][posC]).getC(posC);
                if (!visitados[posF_nueva][posC_nueva])
                    if (tieneSalida(mapa, limit_f, limit_c, posF_nueva, posC_nueva, visitados, portales)) this.esSalida = true;
            }

            //Se evaluan los distintos movimientos. Si alguno conduce a la salida, se pone en verdadero el flag esSalida

            //Movimiento arriba
            if (posF - 1 >= 0 && !visitados[posF - 1][posC])
                //Si la celda de arriba está dentro del mapa y no fue visitada, nos movemos a ella
                if (tieneSalida(mapa, limit_f, limit_c, posF - 1, posC, visitados, portales)) this.esSalida = true;

            //Movimiento abajo
            if (posF + 1 < limit_f && !visitados[posF + 1][posC])
                //Si la celda de abajo está dentro del mapa y no fue visitada, nos movemos a ella
                    if (tieneSalida(mapa, limit_f, limit_c, posF + 1, posC, visitados, portales)) this.esSalida = true;

            //Movimiento izquierda
            if (posC - 1 >= 0 && !visitados[posF][posC - 1])
                //Si la celda de la izquierda está dentro del mapa y no fue visitada, nos movemos a ella
                    if (tieneSalida(mapa, limit_f, limit_c, posF, posC - 1, visitados, portales)) this.esSalida = true;

            //Movimiento derecha
            if (posC + 1 < limit_c && !visitados[posF][posC + 1])
                //Si la celda de la derecha está dentro del mapa y no fue visitada, nos movemos a ella
                if (tieneSalida(mapa, limit_f, limit_c, posF, posC + 1, visitados, portales)) this.esSalida = true;

            //Decrementamos el contador de pasos y marcamos la celda como no visitada para que otras exploraciones
            //puedan encontrar una posible salida más corta
            this.contador--;
            visitados[posF][posC] = false;

            //Se retorna el flag esSalida
            return this.esSalida;
        }
    }
    //Este metodo fue creado para reconocer la entrada al mapa y los portados
    private Map<Character, Portal> reconocerMapa(char[][] mapa, int limit_f, int limit_c)
    {
    	//Creamos un mapa de portales ya que buscamos que podamos acceder directamente con la letra a su portal
        Map<Character, Portal> portales = new HashMap<>();
        //recorremos todo el mapa
        for (int i = 0; i < limit_f; i++)
        {
            for (int j = 0; j < limit_c; j++)
            {
            	//Si es un portal
                if (Character.isLowerCase(mapa[i][j]))
                {
                	//Si ya fue cargado el porta, solo cargamos las otras dos posiciones
                    if (portales.keySet().contains(mapa[i][j]))
                    {
                        portales.get(mapa[i][j]).setX2(i);
                        portales.get(mapa[i][j]).setY2(j);
                    }
                    else //sino, creamos el portal y lo agreamos al mapa solamente con 2 posiciones cargadas.
                    {
                        Portal p = new Portal(i, j);
                        portales.put(mapa[i][j], p);
                    }
                }
                //si es la entrada se carga a las variables pribadas de la clase ejercicio5, asi sabemos por donde empezar
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
    public void test()
    {
        char[][] mapa = new char[][] {
            {'E', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', 'S'}};
        int x = 3;
        int y = 3;
        System.out.println("Mapa: ");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: 4");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'E', '#', '.'},
            {'.', '#', '.'},
            {'.', '#', 'S'}};
        x = 3;
        y = 3;
        System.out.println("Mapa: ");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: -1");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'S', '.', 'b', '#', 'b'},
            {'#', '#', '#', '#', 'a'},
            {'.', '.', 'E', '#', '#'},
            {'c', '#', '#', '.', 'c'},
            {'#', 'a', '.', '.', '.'}
        };
        x = 5;
        y = 5;
        System.out.println("Mapa: ");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: 13");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'#', '.', '.', '.', '.', 'b'},
            {'#', '#', '#', '#', '.', '#'},
            {'a', '.', '#', '.', '.', '#'},
            {'#', '.', '#', '#', '#', '#'},
            {'#', '.', '.', '.', 'a', 'S'},
            {'E', '#', '#', 'b', '.', '.'}};
        x = 6;
        y = 6;
        System.out.println("Mapa:");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: -1");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'#', '.', '.', '.', 'c', '.', 'S'},
            {'.', '#', '#', '#', '#', '#', '.'},
            {'.', '.', 'E', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#', '.'},
            {'c', '.', '.', '.', '.', '.', '.'}};
        x = 5;
        y = 7;
        System.out.println("Mapa:");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: 6");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'E', '#', '.', '.', '#', '.'},
            {'#', '#', '#', '.', '#', '.'},
            {'#', 'a', '#', '.', '#', 'a'},
            {'#', '.', '#', 'S', '#', '#'}};
        x = 4;
        y = 6;
        System.out.println("Mapa:");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: -1");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));
        System.out.println();

        mapa = new char[][] {
            {'E', '.', '.', 'a', '.', 'S'},
            {'#', '#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#'},
            {'.', '.', 'a', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#'}};
        x = 6;
        y = 6;
        System.out.println("Mapa:");
        this.printMapa(mapa, x, y);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Salida esperada: 5");
        System.out.println("Salida obtenida: " + this.obtenerPasos(mapa, x, y));     
        System.out.println();   
    }

    private void printMapa(char[][] mapa, int limit_f, int limit_c)
    {
        for (int i = 0; i < limit_f; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < limit_c; j++)
                System.out.print(mapa[i][j] + " |");
            System.out.println();
        }
    }
    
}
