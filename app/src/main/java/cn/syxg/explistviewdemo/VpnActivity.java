package cn.syxg.explistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class VpnActivity extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout rl;
    LinearLayout ll;
    CircleImageView mCircleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vv_layout);


        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(VpnActivity.this,"购买",Toast.LENGTH_SHORT).show();
            }
        });

        mCircleImageView = findViewById(R.id.profile_image);

        mCircleImageView.startCountDownTime(new CircleImageView.OnCountdownFinishListener() {
            @Override
            public void countdownFinished() {

            }
        });

       /* imageView = findViewById(R.id.iv);
        rl = findViewById(R.id.rl);
        ll = findViewById(R.id.ll);

        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VpnActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });*/



        /*findViewById(R.id.rl).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //v = imageView;
                //Toast.makeText(VpnActivity.this,"hello",Toast.LENGTH_SHORT).show();
                if(v instanceof imageView){

                    return imageView.dispatchTouchEvent(event);

                }else {

                    return false;
                }



            }
        });*/
    }
}
