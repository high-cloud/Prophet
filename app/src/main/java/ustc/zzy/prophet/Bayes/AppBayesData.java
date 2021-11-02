package ustc.zzy.prophet.Bayes;

public class AppBayesData {
    private String output;
    private int input;

    public AppBayesData(String output, int input) {
        this.output = output;
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
