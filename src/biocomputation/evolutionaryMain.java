/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */

//HERE IS A MASSIVE CHANGE I'VE MADE TO THIS FILE
package biocomputation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class evolutionaryMain {

    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

        int genCount = 0;
        int generations = 50;
        int geneLength = 50;
        int populationSize = 50;
        int bestGen = 0;
        float mutationRate = (float) 0.1;
        Population p = new Population(populationSize, geneLength);
        Individual best = new Individual(genCount);
        PrintWriter write = new PrintWriter("output.txt", "utf-8");
        write.print("Fitness" + "\t" + "Mean" + "\n");

        //randomly create new population
        p.initialise();
    
        while (genCount < generations) {
            System.out.println("Generation: " + genCount);
            
            //Tournament selection creates a new offspring arrayList
            p.selection();
            //crosses over selected individuals and places them into a new arrayList, should be added to population???
            for (int i = 0; i < p.getMaxPop() / 2; i++) {

                p.crossOver(p.getOffspring().get(i), p.getOffspring().get(p.getMaxPop() - 1 - i), (float) 0.8);
                //System.out.println("AFTER CROSS"+p.crossed.get(i).fitness);
            }

            // System.out.println("CROSSED FITNESS: " + p.countFitness(p.crossed));
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.mutation(p.getCrossed().get(i), (float) 0.01);
                //  System.out.println("AFTER MUT CROSs" + p.crossed.get(i).fitness);

            }

            p.getPopulation().clear();
            p.getOffspring().clear();
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.getPopulation().add(i, p.getCrossed().get(i));
                //  System.out.println("CROSSED" + p.crossed.get(i).fitness);
            }

            p.getCrossed().clear();
            genCount++;

            for (int i = 0; i < p.getPopulation().size(); i++) {

                if (p.getPopulation().get(i).getFitness() > best.getFitness()) {
                    best.setGenes(p.getPopulation().get(i).getGenes());
                    best.evaluateFitness();
                    bestGen = genCount;

                }

            }
            write.print(best.getFitness());
            write.append("\t");
            int totalFitness = 0;
            int fitMean = 0;

            for (Individual population : p.getPopulation()) {
                totalFitness += population.getFitness();
                //     System.out.println(population.fitness);
            }

            System.out.println("Best: " + best.getFitness());

            fitMean = (totalFitness / p.getMaxPop());
            write.print(fitMean);
            write.append("\n");

            System.out.println("Mean: " + fitMean);
            best.setFitness(0);
        }
        write.close();
        /* System.out.println("Generations: " + genCount);
        System.out.println("");
        System.out.println("Fitness: " + p.countFitness(p.population));
        System.out.println("");
        System.out.println("Best: " + best.fitness);*/
    }

}
