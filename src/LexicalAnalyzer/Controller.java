package LexicalAnalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Grammar.Document;
import Grammar.Expression;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;



public class Controller {
    public String file = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\test.txt";
    
    public Controller(){
        
    }
    
    public void executeFlex (){
        File file = new File ("C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Lexer.flex");
        jflex.Main.generate(file);
        System.out.println("Lexer creado");
    }
    
    public void executeCup () {
        String pathSyntactic = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Parser.cup";
        String[] fileSyntactic = {"-parser", "Parser", pathSyntactic};
        try {
            java_cup.Main.main(fileSyntactic);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fileMove(String origin, String destination){
        Path originPath = FileSystems.getDefault().getPath(origin);
        Path destinationPath = FileSystems.getDefault().getPath(destination);
        try {
            Files.move(originPath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void run(){
        try {
        String report = "";
        
        Yylex lexer = new Yylex( new FileInputStream( file ) );
        Parser p = new Parser( lexer );
        p.setTable();
        p.parse();
        if( p.getSuccess() ) {
          report += "Parser sin problemas\n";
          Document result = p.getDocument();
          report += result.printReport();
          
          System.out.println(lexer.printListLexer());
          System.out.println(p.table.printSymbolTable());
          System.out.println(report);
          
        }else{
            report += "Problema con el parser";
            
            System.out.println(report);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }
    /*
    public void runAux(){
        try {
        String report = "";
        
        Yylex lexer = new Yylex( new FileInputStream( file ) );
        Parser p = new Parser( lexer );
        p.setTable();
        p.parse();
        if( p.getSuccess() ) {
          report += "Parser sin problemas\n";

        }else{
            report += "Problema con el parser";
            //jTextPane2.setText( report );
            System.out.println(report);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }*/

    public static void main(String args[]) {
        Controller c = new Controller();
        String pathParserFrom = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\Parser.java";
        String pathParserTo = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\Parser.java";
        String pathSymFrom = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\sym.java";
        String pathSymTo = "C:\\Users\\Wifuss\\Documents\\NetBeansProjects\\C-0\\src\\LexicalAnalyzer\\sym.java";
        //c.executeFlex();
        //c.executeCup();
        
        //c.fileMove(pathParserFrom, pathParserTo);
        //c.fileMove(pathSymFrom, pathSymTo);
        c.run();

    }
}
