package ustc.zzy.prophet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProphetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prophet);

        initView();
    }

    private void initView(){
        Button button1=super.findViewById(R.id.button_prophet2main);
        button1.setOnClickListener(clickListener);

    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch ( v.getId() ) {
                case R.id.button_prophet2main:
                    startActivity(new Intent(ProphetActivity.this,MainActivity.class));
                    break;


                default:
                    break;
            }

        }

    };
}