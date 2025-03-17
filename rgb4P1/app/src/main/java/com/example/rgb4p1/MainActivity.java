package com.example.rgb4p1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;
    SeekBar bar1, bar2, bar3;
    int red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            return insets;
        });

        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);

        bar1 = findViewById(R.id.seekBar1);
        bar2 = findViewById(R.id.seekBar2);
        bar3 = findViewById(R.id.seekBar3);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar == bar1) {
                    red = progress;
                } else if (seekBar == bar2) {
                    green = progress;
                } else if (seekBar == bar3) {
                    blue = progress;
                }
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        bar1.setOnSeekBarChangeListener(listener);
        bar2.setOnSeekBarChangeListener(listener);
        bar3.setOnSeekBarChangeListener(listener);
    }

    private void updateColor() {
        String rgbText = red + ", " + green + ", " + blue;
        tv1.setText(rgbText);
        tv2.setText(red + " + " + green + " * " + blue + " = " + (red + green * blue));
        tv1.setBackgroundColor(Color.rgb(255 - red, 255 - green, 255 - blue));
        tv1.setTextColor(Color.rgb(red, green, blue));
    }
}
