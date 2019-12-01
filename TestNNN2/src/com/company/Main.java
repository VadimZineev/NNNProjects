package com.company;

public class Main {

    private static double random(double minRandom, double maxRandom) {
        return Math.random() * maxRandom + minRandom;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static void main(String[] args) {
        //Parameters
        double[] expected = {0, 1, 0, 0, 1, 1, 0, 0};

        double[][] startsX = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1},
                {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        double[] weightsFromStartsX = new double[6];
        double[] hiddenNeurons = new double[2];
        double[] weightsFromHiddenNeurons = new double[2];
        double outputNeuron = 0;
        double actual;
        double learningRate = 0.1;
        double sigmoidX;
        double weightsDelta;
        double error;
        int epoch = 0;

        //Step 1: Loading weights
        for (int i = 0; i < weightsFromStartsX.length; i++) {
            weightsFromStartsX[i] = random(0, 1);
        }
        for (int i = 0; i < weightsFromHiddenNeurons.length; i++) {
            weightsFromHiddenNeurons[i] = random(0, 1);
        }
        //Loading is end

        //Step 2:Loading hidden neurons
        hiddenNeurons[0] = weightsFromStartsX[0] * startsX[0][0] + weightsFromStartsX[1] * startsX[0][1] +
                weightsFromStartsX[2];
        hiddenNeurons[1] = weightsFromStartsX[3] * startsX[0][1] + weightsFromStartsX[4] * startsX[0][1] +
                weightsFromStartsX[5];
        hiddenNeurons[0] = sigmoid(hiddenNeurons[0]);
        hiddenNeurons[1] = sigmoid(hiddenNeurons[1]);
        //Loading is end

        //Step 3: Loading output neuron
        for (int i = 0; i < 2; i++) {
            outputNeuron += hiddenNeurons[i] * weightsFromHiddenNeurons[i];
        }
        actual = sigmoid(outputNeuron);
        //Loading is end

        //Step 4: Learning NN
        while (true) {
            if (actual != expected[0]) {
                ++epoch;
                error = actual - expected[0];
                sigmoidX = actual * (1 - actual);
                weightsDelta = error * sigmoidX;
                for (int i = 0; i < 2; i++) {
                    weightsFromHiddenNeurons[i] -= hiddenNeurons[i] * weightsDelta * learningRate;
                }
                for (int i = 0; i < 2; i++) {
                    error = weightsFromHiddenNeurons[0] * weightsDelta;
                    sigmoidX = actual * (1 - actual);
                    weightsDelta = error * sigmoidX;
                }
                for (int j = 0; j < 2; j++) {
                    hiddenNeurons[j] -= weightsFromHiddenNeurons[j] * weightsDelta * learningRate;
                    outputNeuron += hiddenNeurons[j] + weightsFromHiddenNeurons[j];
                }
                actual = sigmoid(outputNeuron);
                System.out.println(actual);
            } else if (actual == expected[0]) {
                System.out.println("Nice!" + " | " + actual);
                break;
            }
        }
    }
}
