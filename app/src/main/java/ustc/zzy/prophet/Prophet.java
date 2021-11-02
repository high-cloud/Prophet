package ustc.zzy.prophet;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ustc.zzy.prophet.Bayes.AppBayesData;
import ustc.zzy.prophet.Bayes.AppBayesFilter;
import ustc.zzy.prophet.Bayes.Bayes;
import ustc.zzy.prophet.information.App;
import ustc.zzy.prophet.information.AppNameDao;
import ustc.zzy.prophet.information.ApplicationDao;
import ustc.zzy.prophet.information.MyDatabase;

// Prophet can see into future, predict what app will be opened
public class Prophet {
    Context context;
    Bayes predictors;


    public Prophet(Context context) {
        this.context = context;
    }

    public void init(){
        AppNameDao appNameDao= MyDatabase.getInstance(context.getApplicationContext()).getAppNameDao();
        ApplicationDao applicationDao=MyDatabase.getInstance(context.getApplicationContext()).getApplicationDao();


        List<String> outSpace=appNameDao.getAllName(); // 输出空间
        List<Integer> inputSpace=new ArrayList<>(1440);   //输入空间

        // 输入空间是 0~1440 代表每天的分钟数
        for (int i=0;i<1440;++i){
            inputSpace.add(i);
        }

        ArrayList<AppBayesData> trainSet=new ArrayList<>();
        List<App> appInfos=applicationDao.getAll();

        // 初始化 训练集
        for(int i=0;i<appInfos.size();++i){
            App app=appInfos.get(i);
            AppBayesData appBayesData=new AppBayesData(app.getAppName(),timeStamp2minuets(app.getAppStartTime()));
            trainSet.add(appBayesData);
        }

        AppBayesFilter appBayesFilter=new AppBayesFilter(outSpace,inputSpace);
        predictors=new Bayes(appBayesFilter,1);

        predictors.train(trainSet);

    }


    // transfer a unix time to minuets passed that day
    public int timeStamp2minuets(long timeStamp){
        return (int) (timeStamp / 1000 /60 %1440);
    }
}
