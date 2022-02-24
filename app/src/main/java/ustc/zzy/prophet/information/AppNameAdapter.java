package ustc.zzy.prophet.information;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ustc.zzy.prophet.R;


public class AppNameAdapter extends RecyclerView.Adapter<AppNameAdapter.ViewHolder> {
    List<AppName> mAppNames;

    public AppNameAdapter(List<AppName> mAppNames) {
        this.mAppNames = mAppNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getAppName().setText(mAppNames.get(position).getApp_name());
    }

    @Override
    public int getItemCount() {
        return mAppNames.size();
    }

    // define what a list_item look like
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView appName;

        public TextView getAppName() {
            return appName;
        }

        public ViewHolder(View view) {
            super(view);

            appName = (TextView) view.findViewById(R.id.list_name_app_name);
        }
    }
}
