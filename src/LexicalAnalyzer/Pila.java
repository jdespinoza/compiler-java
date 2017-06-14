/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carol
 */
public class Pila {
    
    public List pila;
    private int top;
    
    public Pila(){
        pila = new ArrayList<>();
        top = 0;
    }
    
    public void push(int n){
        pila.add(n);
        top++;
    }
    
    public int pop(){
        int n = (int) pila.get(top-1);
        pila.remove(top-1);
        top--;
        return n;
    }
    
    public int getTop(){
        return (int) pila.get(top-1);
    }
    
}
