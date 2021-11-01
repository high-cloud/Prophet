package ustc.zzy.prophet.information;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ApplicationDao {

    @Query("SELECT * FROM apps")
    List<App> getAll();

    @Insert
    void insert(App app);

    @Query("SELECT MAX(app_end_time) FROM apps")
    Long maxAppEndTime();

    @Query("SELECT id as _id,app_name,app_start_time,app_end_time,app_running_time FROM apps")
    Cursor getCursorAll();
}
