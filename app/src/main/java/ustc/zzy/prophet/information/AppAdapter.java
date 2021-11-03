package ustc.zzy.prophet.information;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ustc.zzy.prophet.R;
import ustc.zzy.prophet.util.Time;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    List<App> mApps; //store app informations

    public AppAdapter(List<App> apps) {
        this.mApps = apps;
    }


    // create new views
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_app,parent,false);
        return new ViewHolder(view);
    }


    //replace contents of  a view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getAppId().setText(String.valueOf(mApps.get(position).id));
        holder.getAppName().setText(mApps.get(position).getAppName());
        holder.getAppStartTime().setText(Time.timeStamp2date(mApps.get(position).getAppStartTime()));
        holder.getAppEndTime().setText(Time.timeStamp2date(mApps.get(position).getAppEndTime(),"HH:mm:ss"));
        holder.getAppRunningTime().setText(String.valueOf(mApps.get(position).getAppRunningTime()/1000));
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    // define what a list_item look like
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView appId;
        private final TextView appName;
        private final TextView appStartTime;
        private final TextView appEndTime;
        private final TextView appRunningTime;

        public TextView getAppId() {
            return appId;
        }

        public TextView getAppName() {
            return appName;
        }

        public TextView getAppStartTime() {
            return appStartTime;
        }

        public TextView getAppEndTime() {
            return appEndTime;
        }

        public TextView getAppRunningTime() {
            return appRunningTime;
        }

        public ViewHolder(View view){
            super(view);

            appId=(TextView) view.findViewById(R.id.app_id);
            appName=(TextView) view.findViewById(R.id.app_name);
            appStartTime=(TextView) view.findViewById(R.id.app_start_time);
            appEndTime=(TextView) view.findViewById(R.id.app_end_time);
            appRunningTime=(TextView) view.findViewById(R.id.app_running_time);

        }
    }




}
