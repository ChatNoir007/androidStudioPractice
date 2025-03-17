package com.example.triangle3p1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView3;
    private int a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView3 = findViewById(R.id.textView3);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateEquilateralTriangle();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateIsoscelesTriangle();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateScaleneTriangle();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int choice = random.nextInt(3);
                switch (choice) {
                    case 0:
                        generateEquilateralTriangle();
                        break;
                    case 1:
                        generateIsoscelesTriangle();
                        break;
                    case 2:
                        generateScaleneTriangle();
                        break;
                }
            }
        });
    }

    private void generateEquilateralTriangle() {
        a = b = c = getRandomSide();
        imageView.setImageResource(R.drawable.rownoboczny);
        textView3.setText("a = " + a + "\nb = " + b + "\nc = " + c);
    }

    private void generateIsoscelesTriangle() {
        a = b = getRandomSide();
        c = getRandomSide(a, b);
        imageView.setImageResource(R.drawable.rownoramienny);
        textView3.setText("a = " + a + "\nb = " + b + "\nc = " + c);
    }

    private void generateScaleneTriangle() {
        a = getRandomSide();
        b = getRandomSide();
        c = getRandomSide(a, b);
        imageView.setImageResource(R.drawable.roznoboczny);
        textView3.setText("a = " + a + "\nb = " + b + "\nc = " + c);
    }

    private int getRandomSide() {
        return new Random().nextInt(90) + 10; // Random side between 10 and 99
    }

    private int getRandomSide(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
