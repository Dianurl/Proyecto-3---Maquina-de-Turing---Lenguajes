/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto3lenguajes;

/**
 *
 * @author DELL
 */
public class MTReg { // Expresiones regex de todos los lenguajes a utilizar
    public static MT EndWithABB() {
        return new MT("(?:[ab])*abb");
    }
    
    public static MT ZeroOne() {
        return new MT("0*1*");
    }
    
    public static MT AB() {
        return new MT("(?:ab)*");
    }
    
    public static MT EndWithZero() {
        return new MT("1(?:01)*0");
    }
    
    public static MT ContainsA() {
        return new MT("(?:[ab])*a(?:[ab])*");
    }
}
