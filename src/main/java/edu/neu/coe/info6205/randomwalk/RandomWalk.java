/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();
    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        this.x=x+dx;
        this.y=y+dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        //We are utilizing randomMove function which already moves the coordinates
        for (int i = 0; i < m; i++) {
            randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        //As the distance is asked from the origin we are not explicitly mentioning the origin coordinates
        return Math.pow((Math.pow(x,2))+(Math.pow(y,2)),0.5);
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

//    public static void main(String[] args) {
//        if (args.length == 0){
//            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
//        }
//        //m is the number of steps or moves that can be made in any given( here will be chosen randomly) direction.
//        int m = Integer.parseInt(args[0]);
//        //n is the number of experiments or trials that function will be run.
//        // this will be the default value for number of experiments if we do not give the input to args
//        int n = 30;
//
//        if (args.length > 1) {
//            n = Integer.parseInt(args[1]);
//        }
//        double meanDistance = randomWalkMulti(m, n);
//        System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
//    }

    //     This commented main method will be used for testing custom input code
    public static void main(String[] args) {
        int numberOfTrials = 143;
        for (int steps = 0; steps < 65; steps++) {
                double meanDistance = randomWalkMulti(steps, numberOfTrials);
                System.out.println(steps + " steps: " + meanDistance + " over " + numberOfTrials + " experiments");
        }
    }

}
