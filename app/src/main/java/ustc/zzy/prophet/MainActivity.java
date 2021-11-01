package ustc.zzy.prophet;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ustc.zzy.prophet.information.AppAdapter;
import ustc.zzy.prophet.information.ApplicationDao;
import ustc.zzy.prophet.information.MyDatabase;

public class MainActivity extends Activity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView;

    ListView listView;
    MediaPlayer mediaPlayer;
    boolean isPaused;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mediaPlayer==null)
            return;
        if(mediaPlayer.isPlaying()) {
            isPaused=true;
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isPaused)
        {
            mediaPlayer.start();
        }
    }

    private void initView(){

        isPaused=false;
        button1=super.findViewById(R.id.button1);
        button2=super.findViewById(R.id.button2);
        button3=super.findViewById(R.id.button3);
        button4=super.findViewById(R.id.button4);
        textView=super.findViewById(R.id.textView);

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);

        Button button=super.findViewById(R.id.button_to_info);
        button.setOnClickListener(clickListener);

        Bee bee=new Bee(this);
        bee.getPackages(); // 收集应用信息

    }

    private void initViewDisco(){

        setContentView(R.layout.disco);

        listView=(ListView) super.findViewById(R.id.yiyu);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, Yiyu.lines);
        listView.setAdapter(adapter);

        mediaPlayer=MediaPlayer.create(this,R.raw.surrender);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private void initInformationShow(){
        setContentView(R.layout.activity_information);

        ApplicationDao applicationDao= MyDatabase.getInstance(getApplicationContext()).getApplicationDao();
        String[] from={"_id","app_name"};
        int[] to={R.id.app_id,R.id.app_name};


        // initialize recyclerView
        RecyclerView recyclerView=super.findViewById(R.id.info_list);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AppAdapter appAdapter=new AppAdapter(applicationDao.getAll());
        recyclerView.setAdapter(appAdapter);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.button1:
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    textView.setText("那便是她们的住址。");
                    break;

                case R.id.button2:
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    textView.setText("哪怕这世间最澄清的水，");
                    break;

                case R.id.button3:
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    textView.setText("只要够深，也能让人沉溺。");
                    break;

                case R.id.button4:
                    button4.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.VISIBLE);

                    initViewDisco();
                    break;

                case R.id.button_to_info:
//                    Intent intent=new Intent(MainActivity.this,InformationActivity.class);
//                    startActivity(intent);
                    initInformationShow();
                    break;

                default:
                    break;
            }

        }

    };

}
