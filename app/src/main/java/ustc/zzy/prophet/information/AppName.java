package ustc.zzy.prophet.information;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppName {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    String app_name;

    public AppName(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
}
