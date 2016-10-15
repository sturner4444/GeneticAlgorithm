/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sturner
 */
public class Population {

    private ArrayList<Individual> population = new ArrayList();
    private ArrayList<Individual> offspring = new ArrayList();
    private ArrayList<Individual> crossed = new ArrayList();
    private int chromeLen;
    private int maxPop;
    private Random r = new Random();
//    /int counter = 0;

    //constructor
    public Population(int maxPop, int chromeLen) {
        this.chromeLen = chromeLen;
        this.maxPop = maxPop;

    }

    public ArrayList<Individual> getCrossed() {
        return crossed;
    }

    public int getMaxPop() {
        return maxPop;
    }

    public Random getR() {
        return r;
    }
    
    

    //create population
    public void initialise() {

        for (int i = 0; i < maxPop; i++) {

            Individual e = new Individual(chromeLen);

            e.initGenes();

            e.evaluateFitness();
            population.add(e);
            //System.out.println("NEW GENE FITNESS: " + population.get(i).fitness);
        }
    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }

    public int popFitness() {
        int totalFitness = 0;
        for (int i = 0; i < population.size(); i++) {

            totalFitness += population.get(i).getFitness();

        }
        return totalFitness;
    }

    public int countFitness(ArrayList<Individual> list) {
        int totalFitness = 0;
        for (int i = 0; i < list.size(); i++) {

            totalFitness += list.get(i).getFitness();

        }
        return totalFitness;
    }

    public ArrayList<Individual> getOffspring() {
        return offspring;
    }

    public int getChromeLen() {
        return chromeLen;
    }

    //Takes in two individials and creates two new indiviuals by switching tails (new individuals enter a new list)
    public void crossOver(Individual x, Individual y, float xoverProb) {
        float xoverRate = xoverProb;
        int[] genes = new int[chromeLen];
        //empty individuals that will conain outcome of xover
        Individual newIndividualOne = new Individual(chromeLen);
        Individual newIndividualTwo = new Individual(chromeLen);

        int[] newArrayOne = new int[chromeLen];
        int[] newArrayTwo = new int[chromeLen];
        int crossPoint = r.nextInt(chromeLen);
        float prob = r.nextFloat();

        if (prob < xoverRate) {

            for (int i = 0; i < chromeLen; i++) {

                int temp = 0;
                //copy values up to crossover point

                if (i < crossPoint) {
                    temp = x.getGenes()[i];
                    newIndividualOne.getGenes()[i] = temp;
                    temp = y.getGenes()[i];
                    newIndividualTwo.getGenes()[i] = temp;
                    //copy values from crossover point to length of gene into new individual x to y and y to x
                } else {
                    temp = x.getGenes()[i];
                    newIndividualTwo.getGenes()[i] = temp;
                    temp = y.getGenes()[i];
                    newIndividualOne.getGenes()[i] = temp;

                }

            }
            crossed.add(newIndividualOne);
            crossed.add(newIndividualTwo);
            newIndividualOne.evaluateFitness();
            newIndividualTwo.evaluateFitness();

        } else {

            for (int i = 0; i < chromeLen; i++) {
                int temp = 0;
                temp = x.getGenes()[i];
                newIndividualOne.getGenes()[i] = temp;
                temp = y.getGenes()[i];
                newIndividualTwo.getGenes()[i] = temp;

            }
            crossed.add(newIndividualOne);
            crossed.add(newIndividualTwo);
            newIndividualOne.evaluateFitness();
            newIndividualTwo.evaluateFitness();

        }

        // System.out.println("PASSED VALUE " + x.fitness);
        // System.out.println("NEW VALUE " + newIndividualOne.fitness);
        // System.out.println("END SIZE : " + crossed.size());
    }

    public void selection() {
        int geneInit[] = new int[population.get(1).getChromeLen()];
        Random r = new Random();
        Individual evalOne = new Individual(0);
        Individual evalTwo = new Individual(0);
        for (int i = 0; i < population.size(); i++) {

            evalOne = population.get(r.nextInt(population.size()));
            evalTwo = population.get(r.nextInt(population.size()));
            //Adds selected individs to offspring TESTED WORKING
            if (evalOne.getFitness() > evalTwo.getFitness()) {
                offspring.add(evalOne);
                //displays the values in the Population and then in Offspring
                // System.out.println("EVAL ONE " + evalOne.fitness);
                //System.out.println("Offspring " + i +" "+ offspring.get(i).fitness);

            } else {
                offspring.add(evalTwo);

            }
        }

    }

    public void mutation(Individual ind, float muteRate) {
        float mutationRate = muteRate;

        for (int i = 0; i < ind.getChromeLen(); i++) {
            float prob = r.nextFloat();
            if (prob < mutationRate && ind.getGenes()[i] == 1) {
                ind.getGenes()[i] = 0;

            } else if (prob < mutationRate && ind.getGenes()[i] == 0) {
                ind.getGenes()[i] = 1;

            }

        }

    }
}
