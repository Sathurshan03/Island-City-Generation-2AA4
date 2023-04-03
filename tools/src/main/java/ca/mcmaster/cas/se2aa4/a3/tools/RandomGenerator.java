package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.Random;

public class RandomGenerator {
    private final long seed;
    private Random rand;
    private Random mainRandomController;
    public RandomGenerator(){
        //create a seed 
        rand = new Random();
        seed = rand.nextLong();
        mainRandomController = new Random(seed);
    }
    public RandomGenerator(long seed){
        this.seed = seed;
        mainRandomController = new Random(seed);
    }

    public double getNextdouble(){
        return mainRandomController.nextDouble();
    }

    public long getSeed(){
        return seed;
    }


    public double getNextDouble(double lowerBound, double upperBound){
        return mainRandomController.nextDouble(lowerBound, upperBound);
    }

    public int getNextInteger(int lowerBound, int upperBound){
        return mainRandomController.nextInt(lowerBound, upperBound);
    }

    public void printSeed(){
        System.out.println("The seed of this map is: " + seed);
    }
    
}
