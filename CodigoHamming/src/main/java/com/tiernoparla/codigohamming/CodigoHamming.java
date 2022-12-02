package com.tiernoparla.codigohamming;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class CodigoHamming {

    public static void main(String[] args) {
        int bitsParidad;

        // Primera parte del código hamming con un scanner, un random y un array.
        Scanner scanea = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        int tamaño = scanea.nextInt();

        Random num = new Random();
        int[] msg = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            msg[i] = num.nextInt(2);
            System.out.print(msg[i]);
        }
        System.out.println();
        ///////////////
        //// punto 2////
        ///////////////
        bitsParidad = calcularBitsRed(tamaño);
        System.out.print("bits paridad " + bitsParidad);

        int longMsgHami = tamaño + bitsParidad + mensage.length + 1;
        int[] msgHamming = new int[longMsgHami];

        System.out.println();

    }// main

    static int calcularBitsRed(int tamaño) {

        int i = 0;
        int j = 0;

        while ((j) < tamaño) {
            i++;
            j = (int) Math.pow(2, i) - (i + 1);
        }
        return i;
    }

}// class
