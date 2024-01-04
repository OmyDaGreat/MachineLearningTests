package org.example;

import weka.core.*;
import weka.classifiers.functions.Logistic;
import java.io.BufferedReader;
import java.io.FileReader;

public class CreditWorthiness {
   public static void main(String[] args) throws Exception {
       // Load the dataset
       BufferedReader reader = new BufferedReader(new FileReader("credit.arff"));
       Instances data = new Instances(reader);
       reader.close();

       // Set the class index to the last attribute
       data.setClassIndex(data.numAttributes() - 1);

       // Create a Logistic Regression classifier
       Logistic classifier = new Logistic();

       // Train the classifier
       classifier.buildClassifier(data);

       // Now you can use the classifier to make predictions
       String[] input = {"21","100000","","",""};
       double[] values = new double[data.numAttributes()];
       for (int i = 0; i < values.length; i++) {
           values[i] = Double.parseDouble(input[i]);
       }
       double result = classifier.classifyInstance(new DenseInstance(1.0, values));
       System.out.println("Predicted class value: " + result);
   }
}
