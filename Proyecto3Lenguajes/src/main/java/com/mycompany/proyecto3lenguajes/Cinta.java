/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto3lenguajes;

import java.util.Arrays;

/**
 *
 * @author DELL
 */
public class Cinta {
    private char blank = '_'; // Simboliza el espacio en blanco de la cinta
    private char[] cells; // La cinta como tal
    private int head; // Posicion de la cabeza actual
    
    public Cinta(String cadena) { // Constructor que crea la cinta
        int size = Math.max(128, cadena.length() + 64);
        cells = new char[size];
        Arrays.fill(cells, blank);
        int start = size/4;
        for(int i = 0; i < cadena.length(); i++) { // Se ubica la cadena a la mitad de la cinta y lo demas se llena de blanks
            cells[start + i] = cadena.charAt(i);
        }
        
        head = start;
    }
    
    private void ensureIndex(int idx) { // Funcion que asegura la existencia de la posicion requerida
        if(idx < 0 || idx >= cells.length) { // Si no se encuentra la posicion
            int newsize = cells.length * 2; // Se duplica el tamano de la cinta
            char[] newcells = new char[newsize];
            Arrays.fill(newcells, blank);
            int offset = newsize / 4;
            System.arraycopy(cells, 0, newcells, offset, cells.length); // Se vuelde a colocar el contenido
            head += offset;
            cells = newcells;
        }
    }
    
    public char read() { // Funcion para leer la cabeza actual
        ensureIndex(head);
        return cells[head];
    }
    
    public void write(char c) { // Escribe algun simbolo en la posiscion actual
        ensureIndex(head);
        cells[head] = c;
    }
    
    public void moveRight() { // Mueve cabeza a la derecha
        head++;
        ensureIndex(head);
    }
    
    public void moveLeft() { // Mueve cabeza a la izquierda
        head--;
        ensureIndex(head);
    }
    
    public String snapshot(int window) { // Funcion que crea la cinta de forma visual
        int left = Math.max(0, head - window);
        int right = Math.min(cells.length - 1, head + window);
        StringBuilder sb = new StringBuilder();
        for(int i = left; i <= right; i++) {
            if(i == head) {
                sb.append('[').append(cells[i]).append(']'); // Al encontrar la cabeza, la encasillara en []
                /*if(cells[i] == 'a')
                    cells[i] = '1';
                
                if(cells[i] == 'b')
                    cells[i] = '0';
                
                if(cells[i] == '1')
                    cells[i] = 'a';
                
                if(cells[i] == '0')
                    cells[i] = 'b';*/
            }else {
                sb.append(' ').append(cells[i]).append(' '); // De lo contrario lo deja igual
            }
        }
        
        return sb.toString();
    }
    
    public String fullContentTrimmed() { // Funcion que elimina los blanks creados de forma visual y solo deja el analisis de la propia cadena
        int i = 0;
        while(i < cells.length && cells[i] == blank) // Cuenta cantidad de blanks
            i++;
        
        int j = cells.length - 1;
        while(j >= 0 && cells[j] == blank)
            j--;
        
        if(i > j)
            return "";
        
        StringBuilder sb = new StringBuilder(); 
        for(int k = i; k <= j; k++) { // Vuelve a escribirlo ya si los blanks
            sb.append(cells[k]);
        }
        
        return sb.toString();
    }
    
    public int getHeadIndex() { // Devuelve posicion actual de la cabeza
        return head;
    }
}
