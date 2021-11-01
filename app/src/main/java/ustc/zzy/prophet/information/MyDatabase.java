package ustc.zzy.prophet.information;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {App.class},version = 6,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public static final String DB_NAME="mydatabase.db";
    public static MyDatabase instance;

    public static MyDatabase getInstance(Context context){
        if(instance==null){
            instance=create(context);
        }
        return instance;
    }

    private static MyDatabase create(final Context context){
        return Room.databaseBuilder(context,MyDatabase.class,DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public abstract ApplicationDao getApplicationDao();
}
