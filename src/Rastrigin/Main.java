/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rastrigin;

/**
 *
 * @author joaop
 */

public class Main {
    public static void main(String[] args) {
        EvolutionalRastrigin ER = new EvolutionalRastrigin(500, 10);
        ER.runEvoAlgorithm();
        
        System.out.println("Final Score: " + ER.getBestScore());
    }
}






