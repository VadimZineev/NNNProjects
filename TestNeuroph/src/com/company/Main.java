package com.company;

public class Main {
    public static double random(double minRandom, double maxRandom) {
        double randomNumber = Math.random() * maxRandom + minRandom;
        return randomNumber;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(x));
    }

    public static void main(String[] args) {
        //Parameters
        double[] expected = {0, 1, 0, 0, 1, 1, 0, 0};
        double actual = 0;
        double sigmoidX;
        double error;
        double weightsDelta;
        double learningRate = 0.1;
        int epoch = 0;
        boolean startLearn = true;

        double[][] startsX = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1},
                {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        double[] weightsFromX1 = new double[2];
        double[] weightsFromX2 = new double[2];
        double[] weightsFromX3 = new double[2];
        double[] hiddenNeuron = new double[2];
        double[] weightsHiddenNeurons = new double[2];
        double outputNeuron = 0;
        for (int i = 0; i < 2; i++) {
            weightsFromX1[i] = random(0.1, 0.99);
            weightsFromX2[i] = random(0.1, 0.99);
            weightsFromX3[i] = random(0.1, 0.99);
            weightsHiddenNeurons[i] = random(0.1, 0.99);
        }
        for (int x = 0; x < expected.length; x++) {
            for (int i = 0; i < 2; i++) {
                hiddenNeuron[i] = weightsFromX1[i] * startsX[x][0] + weightsFromX2[i] * startsX[x][1]
                        + weightsFromX3[i] * startsX[x][2];
                outputNeuron += sigmoid(hiddenNeuron[i]) * weightsHiddenNeurons[i];
            }
            actual = sigmoid(outputNeuron);
            if (actual == expected[x]) {
                System.out.println(x + 1 + ") " + "Epoch: " + epoch + " | " + actual);
            } else {
                while (startLearn) {
                    if (actual == expected[x]) {
                        System.out.println(x + 1 + ") " + "Epoch: " + epoch + " | " + actual);
                        startLearn = false;
                    } else {
                        error = actual - expected[x];
                        sigmoidX = actual * (1 - actual);
                        weightsDelta = error * sigmoidX;
                        for (int i = 0; i < 2; i++) {
                            weightsHiddenNeurons[i] = weightsHiddenNeurons[i] -
                                    - hiddenNeuron[i] * weightsDelta * learningRate;
                            outputNeuron += sigmoid(hiddenNeuron[i]) + weightsHiddenNeurons[i];
                        }
                        actual = sigmoid(outputNeuron);
                        ++epoch;
                        //System.out.println(x + ") " + "Epoch: " + epoch + " | " + actual);
                    }
                }
            }
        }
    }
}
