package com.tiernoparla.codigohamming;

import java.util.Random;
import java.util.Scanner;

public class CodigoHamming {

    public static void main(String[] args) {
        //Primera parte del código hamming con un scanner, un random y un array.
        Scanner scanea = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        int tamaño = scanea.nextInt();

        Random num = new Random();
        int[] mensage = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            mensage[i] = num.nextInt(2);
            System.out.print(mensage[i]);
        }

    }

}
