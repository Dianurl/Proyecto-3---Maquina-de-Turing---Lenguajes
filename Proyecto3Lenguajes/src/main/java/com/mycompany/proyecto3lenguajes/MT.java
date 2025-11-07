/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto3lenguajes;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author DELL
 */
public class MT {
    private final String regex;
    private final Pattern pattern;
    private Cinta tape;
    private String currentState;
    private boolean accepted;
    private boolean finished;
    private int steps;
    private static Scanner type = new Scanner(System.in);
    
    public MT(String regex) { // Contructor de la maquina de turing
            this.regex = regex;
            this.pattern = Pattern.compile(regex);
            this.currentState = "Inicializado";
            this.finished = false;
            this.steps = 0;
    }
    
    public String getRegex() {
        return regex;
    }
    
    public void loadInput(String input) { // Funcion encargada de leer la cinta y asociarlo al lenguaje de la cadena
        this.accepted = pattern.matcher(input).matches(); // Se asocia al patron correcto del lenguaje
        this.tape = new Cinta(input);
        this.currentState = "Escaneando";
        this.finished = false;
        this.steps = 0;
    }
    
    public boolean step() { // Analiza un solo paso de la maquina de Turing
        if(finished)
            return false;
        
        char s = tape.read(); // Se lee la cabeza
        steps++;
        if(s == '_') {
            if(accepted) { // Si la cadena pertenece al patron, se acepta; De lo contrario se rechaza
                currentState = "Aceptada";
            } else {
                currentState = "Rechazada";
            }
            
            finished = true; // Se termina la cadena
            return false;
        } 
        char newSymbol = s;
        if(s == 'a') // Al ir leyendo la cabeza, se convertira el simbolo a uno nuevo
            newSymbol = '1';
        
        if(s == 'b')
            newSymbol = '0';
        
        if(s == '1')
            newSymbol = 'a';
        
        if(s == '0')
            newSymbol = 'b';
        
        tape.write(newSymbol);
        tape.moveRight(); // Si aun no a terminado, sigue leyendo
        currentState = "Escaneando";
        return true;
        
    }
    
    public void runAutomatic(int maxSteps) { // Funcion que analiza la cinta hasta que termine
        int count = 0; // Pasos a analizar
        while(count < maxSteps) {
            boolean progress = step(); // Analiza cada simbolo de la cadena
            if(!progress) // Cuando ya termino de leer
                break;
            
            count++;
        }
    }
    
    public boolean isFinished() { // Funciones para obetner cada uno de los estados analizados de la cadena
        return finished;
    }
    
    public String getCurrentState() {
        return currentState;
    }
    
    public String tapeSnapshot(int window) {
        return tape.snapshot(window);
    }
    
    public String finalTapeContent() {
        return tape.fullContentTrimmed();
    }
    
    public boolean accepted() {
        return accepted;
    }
    
    public int getSteps() {
        return steps;
    }
    
    public void CreateMT(MT mt) { // Funcion que analiza la cadena en MT y su metodo para analizar
        System.out.println("Ingrese la cadena a evauluar (No dejar espacios): ");
        String cadena = type.nextLine().trim();
        while(cadena.isEmpty()) { // Evita que se cree un salto no deseado
            cadena = type.nextLine().trim();
        }
        loadInput(cadena); // Se asocia la cadena a utilizar
        
        System.out.println("Elija modo a analizar: ");
        System.out.println("1. Paso a paso");
        System.out.println("2. Automatico");
        int modo = type.nextInt();
        
        if(modo == 1) {
            stepBystep(mt);
        } else {
            automaticRun(mt);
        }
        
        System.out.println("Visualizacion MT: ");
        System.out.println("La cadena fue: " + mt.getCurrentState()); // Estado final simulada: 
        System.out.println("Pasos simulados: " + mt.getSteps());
        System.out.println("Contenido final de la cinta: " + mt.finalTapeContent());
    }
    
    private static void stepBystep(MT mt) { // Funcion que analiza la cadena paso a paso
            System.out.println("\n Paso a paso:");
            while(true) {
                System.out.println("Estado: " + mt.getCurrentState() + " | Pasos: " + mt.getSteps());
                System.out.println("Cinta: " + mt.tapeSnapshot(7)); //12 // Se lee la cabeza actual con una cantidad definida para la cinta
                System.out.println("Para avanar oprima 'Espacio' + 'Enter'");
                String write = type.nextLine();
                
                while(write.isEmpty()) { // Evita que se creen saltos no deseado
                    write = type.nextLine();
                }
                
                boolean progress = mt.step();
                if(!progress) { // Si ya se leyo toda la cadena
                    System.out.println("MT finalizada \n");
                    break;
                }
            }
        }
        
        private static void automaticRun(MT mt) { // Funcion que lee toda la MT y la analiza de una sola vez
            int maxSteps = 10000; // Numero gigante para siempre leer toda la cadena
            mt.runAutomatic(maxSteps); // Lee y analiza la cadena de corrido
            System.out.println("Analisis automatico completado \n");
        }
}
