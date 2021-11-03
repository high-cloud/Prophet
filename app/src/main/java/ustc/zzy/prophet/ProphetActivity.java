package ustc.zzy.prophet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ustc.zzy.prophet.Bayes.AppBayesData;

public class ProphetActivity extends Activity {
    Prophet prophet;
    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_prophet2main:
                    startActivity(new Intent(ProphetActivity.this, MainActivity.class));
                    break;

                case R.id.button_init_prophet:
                    prophet.init();
                    break;

                case R.id.button_predict:
                    long time = System.currentTimeMillis();
                    TextView textView = ProphetActivity.this.findViewById(R.id.textView_prophet);
                    textView.setText(prophet.predict(new AppBayesData("", prophet.timeStamp2minuets(time))));

                default:
                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_prophet);

        Button button1 = super.findViewById(R.id.button_prophet2main);
        button1.setOnClickListener(clickListener);

        Button button_train = super.findViewById(R.id.button_init_prophet);
        button_train.setOnClickListener(clickListener);

        Button button_predict = super.findViewById(R.id.button_predict);
        button_predict.setOnClickListener(clickListener);

        prophet = new Prophet(this);
    }
}