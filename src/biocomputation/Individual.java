/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import;
import java.util.Random;

/**
 *
 * @author sturner
 */
public class Individual {

    Import math;
    private int chromeLen;
    private int[] genes = new int[0];
    private int fitness;

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public int getChromeLen() {
        return chromeLen;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public Individual(int chromeLen) {
        this.chromeLen = chromeLen;
        this.genes = new int[chromeLen];
    }

    public void evaluateFitness() {
        this.fitness = 0;
        for (int i = 0; i < this.genes.length; i++) {
            if (this.genes[i] == 1) {

                this.fitness++;
            }
        }

    }

    public void initGenes() {

        for (int i = 0; i < chromeLen; i++) {
            Random r = new Random();
            this.genes[i] = r.nextInt(2);

        }

    }

}
