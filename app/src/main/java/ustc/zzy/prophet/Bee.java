package ustc.zzy.prophet;


import android.app.Application;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ustc.zzy.prophet.information.App;
import ustc.zzy.prophet.information.ApplicationDao;
import ustc.zzy.prophet.information.MyDatabase;

// Bee is used to collect information
public class Bee {
    Context context;

    public Bee(Context context){
        this.context=context;
    }
    public void getPackages() {

        HashMap<String,String> packageNameToName=new HashMap<>();


        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);

            // establish hash map
            packageNameToName.put(packageInfo.packageName,packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
        }

        //收集app信息
        UsageStatsManager usageStatsManager=(UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        ArrayList<App> apps =new ArrayList<>();
        if(usageStatsManager!=null){

            ApplicationDao dao=MyDatabase.getInstance(context.getApplicationContext()).getApplicationDao();
            long lastQueryTime;
            try {
                lastQueryTime= dao.maxAppEndTime(); // 已经记录的最大的endtime
            }catch (Exception e){
                lastQueryTime=System.currentTimeMillis()- SystemClock.elapsedRealtime();
            }
            long bootTime=System.currentTimeMillis()- SystemClock.elapsedRealtime();
            long endTime=System.currentTimeMillis();
            UsageEvents.Event e=new UsageEvents.Event();
            UsageEvents events=usageStatsManager.queryEvents(lastQueryTime,endTime);

            String name="";
            long appStartTime=0;
            long appEndTime=0;

            while(events.hasNextEvent()){
                events.getNextEvent(e);
                if(e.getEventType()==UsageEvents.Event.MOVE_TO_FOREGROUND){
                    name=e.getPackageName();
                    appStartTime=e.getTimeStamp();
                    appEndTime=0;
                }
                if(e.getEventType()==UsageEvents.Event.MOVE_TO_BACKGROUND){
                    if(name==e.getPackageName() && appStartTime!=0){
                        appEndTime=e.getTimeStamp();
                        dao.insert(new App(0,packageNameToName.get(name),appStartTime,appEndTime));
                        Log.i("bee",packageNameToName.get(name)+" "+appStartTime+" "+appEndTime);
                    }
                    name="";
                    appStartTime=0;
                }
            }
        }
    }
}
