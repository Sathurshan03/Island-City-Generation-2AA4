package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.Random;

public class RandomSeed {
    private final long seed;
    private Random rand;
    public RandomSeed(){
        //create a seed 
        rand = new Random();
        seed = rand.nextLong();
        printSeed();
    }
    public RandomSeed(long seed){
        this.seed = seed;
        remindSeed();
    }

    private void printSeed(){
        System.out.println("\nThe seed of this map is: " + seed);
    }

    private void remindSeed(){
        System.out.println("\nThe seed that was used for the map is: " + seed);
    }
    
}
