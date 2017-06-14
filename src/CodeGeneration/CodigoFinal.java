
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
    private static String dirCodeFinal = "C:\\Users\\Wifuss\\Documents\\Jason\\C-0_6\\CompiC-0\\CF.ens";
    private FileWriter codeFinal;
    private static String tab = "                        ";
    private ArrayList<Cuadrupla> codigoIC;
    
    public CodigoFinal() {

    }

    public CodigoFinal(ArrayList<Cuadrupla> codigoEns) {
        this.codigoIC = codigoEns;
    }

    public void setCodigoEns(ArrayList<Cuadrupla> codigoEns) {
        this.codigoIC = codigoEns;
    }
    
    /**
     * Procesa el codigo IC a codigo FC
     */
    public void procesarICtoFC(){
        int i;
        String op1, op2, op3, etiqueta;
        Cuadrupla n;
        
        this.abrirFicheroEscritura();
        
            i = 0;
            while(i < codigoIC.size()){
                n = codigoIC.get(i);
                etiqueta = n.getEtiqueta();
                op1 = n.getDir1();
                op2 = n.getDir2();
                op3 = n.getDir3();
                //System.out.println((i+1)+" "+etiqueta+" "+op1+" "+op2+" "+op3);
                switch(etiqueta){
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
    public void abrirFicheroEscritura(){
        try {
            this.codeFinal = new FileWriter(dirCodeFinal);
	} catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	}
    }
    
    public void escribirFichero(String str){
        try {
            this.codeFinal.write(str+"\n");
        } catch (IOException ex) {
            Logger.getLogger(CodigoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarFicheroEscritura(){
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
    
    
    public static void main(String args[]) throws FileNotFoundException, IOException {

        CodigoIntermedio ci = new CodigoIntermedio();
        
        FileReader fr = new FileReader("C:\\Users\\Wifuss\\Documents\\Jason\\C-0_6\\CompiC-0\\prueba.ci");
        BufferedReader bf = new BufferedReader(fr);
        
        String cadena = null;
        int poss = 0;
        String str3;
        cadena = bf.readLine();
        while (cadena != null) {
            System.out.println(cadena);
            String split[];    
                
            split = cadena.split(" ");
            //System.out.println(split[0]+" "+split[1]+" "+split[2]+" "+split[3]);
            if(split[3].charAt(0)==34){
                str3="";
                int i = 3;
                while( i < split.length-1 ){
                    str3+=split[i]+" ";
                    i++;
                }
                str3+=split[i];
            }
            else{
                str3=split[3];
            }
            
            ci.guardarCuadrupla(new Cuadrupla(split[0], split[1], split[2], str3));
            //System.out.println(poss+" "+ci.getElementoCI(poss).toString());
            poss++;
            cadena = bf.readLine();
        }
        try {
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(CodigoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CodigoFinal cod = new CodigoFinal();
        cod.setCodigoEns(ci.getCI());
        cod.procesarICtoFC();
    }
    
}
