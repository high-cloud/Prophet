package ustc.zzy.prophet;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BeeServer extends Service {
    public BeeServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Bee bee = new Bee(this);
        bee.getPackages(); // 收集应用信息
    }
}