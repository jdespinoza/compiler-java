/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LexicalAnalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Grammar.Documento;
import Grammar.Expresion;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


/**
 *
 * @author Carol
 */
public class Controller {
    
    public String file = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\test.txt";
    
    public Controller(){
        
    }
    
    public void executeFlex (){
        File file = new File ("C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Lexer.flex");
        jflex.Main.generate(file);
        System.out.println("Lexer creado");
    }
    
    public void executeCup () {
        String pathSyntactic = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Parser.cup";
        String[] fileSyntactic = {"-parser", "Parser", pathSyntactic};
        try {
            java_cup.Main.main(fileSyntactic);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fileMove(String origen, String destino){
        Path origenPath = FileSystems.getDefault().getPath(origen);
        Path destinoPath = FileSystems.getDefault().getPath(destino);
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void run(){
        try {
        String reporte = "";
        
        Yylex lexer = new Yylex( new FileInputStream( file ) );
        Parser p = new Parser( lexer );
        p.setTable();
        p.parse();
        if( p.Exito() ) {
          reporte += "Parser sin problemas\n";
          Documento resultado = p.getDocumento();
          reporte += resultado.imprimirReporte();
          System.out.println(reporte);
          //jTextPane2.setText( reporte );
        }else{
            reporte += "Problema con el parser";
            //jTextPane2.setText( reporte );
            System.out.println(reporte);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }
    
    public void runAux(){
        try {
        String reporte = "";
        
        Yylex lexer = new Yylex( new FileInputStream( file ) );
        Parser p = new Parser( lexer );
        p.setTable();
        p.parse();
        if( p.Exito() ) {
          reporte += "Parser sin problemas\n";

        }else{
            reporte += "Problema con el parser";
            //jTextPane2.setText( reporte );
            System.out.println(reporte);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }

    public static void main(String args[]) {
        Controller c = new Controller();
        String pathParserFrom = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\Parser.java";
        String pathParserTo = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Parser.java";
        String pathSymFrom = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\sym.java";
        String pathSymTo = "C:\\Users\\Carol\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\sym.java";
        //c.executeFlex();
        //c.executeCup();
        //c.exucuteAll();
        //c.runCompi();
        //c.fileMove(pathParserFrom, pathParserTo);
        //c.fileMove(pathSymFrom, pathSymTo);
        c.run();
        //c.runAux();
        
    }
}
