package org.example;

import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LoanClassifier {
    private final RandomForest classifier;
    private final Instances data;

    public LoanClassifier(String arffFilePath) throws Exception {
        // Load ARFF file
        DataSource source = new DataSource(arffFilePath);
        data = source.getDataSet();

        // Set class index (loan_grade)
        data.setClassIndex(data.numAttributes() - 1);

        // Create RandomForest classifier
        classifier = new RandomForest();
        classifier.buildClassifier(data);
    }

    public String predictLoanGrade(double[] values) throws Exception {
        // Create a new instance for prediction
        Instance newInst = new DenseInstance(1.0, values);
        newInst.setDataset(data);

        // Get predicted class label
        int predictedClass = (int) classifier.classifyInstance(newInst);
        return data.classAttribute().value(predictedClass);
    }
}
