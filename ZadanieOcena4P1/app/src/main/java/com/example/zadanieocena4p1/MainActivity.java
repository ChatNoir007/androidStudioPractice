package com.example.zadanieocena4p1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img1, img2, img3, img4, img5;
    CheckBox chk1, chk2, chk3, chk4, chk5;
    Button btn1, btn2, btn3;
    Random random = new Random();
    int[] diceValues = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv1 = findViewById(R.id.textView1);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);

        chk1 = findViewById(R.id.checkBox1);
        chk2 = findViewById(R.id.checkBox2);
        chk3 = findViewById(R.id.checkBox3);
        chk4 = findViewById(R.id.checkBox4);
        chk5 = findViewById(R.id.checkBox5);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        Reset(null);
    }

    public void Losuj(View view) {
        ImageView[] diceImages = {img1, img2, img3, img4, img5};

        for (int i = 0; i < 5; i++) {
            diceValues[i] = random.nextInt(6) + 1;
            int resID = getResources().getIdentifier("kostka" + diceValues[i], "drawable", getPackageName());
            diceImages[i].setImageResource(resID);
        }

        btn1.setEnabled(false);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        tv1.setText("");
    }

    public void Dobierz(View view) {
        CheckBox[] checkBoxes = {chk1, chk2, chk3, chk4, chk5};
        ImageView[] diceImages = {img1, img2, img3, img4, img5};

        for (int i = 0; i < 5; i++) {
            if (checkBoxes[i].isChecked()) {
                diceValues[i] = random.nextInt(6) + 1;
                int resID = getResources().getIdentifier("kostka" + diceValues[i], "drawable", getPackageName());
                diceImages[i].setImageResource(resID);
                checkBoxes[i].setChecked(false);
            }
        }

        btn2.setEnabled(false);
        tv1.setText(sprawdzWynik());
    }

    public void Reset(View view) {
        btn1.setEnabled(true);
        btn2.setEnabled(false);
        btn3.setEnabled(false);

        chk1.setChecked(false);
        chk2.setChecked(false);
        chk3.setChecked(false);
        chk4.setChecked(false);
        chk5.setChecked(false);

        img1.setImageResource(R.drawable.pytajnik);
        img2.setImageResource(R.drawable.pytajnik);
        img3.setImageResource(R.drawable.pytajnik);
        img4.setImageResource(R.drawable.pytajnik);
        img5.setImageResource(R.drawable.pytajnik);

        tv1.setText("Wynik");
    }

    public String sprawdzWynik() {
        int[] counts = new int[7];
        for (int value : diceValues) {
            counts[value]++;
        }

        boolean para = false, trojka = false, kareta = false;
        int liczbaPar = 0;

        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 2) {
                liczbaPar++;
                para = true;
            } else if (counts[i] == 3) {
                trojka = true;
            } else if (counts[i] == 4) {
                kareta = true;
            } else if (counts[i] == 5) {
                return "Piątka!";
            }
        }

        if (kareta) return "Kareta!";
        if (trojka && para) return "Full (Trójka + Para)!";
        if (trojka) return "Trójka!";
        if (liczbaPar == 2) return "Dwie Pary!";
        if (para) return "Para!";

        return "Brak układu!";
    }
}
