package ustc.zzy.prophet.Bayes;

import java.util.ArrayList;
import java.util.List;

/* ByesFilter is a interface define what input space
    and output space is, and offer a indicate func.
 */
public interface BayesFilter<DataType>{

//    public  int indicate(DataType data,String y) ;
//    public  int indicate(DataType data,String y,int j,int l);

    public int outOfData(DataType data); // 返回data中的out的指标

    public int  inputOfData(DataType data,int ith_dimension);
    public List<Integer> inputSpace_i(int ith_dimension);

    public int getDimension(); // dimension of input space

    public <T> boolean ifDataTypeSame(T t);

    public List<String> outPutSpace();



}
