package org.example;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

import java.io.OutputStream;
import java.io.PrintStream;

public class DecisionTree {
    public static void main(String[] args) throws Exception {
        System.setErr(new PrintStream(new OutputStream() {public void write(int b) { /* Get rid of unused warning */ }}));

        // Load data from CSV file
        DataSource source = new DataSource(System.getProperty("user.home") + "\\IdeaProjects\\MachineLearningTests\\src\\main\\java\\org\\example\\data.arff");
        Instances data = source.getDataSet();

        // Convert string attributes to nominal
        StringToNominal stn = new StringToNominal();
        stn.setInputFormat(data);
        Instances newData = Filter.useFilter(data, stn);

        // Set the class index
        newData.setClassIndex(newData.numAttributes() - 1);

        // Create J48 tree classifier
        J48 tree = new J48();

        // Train the classifier with the data
        tree.buildClassifier(newData);

        // Print out the built model
        System.out.println(tree);
    }
}