/*
 * -------------------------------------------
 * | Developed by: João Pedro Molina Peinado |
 * |    Email: a15030@bcc.unifal-mg.edu.br   |
 * -------------------------------------------
 */

package Rastrigin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author joaopeinado
 */
public class GravarCSV {
    
    public static void gravarResultado(float[] result) throws IOException {
        
        FileWriter arq = new FileWriter("saida5.csv");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        gravarArq.printf("Saida%n");
        
        for (int i = 1; i < result.length; i++) {
            
            gravarArq.printf("%s%n",result[i]);   
        }     
        arq.close();
    }
}
