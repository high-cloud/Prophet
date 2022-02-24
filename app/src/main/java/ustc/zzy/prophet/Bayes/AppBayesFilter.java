package ustc.zzy.prophet.Bayes;

import java.util.List;


public class AppBayesFilter implements BayesFilter<AppBayesData> {

    List<String> mOutputSpace;
    List<Integer> mInputSpace;

    public AppBayesFilter(List<String> mOutputSpace, List<Integer> mInputSpace) {
        this.mOutputSpace = mOutputSpace;
        this.mInputSpace = mInputSpace;
    }

    @Override
    public int outOfData(AppBayesData data) {
        int first = mOutputSpace.indexOf(data.getOutput());
        if (first == -1) {
            throw new Error();
        }

        return first;
    }

    @Override
    public int inputOfData(AppBayesData data, int ith_dimension) {
        if (ith_dimension != 0) {
            throw new Error();
        }
        return mInputSpace.indexOf(data.getInput());
    }

    @Override
    public List<Integer> inputSpace_i(int ith_dimension) {
        if (ith_dimension != 0) {
            throw new Error();
        }

        return mInputSpace;
    }

    @Override
    public int getDimension() {
        return 1;
    }

    @Override
    public <T> boolean ifDataTypeSame(T t) {
        return t.getClass() == AppBayesData.class;
    }

    @Override
    public List<String> outPutSpace() {
        return mOutputSpace;
    }
}
