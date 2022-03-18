package com.jetbrains;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputFile iris_test = new InputFile(
                System.getProperty("user.dir") + "\\data\\iris_test.txt"
        );

        InputFile iris_training = new InputFile(
                System.getProperty("user.dir") + "\\data\\iris_training.txt"
        );

        System.out.print("Input k: ");
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());

        showStatistic(iris_test, iris_training, k);
    }

    public static void showStatistic(InputFile test, InputFile training, int k) {
        if (test.getNumberOfColumns() == training.getNumberOfColumns()) {
            int counterForTrue = 0;
            List<InputFile.Record> testRecords = test.getRecords();
            List<InputFile.Record> trainingRecords = training.getRecords();

            System.out.println("TestRecord\t\t\t\t\t\t\t|\t ClosestTrainingRecord\t\t\t\t\t|\tIsCorrect\t|\tDistanceBetween");
            for (InputFile.Record testRecord : testRecords) {

//                double[] distances = new double[test.getNumberOfColumns()];
//                Arrays.fill(distances, 0f);
                double curDistance;
                double minDistance = Double.MAX_VALUE;
                int minIndexInTrainings = 0;

                for (int i = 0; i < trainingRecords.size(); i++) {
                    curDistance = InputFile.distanceBetween(testRecord, trainingRecords.get(i), test.getNumberOfColumns() - 1);
                    if (curDistance < minDistance) {
                        minDistance = curDistance;
                        minIndexInTrainings = i;
                    }
                }

                if (testRecord.decision.trim().compareTo(trainingRecords.get(minIndexInTrainings).decision.trim()) == 0)
                    counterForTrue++;

                System.out.println(testRecord
                        + "\t|\t" + trainingRecords.get(minIndexInTrainings)
                        + "\t|\t" + (testRecord.decision.trim().compareTo(trainingRecords.get(minIndexInTrainings).decision.trim()) == 0)
                        + "\t\t|\t" + minDistance);
            }

            System.out.println();
            System.out.println("Correctness of experiment:\t\t\t" + counterForTrue + "\t/\t" + testRecords.size());
            System.out.println("Possibility for correct answer:\t\t" + ((double) counterForTrue * 100 / (double) testRecords.size()) + " %");
        } else {

        }
    }
}
