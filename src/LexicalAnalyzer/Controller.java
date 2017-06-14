
package LexicalAnalyzer;

//import LexicalAnalyzer.Parser;
//import LexicalAnalyzer.Yylex;
import UI.CodeToUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


/**
 *
 * @author Carol
 */
public class Controller {
    
    public String file = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\test.txt";
    
    public Controller(){
        
    }
    
    public void executeFlex (){
        File file = new File ("C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\src\\LexicalAnalyzer\\Lexer.flex");
        jflex.Main.generate(file);
        System.out.println("Lexer creado");
    }
    
    public void executeCup () {
        String pathSyntactic = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\src\\LexicalAnalyzer\\Parser.cup";
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
        CodeToUI codigoStr = new CodeToUI();
        String resLexer = "",resTable="";
        Yylex lexer = new Yylex( new FileInputStream( file ) );
        Parser p = new Parser( lexer );
        p.init();
        p.parse();
            System.out.println(lexer.getListLexer().size());
        codigoStr.generaListLexemas(lexer.getListLexer());
        resLexer = codigoStr.getListLexemas();        
        
        codigoStr.generarTablaSimbolos((ArrayList)p.getTable().getIntList(), (ArrayList)p.getTable().getFunciones());
        resTable = codigoStr.getTablaSimbolos();
            System.out.println("Finalizo \n"+resLexer);
            System.out.println(resTable);
            
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }

    public static void main(String args[]) {
        Controller c = new Controller();
        String pathParserFrom = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\Parser.java";
        String pathParserTo = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\src\\LexicalAnalyzer\\Parser.java";
        String pathSymFrom = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\sym.java";
        String pathSymTo = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\src\\LexicalAnalyzer\\sym.java";
        //c.executeFlex();
        //c.executeCup();
        //c.fileMove(pathParserFrom, pathParserTo);
        //c.fileMove(pathSymFrom, pathSymTo);
        c.run();
       
    }
}
