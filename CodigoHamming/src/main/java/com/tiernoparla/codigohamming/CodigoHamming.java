package com.tiernoparla.codigohamming;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class CodigoHamming {
    static Scanner scanea = new Scanner(System.in);
    static Random num = new Random();

    public static void main(String[] args) {
        int bitsParidad;

        // Primera parte del código hamming con un scanner, un random y un array.

        System.out.println("Introduce un número: ");
        int tamaño = scanea.nextInt();

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
        ///////////////
        /// punto 3////
        ///////////////

        int[] msgHammingModificado = new int[msgHamming.length];
        msgHammingModificado = noise(msgHamming);

        System.out.println();
        for (int i = 0; i < msgHammingModificado.length; i++) {
            if (i == 0 || i == 1 || i == 2 || i == 4 || i == 8 || i == 16)
                System.out.print("->");
            System.out.print(msgHammingModificado[i]);
        }

        ///////////////
        /// punto 4////
        ///////////////

        reciever(msgHammingModificado);

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

    static int[] noise(int[] codigoHamming) {
        int indice;

        for (int i = 0; i < 2; i++) {
            indice = num.nextInt(codigoHamming.length - 1);
            if (indice == 0 || indice == 1 || indice == 2 || indice == 4 || indice == 8 || indice == 16) {
                continue;
            } else
                codigoHamming[indice] = num.nextInt(2);
        }

        return codigoHamming;
    }

    static void reciever(int[] msg) {
        //No he conseguido hacer que funcione todavia, falta depuración.
        int contador = 0;
        int bit0 = 0, bit1 = 0, bit2 = 0, bit4 = 0, bit8 = 0, bit16 = 0;
        int marca = 0;
        boolean esCorrecto = true;

        //////////// bit paridad ///////////////////////////////////////////////
        for (int i = 0; i < msg.length; i++) {
            if (msg[1] == 1 || msg[2] == 1 || msg[4] == 1 || msg[8] == 1 || msg[16] == 1) {
                contador++;
            }
        }
        // si el numero de 1's es par y el bit de paridad es 0 es correcto. Si es impar
        // y el bit de paridad es 1, es correcto.
        if ((contador % 2 == 0 && msg[0] == 0) || (contador % 2 != 0 && msg[0] == 1)) {
            esCorrecto = true;
        } else {
            esCorrecto = false;
            bit0 = 0;
        }

        //////////// bit paridad 1 ///////////////////////////////////////////////
        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[3] == 1 || msg[5] == 1 || msg[7] == 1 || msg[9] == 1 || msg[11] == 1 || msg[13] == 1 || msg[15] == 1
                    || msg[17] == 1 || msg[19] == 1 || msg[21] == 1) {
                contador++;
            }
        }
        // si el numero de 1's es par y el bit de paridad es 0 es correcto. Si es impar
        // y el bit de paridad es 1, es correcto.
        if ((contador % 2 == 0 && msg[1] == 0) || (contador % 2 != 0 && msg[1] == 1)) {

        } else {
            marca++;
            bit1 = 1;
        }

        ///////// bit paridad 2//////////////////////////////////////////////////////
        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[3] == 1 || msg[6] == 1 || msg[7] == 1 || msg[10] == 1 || msg[11] == 1 || msg[14] == 1
                    || msg[15] == 1 || msg[18] == 1 || msg[19] == 1) {
                contador++;
            }
        }
        if ((contador % 2 == 0 && msg[2] == 0) || (contador % 2 != 0 && msg[2] == 1)) {

        } else {
            marca++;
            bit2 = 2;
        }
        /////// bit paridad 4 ////////////////////////////////////////////////////

        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[5] == 1 || msg[6] == 1 || msg[7] == 1 || msg[12] == 1 || msg[13] == 1 || msg[14] == 1
                    || msg[15] == 1 || msg[20] == 1 || msg[21] == 1) {
                contador++;
            }
        }
        if ((contador % 2 == 0 && msg[4] == 0) || (contador % 2 != 0 && msg[4] == 1)) {

        } else {
            marca++;
            bit4 = 4;
        }
        /////// bit paridad 8 ////////////////////////////////////////////////////

        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[9] == 1 || msg[10] == 1 || msg[11] == 1 || msg[12] == 1 || msg[13] == 1 || msg[14] == 1
                    || msg[15] == 1) {
                contador++;
            }
        }
        if ((contador % 2 == 0 && msg[8] == 0) || (contador % 2 != 0 && msg[8] == 1)) {

        } else {
            marca++;
            bit8 = 8;
        }

        /////// bit paridad 16 ////////////////////////////////////////////////////

        contador = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[17] == 1 || msg[18] == 1 || msg[19] == 1 || msg[20] == 1 || msg[21] == 1) {
                contador++;
            }
        }
        if ((contador % 2 == 0 && msg[16] == 0) || (contador % 2 != 0 && msg[16] == 1)) {

        } else {
            marca++;
            bit16 = 16;
        }

        if (marca > 0) {
            System.out.println();
            System.out.println("Hay un error en el bit " + (bit1 + bit2 + bit4 + bit8 + bit16));
        } else if (marca > 0 && esCorrecto) {
            System.out.println("hay dos fallos");
        } else {
            System.out.println();
            System.out.println("El código es igual que el original.");
        }
    }

}// class
