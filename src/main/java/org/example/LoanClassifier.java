package org.example;

import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.OutputStream;
import java.io.PrintStream;

public class LoanClassifier {
    public static void main(String[] args) throws Exception {
        // System.setErr(new PrintStream(new OutputStream() {public void write(int b) { /* Get rid of unused warning */ }}));

        // Load ARFF file
        DataSource source = new DataSource(System.getProperty("user.home") + "\\IdeaProjects\\MachineLearningTests\\src\\main\\java\\org\\example\\creditRisk.arff");
        Instances data = source.getDataSet();

        // Set class index (loan_grade)
        data.setClassIndex(data.numAttributes() - 1);

        // Create RandomForest classifier
        RandomForest classifier = new RandomForest();
        classifier.buildClassifier(data);

        // Make predictions (replace with your own data)
        double[] values = {22,59000,0,3};

        // Create a new instance for prediction
        Instance newInst = new DenseInstance(1.0, values);
        newInst.setDataset(data);

        // Get predicted class label
        int predictedClass = (int) classifier.classifyInstance(newInst);
        String predictedClassLabel = data.classAttribute().value(predictedClass);

        // Print the predicted loan grade
        System.out.println("Predicted Loan Grade: " + predictedClassLabel);
        System.out.println(classifier);
    }
}
