/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rastrigin;

import java.io.IOException;

/**
 *
 * @author joaop
 */
public class Main {
    public static void main(String[] args) throws IOException {
        EvolutionalRastrigin ER = new EvolutionalRastrigin(500, 100);
        ER.runEvoAlgorithm();
        System.out.println("\n\n" + "Final Score: " + ER.functionRastrigin(ER.population[ER.bestOne]));
        
        GravarCSV k = new GravarCSV();
        k.gravarResultado(ER.Solutions);
    }
}






