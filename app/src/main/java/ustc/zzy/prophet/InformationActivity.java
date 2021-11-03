package ustc.zzy.prophet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initView();
//        TextView textView=super.findViewById(R.id.textView2);
//        textView.setText("fuck you");
    }

    private void initView() {
//        ApplicationDao applicationDao= MyDatabase.getInstance(getApplicationContext()).getApplicationDao();
//        String[] from={"id","app_name","app_start_time","app_end_time","app_running_time"};
//        int[] to={R.id.app_id,R.id.app_name,R.id.app_start_time,R.id.app_end_time,R.id.app_running_time};
//
//        // adapter is something between front and back
//        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.activity_information,
//                applicationDao.getCursorAll(),from,to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, Yiyu.lines);
        ListView listView = super.findViewById(R.id.info_list);
        listView.setAdapter(adapter);
    }
}