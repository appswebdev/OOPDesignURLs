package college.edu.tomer.oopdesignurls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnWeatherChangedListener {
    TextView tvWhether;
    ImageView ivTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWhether = (TextView) findViewById(R.id.tvWheather);
        ivTemp = (ImageView) findViewById(R.id.ivTemp);
        ivTemp = (ImageView) findViewById(R.id.ivTemp);

        WhetherStation station = WhetherStation.getSharedInstance();
        station.addListener(this);


    }

    @Override
    public void onWeatherChanged(final Weather weather) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvWhether.setText(weather.getTemp() + "Deg");
            }
        });

    }
}
