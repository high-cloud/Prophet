package ustc.zzy.prophet;

import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

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
        button1=(Button) super.findViewById(R.id.button1);
        button2=(Button) super.findViewById(R.id.button2);
        button3=(Button) super.findViewById(R.id.button3);
        button4=(Button) super.findViewById(R.id.button4);

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);



    }

    private void initViewDisco(){

        setContentView(R.layout.disco);

        listView=(ListView) super.findViewById(R.id.yiyu);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,Yiyu.lines);
        listView.setAdapter(adapter);

        mediaPlayer=MediaPlayer.create(this,R.raw.surrender);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }


    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.button1:
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    break;

                case R.id.button2:
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    break;

                case R.id.button3:
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    break;

                case R.id.button4:
                    button4.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.VISIBLE);

                    initViewDisco();
                    break;

                default:
                    break;
            }

        }
    };
}
