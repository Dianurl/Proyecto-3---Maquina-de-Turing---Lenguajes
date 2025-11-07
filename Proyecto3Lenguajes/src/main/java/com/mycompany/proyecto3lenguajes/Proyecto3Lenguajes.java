/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto3lenguajes;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Proyecto3Lenguajes {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Scanner type = new Scanner(System.in);
        String text = "";
        MT mt = null;
        
        int option = 0;
        while(option != 6) {
            System.out.println("");
            System.out.println("Menu principal"); // Menu principal a utilizar para elegir lenguaje
            System.out.println("Elija el lenguaje de cadena a utilizar:");
            System.out.println("1. Cadena con (a|b)*abb");
            System.out.println("2. Cadena con 0*1*");
            System.out.println("3. Cadena con (ab)*");
            System.out.println("4. Cadena con 1(01)*0");
            System.out.println("5. Cadena con (a+b)*a(a+b)*");
            System.out.println("6. Salir");
            System.out.println("Elegir opcion:");
            String answer = type.nextLine();
            
            try { 
                option = Integer.parseInt(answer); // Seleccion de lenguaje para para MT
                switch(option){ 
                    case 1 -> {
                        mt = MTReg.EndWithABB();
                        text = "Alfabeto a utilizar: a, b";
                        System.out.println(text);
                        mt.CreateMT(mt);
                        break;
                    }
                    
                    case 2 -> {
                        mt = MTReg.ZeroOne();
                        text = "Alfabeto a utilizar: 0, 1";
                        System.out.println(text);
                        mt.CreateMT(mt);
                        break;
                    }

                    case 3 -> {
                        mt = MTReg.AB();
                        text = "Alfabeto a utilizar: a, b";
                        System.out.println(text);
                        mt.CreateMT(mt);
                        break;
                    }
                    
                    case 4 -> {
                        mt = MTReg.EndWithZero();
                        text = "Alfabeto a utilizar: 0, 1";
                        System.out.println(text);
                        mt.CreateMT(mt);
                        break;
                    }
                    
                    case 5 -> {
                        mt = MTReg.ContainsA();
                        text = "Alfabeto a utilizar: a, b";
                        System.out.println(text);
                        //option = 6;
                        mt.CreateMT(mt);
                        break;
                    }
                    
                    case 6 -> {
                        System.out.println("Programa finzalizado correctamente");
                    }

                    default -> {
                        System.out.println("No se eligio una respuesta correcta");
                    }
                }
            }catch(NumberFormatException e) { // Si se escribio una letra, lanza texto y repite menu
                System.out.println("Se ingreso una respuesta incorrecta, vuelva a intentarlo.");
                option = 0;
            }
        }
    }
}
