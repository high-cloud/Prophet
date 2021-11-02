package ustc.zzy.prophet.information;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppNameDao {

    @Query("SELECT * FROM AppName")
    List<AppName> getAll();

    @Query("SELECT app_name FROM appname")
    List<String> getAllName();

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void insert(AppName appName);
}
