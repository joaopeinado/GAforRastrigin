/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rastrigin;

import java.util.Random;

/**
 *
 * @author joaop
 */
public class EvolutionalRastrigin {

    public float[][] population;
    public float[] childOne;
    public float[] childTwo;
    public int bestOne = 0, worstTwo = 0, worstOne = 0;
    private final int sizePopulation, sizeFamily;
    public float[] Solutions;
    
    public EvolutionalRastrigin(int x, int y) {
        this.sizePopulation = x;
        this.sizeFamily = y;
    }
    
    public void runEvoAlgorithm() {
        Random rnd = new Random();
        int option;
        int k = 0;
        this.createPopulation();
        this.findBestAndWorsts();
        while (k < 1000000 && this.functionRastrigin(this.population[this.bestOne]) > 0) {
            option = rnd.nextInt(2); // 0 - Crossover  ##  1 - Mutation
            if (option == 0) {
                this.Crossover(rnd.nextInt(this.sizePopulation), rnd.nextInt(this.sizePopulation));
                this.ReplacementTwo(this.worstOne, this.worstTwo);
            } else {
                this.Mutation(rnd.nextInt(this.sizePopulation));
                this.Replacement(this.worstOne);
            }
            this.findBestAndWorsts();
            this.Solutions[k] = this.functionRastrigin(this.population[this.bestOne]);
            k++;
            //if (k == 100000) System.out.println("A");
            //System.out.println(this.functionRastrigin(this.population[this.bestOne]) + " " + this.functionRastrigin(this.population[this.worstOne]));
        }
    }

    public void Replacement(int position) {
        System.arraycopy(this.childOne, 0, this.population[position], 0, this.sizeFamily);
    }
    
    public void ReplacementTwo(int position1, int position2){
        System.arraycopy(this.childOne, 0, this.population[position1], 0, this.sizeFamily);
        System.arraycopy(this.childTwo, 0, this.population[position2], 0, this.sizeFamily);
    }

    public void findBestAndWorsts() {
        float bestScore = (float) 1000000;
        float worstScore = (float) -1;
        float current;
        for (int i = 0; i < this.sizePopulation; i++) {
            current = this.functionRastrigin(this.population[i]);
            if (current < bestScore) {
                bestScore = current;
                this.bestOne = i;
            } else if (current > worstScore) {
                worstScore = current;
                this.worstTwo = this.worstOne;
                this.worstOne = i;
            }
        }
    }

    public void Mutation(int parent) {
        Random rnd = new Random();
        int option = rnd.nextInt(2); // 0 - Uniform Mutation  ##  1 - Nonuniform Mutation with Gaussian Distribuition
        if (option == 0) {
            for (int j = 0; j < this.sizeFamily; j++) {
                this.childOne[j] = ((rnd.nextFloat() * 10) - 5);
            }
        } else {
            for (int j = 0; j < this.sizeFamily; j++) {
                this.childOne[j] = (this.population[parent][j] + ((rnd.nextFloat() * 2) - 1));
                if (this.childOne[j] > 5) {
                    this.childOne[j] = 5;
                } else if (this.childOne[j] < -5) {
                    this.childOne[j] = -5;
                }
            }
        }
    }

    public void Crossover(int parent1, int parent2) {
        Random rnd = new Random();
        int option = rnd.nextInt(3); // 0 - One-Point Crossover  ##  1 - Uniform Crossover  ##  2 - Whole Arithmetic Recombination        
        switch (option) {
            case 0:
                int pos = (rnd.nextInt(this.sizeFamily-2) + 1);
                System.arraycopy(this.population[parent1], 0, this.childOne, 0, pos);
                System.arraycopy(this.population[parent2], 0, this.childTwo, 0, pos);
                System.arraycopy(this.population[parent2], pos, this.childOne, pos, this.sizeFamily - pos);
                System.arraycopy(this.population[parent1], pos, this.childTwo, pos, this.sizeFamily - pos);
                break;
            case 1:
                for (int i = 0; i < this.sizeFamily; i = i + 2) {
                    this.childOne[i] = this.population[parent1][i];
                    this.childTwo[i] = this.population[parent2][i];
                    if ((i+1) < this.sizeFamily) this.childOne[i + 1] = this.population[parent2][i + 1];
                    if ((i+1) < this.sizeFamily) this.childTwo[i + 1] = this.population[parent1][i + 1];
                }
                break;
            default:
                float alpha = rnd.nextFloat();
                for (int i = 0; i < this.sizeFamily; i++) {
                    this.childOne[i] = (this.population[parent1][i] * alpha) + (this.population[parent2][i] * (1-alpha));
                    this.childTwo[i] = (this.population[parent2][i] * alpha) + (this.population[parent1][i] * (1-alpha));
                }
                break;
        }
    }

    public float functionRastrigin(float v[]) {
        float result = 0;
        for (int i = 0; i < this.sizeFamily; i++) {
            result += (Math.pow(v[i], 2) - 10 * (Math.cos(2 * Math.PI * v[i])));
        }
        return ((10 * this.sizeFamily) + result);
    }

    public void createPopulation() {
        this.population = new float[this.sizePopulation][this.sizeFamily];
        this.childOne = new float[this.sizeFamily];
        this.childTwo = new float[this.sizeFamily];
        this.Solutions = new float[1000000];
        Random rnd = new Random();
        for (int i = 0; i < this.sizePopulation; i++) {
            for (int j = 0; j < this.sizeFamily; j++) {
                this.population[i][j] = ((rnd.nextFloat() * 10) - 5);
            }
        }
    }
}
