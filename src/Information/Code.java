/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Information;

import java_cup.Main;

/**
 *
 * @author Carol
 */
public class Code {
    public static int line;
    public static String token;
    public static void saveInformation(int l, String t){
        line = l;
        token = t;
    }
    
}
