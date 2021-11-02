package ustc.zzy.prophet;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ustc.zzy.prophet.information.AppAdapter;
import ustc.zzy.prophet.information.AppNameAdapter;
import ustc.zzy.prophet.information.AppNameDao;
import ustc.zzy.prophet.information.ApplicationDao;
import ustc.zzy.prophet.information.MyDatabase;

public class MainActivity extends Activity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        startService(new Intent(this,BeeServer.class));

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mediaPlayer==null)
            return;
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_main);

        Button button1=super.findViewById(R.id.button1);
        Button button2=super.findViewById(R.id.button2);
        Button button3=super.findViewById(R.id.button3);
        Button button4=super.findViewById(R.id.button4);

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);

        Button buttonInfo=super.findViewById(R.id.button_to_info);
        buttonInfo.setOnClickListener(clickListener);

        Button buttonName=super.findViewById(R.id.button_to_name);
        buttonName.setOnClickListener(clickListener);

        Button button2Prophet=super.findViewById(R.id.button_main2prophet);
        button2Prophet.setOnClickListener(clickListener);


    }

    private void initViewDisco(){

        setContentView(R.layout.disco);

        ListView listView=(ListView) super.findViewById(R.id.yiyu);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, Yiyu.lines);
        listView.setAdapter(adapter);

        if(mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.surrender);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        else {
            mediaPlayer.start();
        }
    }

    private void initInformationShow(){
        setContentView(R.layout.activity_information);

        ApplicationDao applicationDao= MyDatabase.getInstance(getApplicationContext()).getApplicationDao();


        // initialize recyclerView
        RecyclerView recyclerView=super.findViewById(R.id.info_list);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AppAdapter appAdapter=new AppAdapter(applicationDao.getAll());
        recyclerView.setAdapter(appAdapter);
    }

    private void initNameShow(){
        setContentView(R.layout.app_name);


        Button button2Main=super.findViewById(R.id.name_2_main);
        button2Main.setOnClickListener(clickListener);


        AppNameDao appNameDao = MyDatabase.getInstance(getApplicationContext()).getAppNameDao();

        // initialize recyclerView
        RecyclerView recyclerView=super.findViewById(R.id.name_list);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AppNameAdapter appNameAdapter=new AppNameAdapter(appNameDao.getAll());
        recyclerView.setAdapter(appNameAdapter);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.button1: {
                    Button button1 = MainActivity.this.findViewById(R.id.button1);
                    Button button2 = MainActivity.this.findViewById(R.id.button2);


                    TextView textView = MainActivity.this.findViewById(R.id.textView);
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    textView.setText("那便是她们的住址。");
                    break;
                }

                case R.id.button2: {
                    Button button2 = MainActivity.this.findViewById(R.id.button2);
                    Button button3 = MainActivity.this.findViewById(R.id.button3);

                    TextView textView = MainActivity.this.findViewById(R.id.textView);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    textView.setText("哪怕这世间最澄清的水，");
                    break;
                }

                case R.id.button3: {
                    Button button3 = MainActivity.this.findViewById(R.id.button3);
                    Button button4 = MainActivity.this.findViewById(R.id.button4);

                    TextView textView = MainActivity.this.findViewById(R.id.textView);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    textView.setText("只要够深，也能让人沉溺。");
                    break;
                }

                case R.id.button4: {
                    Button button1=MainActivity.this.findViewById(R.id.button1);

                    Button button4=MainActivity.this.findViewById(R.id.button4);


                    button4.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.VISIBLE);

                    initViewDisco();
                    break;
                }

                case R.id.button_to_info:
                    initInformationShow();
                    break;

                case R.id.button_to_name:
                    initNameShow();
                    break;

                case R.id.name_2_main:
                    initView();
                    break;

                case R.id.button_main2prophet:
                    startActivity(new Intent(MainActivity.this,ProphetActivity.class));
                    break;

                default:
                    break;
            }

        }

    };

}
