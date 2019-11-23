package com.company;

public class Main {
    public static double random(double minRandom, double maxRandom) {
        double randomNumber = Math.random() * maxRandom + minRandom;
        return randomNumber;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.pow(Math.exp(-x), -x));
    }

    public static void main(String[] args) {
        //Parameters
        double[] expected = {0, 1, 0, 0, 1, 1, 0, 0};
        double[][] threeStartNeuronsValue = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1},
                {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        double actual;
        double learningRate = 0.14;
        double sigmoidX;
        int epoch = 0;
        double wightsDeltaOutputNeuron;
        double[] wightsDeltaHiddenNeurons;
        double[] wightsDeltaInputNeurons1;
        double[] wightsDeltaInputNeurons2;
        double[] wightsDeltaInputNeurons3;

        //Neurons
        double[] inputNeurons = new double[3];
        double[] hiddenNeurons = new double[2];
        double outputNeuron = 0;
        //Weights
        double[] weightsBetweenInpAndHidNs1 = new double[2];
        double[] weightsBetweenInpAndHidNs2 = new double[2];
        double[] weightsBetweenInpAndHidNs3 = new double[2];
        double[] weightsBetweenHidAndOutNs = new double[2];
        //Errors
        double[] errorsWeightsBetweenInpAndHidNs1 = new double[2];
        double[] errorsWeightsBetweenInpAndHidNs2 = new double[2];
        double[] errorsWeightsBetweenInpAndHidNs3 = new double[2];
        double[] errorsWeightsBetweenHidAndOutNs = new double[2];
        double errorOutputNeuron;

        for (int i = 0; i < inputNeurons.length; i++) {
            inputNeurons[i] = threeStartNeuronsValue[0][i];
        }
        for (int i = 0; i < weightsBetweenInpAndHidNs1.length; i++) {
            weightsBetweenInpAndHidNs1[i] = random(0, 1);
            weightsBetweenInpAndHidNs2[i] = random(0, 1);
            weightsBetweenInpAndHidNs3[i] = random(0, 1);
            weightsBetweenHidAndOutNs[i] = random(0, 1);
        }
        //Value for Hidden Neurons
        for (int i = 0; i < hiddenNeurons.length; i++) {
            hiddenNeurons[i] = inputNeurons[0] * weightsBetweenInpAndHidNs1[i] +
                    inputNeurons[1] * weightsBetweenInpAndHidNs2[i] + inputNeurons[2] * weightsBetweenInpAndHidNs3[i];
            hiddenNeurons[i] = sigmoid(hiddenNeurons[i]);
            outputNeuron += hiddenNeurons[i] * weightsBetweenHidAndOutNs[i];
        }
        actual = sigmoid(outputNeuron);
        System.out.println("До: " + actual);
        errorOutputNeuron = expected[0] - actual;
        sigmoidX = actual * (1 - actual);
        wightsDeltaOutputNeuron = errorOutputNeuron * sigmoidX;

        //Errors 5 and 6 neurons

        for (int i = 0; i < 2; i++) {
            errorsWeightsBetweenHidAndOutNs[i] = errorOutputNeuron * weightsBetweenHidAndOutNs[i];
            errorsWeightsBetweenInpAndHidNs1[i] += weightsBetweenInpAndHidNs1[i] * errorsWeightsBetweenHidAndOutNs[i];
            errorsWeightsBetweenInpAndHidNs2[i] += weightsBetweenInpAndHidNs2[i] * errorsWeightsBetweenHidAndOutNs[i];
            errorsWeightsBetweenInpAndHidNs3[i] += weightsBetweenInpAndHidNs3[i] * errorsWeightsBetweenHidAndOutNs[i];
        }
        for (int i = 0; i < 2; i++) {
            weightsBetweenInpAndHidNs1[i] = weightsBetweenInpAndHidNs1[i] + learningRate * errorsWeightsBetweenInpAndHidNs1[i];
            weightsBetweenInpAndHidNs2[i] = weightsBetweenInpAndHidNs2[i] + learningRate * errorsWeightsBetweenInpAndHidNs2[i];
            weightsBetweenInpAndHidNs3[i] = weightsBetweenInpAndHidNs3[i] + learningRate * errorsWeightsBetweenInpAndHidNs3[i];
        }
        for (int i = 0; i < hiddenNeurons.length; i++) {
            hiddenNeurons[i] = inputNeurons[0] * weightsBetweenInpAndHidNs1[i] +
                    inputNeurons[1] * weightsBetweenInpAndHidNs2[i] + inputNeurons[2] * weightsBetweenInpAndHidNs3[i];
            hiddenNeurons[i] = sigmoid(hiddenNeurons[i]);
            outputNeuron += hiddenNeurons[i] * weightsBetweenHidAndOutNs[i];
        }
        actual = sigmoid(outputNeuron);
        System.out.println("После: " + actual);

    }
}
