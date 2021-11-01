package ustc.zzy.prophet.information;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "apps")
public class App {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name="app_name")
    String appName;

    @ColumnInfo(name="app_start_time")
    long appStartTime;

    @ColumnInfo(name="app_end_time")
    long appEndTime;

    @ColumnInfo(name="app_running_time")
    long appRunningTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getAppStartTime() {
        return appStartTime;
    }

    public void setAppStartTime(long appStartTime) {
        this.appStartTime = appStartTime;
    }

    public long getAppEndTime() {
        return appEndTime;
    }

    public void setAppEndTime(long appEndTime) {
        this.appEndTime = appEndTime;
    }

    public long getAppRunningTime() {
        return appRunningTime;
    }

    public void setAppRunningTime(long appRunningTime) {
        this.appRunningTime = appRunningTime;
    }

    @Ignore
    public App(){}


    public App(int id,String appName, long appStartTime, long appEndTime) {
        this.id=id;
        this.appName = appName;
        this.appStartTime = appStartTime;
        this.appEndTime = appEndTime;
        this.appRunningTime=appEndTime-appStartTime;
    }
}
