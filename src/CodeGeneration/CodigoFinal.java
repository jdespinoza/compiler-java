
package CodeGeneration;

import CodigoIntermedio.CodigoIntermedio;
import CodigoIntermedio.Cuadrupla;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CodigoFinal {
    private static String dirCodeFinal = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\CF.ens";
    private FileWriter codeFinal;
    private static String tab = "                        ";
    private Cuadrupla[] codigoIC;
    
    private boolean existeFuncion;
    private static String direccionIC = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_8\\CompiC-0\\prueba.ci";
   
    
    
    public CodigoFinal(Cuadrupla[] codigoIC) {
        existeFuncion = false;
        
        this.codigoIC = codigoIC;
    }

    
    /**
     * Procesa el codigo IC a codigo FC
     */
    public void procesarCI_CF(){
        int i;
        String op1, op2, op3, etiqueta;
        Cuadrupla n;
        
        this.abrirFicheroEscritura();
        int len = this.codigoIC.length;
        i = 0;
        this.ensInicioMain();//etiqueta de salto a inicio main
        while(i < len){
            n = codigoIC[i];
            etiqueta = n.getEtiqueta();
            op1 = n.getDir1();
            op2 = n.getDir2();
            op3 = n.getDir3();
            switch(etiqueta){
                case "FUNCION":
                    ensFuncion(op3);
                    break;
                case "MAIN":
                    ensMain();
                    break;
                case "LLAMADA":
                    ensLlamada(op3);
                    break;
                case "CARGAR_DIRECCION":
                    ensCargarDireccion(op1, op3);
                    break;
                case "CARGAR_VALOR":
                    ensCargarValor(op1, op3);
                    break;
                case "SUMAR":
                    ensSumar(op1, op2, op3);
                    break;
                case "RESTAR":
                    ensRestar(op1, op2, op3);
                    break;
                case "MULTIPLICAR":
                    ensMultiplicar(op1, op2, op3);
                    break;
                case "DIVIDIR":
                    ensDividir(op1, op2, op3);
                    break;
                case "OR":
                    ensOr(op1, op2, op3);
                    break;
                case "AND":
                    ensAnd(op1, op2, op3);
                    break;
                case "MAYOR":
                    ensMayor(op1, op2, op3);
                    break;
                case "MENOR":
                    ensMenor(op1, op2, op3);
                    break;
                case "IGUAL":
                    ensIgual(op1, op2, op3);
                    break;
                case "DISTINTO":
                    ensDistinto(op1, op2, op3);
                    break;
                case "SALTAR_CONDICION":
                    ensSaltarCondicion(op1, op3);
                    break;
                case "ETIQUETA":
                    ensEtiqueta(op3);
                    break;
                case "SALTAR_ETIQUETA":
                   ensSaltarEtiqueta(op3);
                    break;
                case "IMPRIMIR_ENTERO":
                    ensPUTW(op1);
                    break;
                case "IMPRIMIR_CADENA":
                    ensPUTS(op1);
                    break;
                case "PONER_CADENA":
                    ensPonerCadena(op1, op3);
                     break;
                case "FIN":
                    ensFin();
                    break;
                default:
                    System.out.println("Etiqueta mal escrita o no espicificada: "+(i+1)+" "+etiqueta);
                        break;
                }
                i++;
            }
            cerrarFicheroEscritura();
    }
    
    /**
     * Crear archivo codigoFinal.ens
     */
    private void abrirFicheroEscritura(){
        try {
            this.codeFinal = new FileWriter(dirCodeFinal);
	} catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	}
    }
    
    private void escribirFichero(String str){
        try {
            this.codeFinal.write(str+"\n");
        } catch (IOException ex) {
            Logger.getLogger(CodigoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cerrarFicheroEscritura(){
        try{
            codeFinal.close();
        }catch(Exception ex){
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }    
    
    /**
     * Se escribe una liena al final del archivo
     */
    public void addLineaArchivo(String linea){
        escribirFichero(linea);
    }
    
    public void ensSumar(String op1, String op2, String op3){
        addLineaArchivo(tab+"ADD /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }
    
    public void ensRestar(String op1, String op2, String op3){
        addLineaArchivo(tab+"SUB /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }
    
    public void ensMultiplicar(String op1, String op2, String op3){
        addLineaArchivo(tab+"MUL /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }
    
    public void ensDividir(String op1, String op2, String op3){
        addLineaArchivo(tab+"DIV /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }

    public void ensAnd(String op1, String op2, String op3){
        addLineaArchivo(tab+"AND /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }
    
    public void ensOr(String op1, String op2, String op3){
        addLineaArchivo(tab+"OR /"+op1+" , /"+op2);
        addLineaArchivo(tab+"MOVE .A , /"+op3);
    }
    
    //Menor op1 op2 op3 --> x < y
    public void ensMenor(String op1, String op2, String op3){
        addLineaArchivo(tab+"CMP /"+op1+" , /"+op2);
        addLineaArchivo(tab+"BN $5");
        addLineaArchivo(tab+"MOVE #0 , /"+op3);
        addLineaArchivo(tab+"BR $3");
        addLineaArchivo(tab+"MOVE #1 , /"+op3);
    }
    
    //Menor op1 op2 op3 --> x > y
    public void ensMayor(String op1, String op2, String op3){
        addLineaArchivo(tab+"CMP /"+op2+" , /"+op1);
        addLineaArchivo(tab+"BN $5");
        addLineaArchivo(tab+"MOVE #0 , /"+op3);
        addLineaArchivo(tab+"BR $3");
        addLineaArchivo(tab+"MOVE #1 , /"+op3);
    }

    
    //Igual op1 op2 op3 --> ==
    public void ensIgual(String op1, String op2, String op3){
        addLineaArchivo(tab+"CMP /"+op1+" , /"+op2);
        addLineaArchivo(tab+"BZ $5");
        addLineaArchivo(tab+"MOVE #0 , /"+op3);
        addLineaArchivo(tab+"BR $3");
        addLineaArchivo(tab+"MOVE #1 , /"+op3);
    }
    
    //Distinto op1 op2 op3 --> ¡=
    public void ensDistinto(String op1, String op2, String op3){
        addLineaArchivo(tab+"CMP /"+op1+" , /"+op2);
        addLineaArchivo(tab+"BZ $5");
        addLineaArchivo(tab+"MOVE #1 , /"+op3);
        addLineaArchivo(tab+"BR $3");
        addLineaArchivo(tab+"MOVE #0 , /"+op3);
    }
    
    /** PUTW op1 null null
     *  imprimir entero
     */
    public void ensPUTW(String op1){
        addLineaArchivo(tab+"WRINT /"+op1);
    }
    
    /**PUTW op1 null null
     * imprimir string -> cadena de caracteres
     */
    public void ensPUTS(String op1){
        addLineaArchivo(tab+"WRSTR /"+op1);
    }
     
    /** CARGAR_VALOR op1 null op3
     * Asigna valor numerico a una direccion
     */
    public void ensCargarValor(String op1, String op3){
        addLineaArchivo(tab+"MOVE #"+op1+" , /"+op3);
    }
    
    /** CARGAR_DIRECCION op1 null op3
     * asigna el valor de la priemra direccion a la segunda
     */
    public void ensCargarDireccion(String op1, String op3){
        addLineaArchivo(tab+"MOVE /"+op1+" , /"+op3);
    }
    
    /** ETIQUETA NULL NULL res
     * 
     */
    public void ensEtiqueta(String op3){
        String et = op3+":"+tab;
        addLineaArchivo(et.substring(0, tab.length())+"NOP");
    }

    /**SALTAR_CONDICION op1 null res
     * 
     */
    public void ensSaltarCondicion(String op1, String op3){
        addLineaArchivo(tab+"CMP #0 , /"+op1);
        addLineaArchivo(tab+"BZ /"+op3);
    }
    
    /**SALTAR_ETIQUETA null null res
     * 
     */
    public void ensSaltarEtiqueta(String op3){
        addLineaArchivo(tab+"BR /"+op3);
    }
    
    /**PONER CADENA op1 null op3
     * 
     */
    public void ensPonerCadena(String op1, String op3){
        String cad = op1+": DATA"+tab;
        addLineaArchivo(cad.substring(0,tab.length())+op3);
    }
    
    /**FIN null null null
     * 
     */
    public void ensFin(){
        addLineaArchivo(tab+"HALT");
    }

    /**SALTO_MAIN
     */
    public void ensInicioMain(){
        addLineaArchivo(tab+"BR /MAIN");
    }
    
    /**FUNCION
     * Decaracion function
     */
    public void ensFuncion(String op3){
        System.out.println("-------------"+op3);
        if(existeFuncion){
            addLineaArchivo(tab+"RET");
            String et = op3+":"+tab;
            addLineaArchivo(et.substring(0, tab.length())+"NOP");
            existeFuncion=false;
        }
        else{
            String et = op3+":"+tab;
            addLineaArchivo(et.substring(0, tab.length())+"NOP");
            existeFuncion = true;
        }
    }
    
    /**MAIN null null null
     * Etiqueta Main
     */
    public void ensMain(){
        if(existeFuncion){
            addLineaArchivo(tab+"RET");
            String et = "MAIN:"+tab;
            addLineaArchivo(et.substring(0, tab.length())+"NOP");
            existeFuncion=false;
        }
        else{
            addLineaArchivo(tab+"RET");
            String et = "MAIN:"+tab;
            addLineaArchivo(et.substring(0, tab.length())+"NOP");
            existeFuncion=false;
        }
    }
    
    /**LLAMADA null null etiqueta
     */
    public void ensLlamada(String op3){
        addLineaArchivo(tab+"CALL /"+op3);
    }
    
}
