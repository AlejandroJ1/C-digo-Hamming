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
        int[] mensage = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            mensage[i] = num.nextInt(2);
            System.out.print(mensage[i]);
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

        msgHamming = codigoHamming(longMsgHami, mensage);
        for (int i = 0; i < msgHamming.length; i++) {
            if (i == 0 || i == 1 || i == 2 || i == 4 || i == 8 || i == 16)
                System.out.print("->");
            System.out.print(msgHamming[i]);
        }

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

    static int[] codigoHamming(int tamañoTotal, int[] codOriginal) {
        int msgHamming[] = new int[tamañoTotal];
        int i = 0;
        int j = 0;
        for (i = 0, j = 0; i < msgHamming.length; i++) {
            if ((i == Math.pow(2, 0) || i == Math.pow(2, 1) || i == Math.pow(2, 2) || i == Math.pow(2, 3) ||
                    i == Math.pow(2, 4) || i == Math.pow(2, 5) || i == Math.pow(2, 6) || i == Math.pow(2, 7))
                    || j > codOriginal.length - 1) {
                continue;
            } else {
                msgHamming[i] = codOriginal[j];
                j++;
                if (j > codOriginal.length - 1) {
                    i++;
                    break;
                }
            }
        }
        int aux[] = new int[i + 1];
        while (i + 1 > 0) {
            aux[i] = msgHamming[i];
            i--;
        }
        activarBitsParidad(aux);
        return aux;
    }

    static int[] activarBitsParidad(int[] msg) {

        int contador = 0;
        //////////// bit paridad 1 ///////////////////////////////////////////////
        for (int i = 0; i < msg.length; i++) {
            if (msg[3] == 1 || msg[5] == 1 || msg[7] == 1 || msg[9] == 1 || msg[11] == 1 || msg[13] == 1 || msg[15] == 1
                    || msg[17] == 1 || msg[19] == 1 || msg[21] == 1) {
                contador++;
            }
            if (contador % 2 == 0)
                msg[1] = 0;
            else
                msg[1] = 1;
        }
        ///////// bit paridad 2//////////////////////////////////////////////////////
        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[3] == 1 || msg[6] == 1 || msg[7] == 1 || msg[10] == 1 || msg[11] == 1 || msg[14] == 1
                    || msg[15] == 1 || msg[18] == 1 || msg[19] == 1) {
                contador++;
            }
            if (contador % 2 == 0)
                msg[2] = 0;
            else
                msg[2] = 1;
        }
        /////// bit paridad 4 ////////////////////////////////////////////////////

        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[5] == 1 || msg[6] == 1 || msg[7] == 1 || msg[12] == 1 || msg[13] == 1 || msg[14] == 1
                    || msg[15] == 1 || msg[20] == 1 || msg[21] == 1) {
                contador++;
            }
            if (contador % 2 == 0)
                msg[4] = 0;
            else
                msg[4] = 1;
        }
        /////// bit paridad 8 ////////////////////////////////////////////////////

        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[9] == 1 || msg[10] == 1 || msg[11] == 1 || msg[12] == 1 || msg[13] == 1 || msg[14] == 1
                    || msg[15] == 1) {
                contador++;
            }
            if (contador % 2 == 0)
                msg[8] = 0;
            else
                msg[8] = 1;
        }
        /////// bit paridad 16 ////////////////////////////////////////////////////
        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[17] == 1 || msg[18] == 1 || msg[19] == 1 || msg[20] == 1 || msg[21] == 1) {
                contador++;
            }
            if (contador % 2 == 0)
                msg[16] = 0;
            else
                msg[16] = 1;
        }

        return msg;
    }

}// class
