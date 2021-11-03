package ustc.zzy.prophet.Bayes;

import java.util.List;

public class Bayes {
    private int lambda;
    private BayesFilter bayesFilter;

    private double[] prioriProbability;
    private double[][][] conditionalProbability;

    public Bayes(BayesFilter bayesFilter, int lambda) {
        this.bayesFilter = bayesFilter;
        this.lambda = lambda;

        //initialize probability
        prioriProbability = new double[bayesFilter.outPutSpace().size()];

        conditionalProbability = new double[bayesFilter.getDimension()][][];
        for (int i = 0; i < bayesFilter.getDimension(); ++i) {
            conditionalProbability[i] = new double[bayesFilter.inputSpace_i(i).size()][bayesFilter.outPutSpace().size()];
        }
    }

    public <DataType> String predict(DataType condition) {
        int out_index = 0;
        double max_p = 0;
        for (int k = 0; k < bayesFilter.outPutSpace().size(); ++k) {

            double probability = prioriProbability[k];
            for (int j = 0; j < bayesFilter.getDimension(); ++j) {
                probability *= conditionalProbability[j][bayesFilter.inputOfData(condition, j)][k];
            }

            if (probability > max_p) {
                max_p = probability;
                out_index = k;
            }
        }

        return (String) bayesFilter.outPutSpace().get(out_index);
    }

    public <DataType> void train(List<DataType> trainSet) throws Error {
        if (!bayesFilter.ifDataTypeSame(trainSet.get(0))) {
            throw new Error("wrong trainSet type");
        }

        List<String> outSpace = bayesFilter.outPutSpace();


        // P(Y=c)=sigma_i I(i=c)
        for (int i = 0; i < trainSet.size(); ++i) {
            int out_index = bayesFilter.outOfData(trainSet.get(i));
            prioriProbability[out_index] += 1.0;

            // P(X^j=a_jl | Y=c)=sigma_i I(x^j_i=a_jl | y_i=c)
            for (int j = 0; j < bayesFilter.getDimension(); ++j) {
                int input_index = bayesFilter.inputOfData(trainSet.get(i), j);
                conditionalProbability[j][input_index][out_index] += 1.0;
            }
        }

        // continue cal P_jlk
        for (int j = 0; j < bayesFilter.getDimension(); ++j) {
            for (int l = 0; l < bayesFilter.inputSpace_i(j).size(); ++l) {
                for (int k = 0; k < bayesFilter.outPutSpace().size(); ++k) {
                    conditionalProbability[j][l][k] += lambda;
                    conditionalProbability[j][l][k] /= prioriProbability[k] + lambda * bayesFilter.inputSpace_i(j).size();
                }
            }
        }

        // continue cal P_k
        for (int k = 0; k < bayesFilter.outPutSpace().size(); ++k) {
            prioriProbability[k] += lambda;
            prioriProbability[k] /= trainSet.size() + bayesFilter.outPutSpace().size() * lambda;
        }


    }
}
