package quizapp.mydomain.lobanov_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class GameLavels extends AppCompatActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelavels);

        MobileAds.initialize(this,"ca-app-pub-1822178150295486~1526707657");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);


//Убираем строку с зарядом батареи и временем
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//******

//Нажатие кнопки наза.
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLavels.this, MainActivity.class);
                    startActivity(intent);
                    //если ставить finish то закроется GameLavels активити
                    finish();

                }catch (Exception e){

                }
            }
        });
//******

        //Конопка перехода на 1 уровень - начало
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=1) {
                        Intent intent = new Intent(GameLavels.this, Level1.class);
                        startActivity(intent);
                        finish();
                    }else {
                        //пусто
                    }

                }catch (Exception e){
                    //пусто
                }
            }
        });

        //Конопка перехода на 2 уровень - начало
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=2) {
                        Intent intent = new Intent(GameLavels.this, Level2.class);
                        startActivity(intent);
                        finish();
                    }else {
                        //пусто
                    }

                }catch (Exception e){
                    //пусто
                }
            }
        });

        //Конопка перехода на 2 уровень - конец

        //Конопка перехода на 3 уровень - начало
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=3) {
                        Intent intent = new Intent(GameLavels.this, Level3.class);
                        startActivity(intent);
                        finish();
                    }else {
                        //Пусто
                    }

                }catch (Exception e){
                    //пусто
                }
            }
        });

        //Конопка перехода на 4 уровень - начало
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level>=4) {
                        Intent intent = new Intent(GameLavels.this, Level4.class);
                        startActivity(intent);
                        finish();
                    }else {
                        //Пусто
                    }

                }catch (Exception e){
                    //пусто
                }
            }
        });

        //Конопка перехода на 4 уровень - конец

        final int [] x= {
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,
                R.id.textView6,
                R.id.textView7,
                R.id.textView8,
                R.id.textView9,
                R.id.textView10,
                R.id.textView11,
                R.id.textView12,
                R.id.textView13,
                R.id.textView14,
                R.id.textView15,
                R.id.textView16,
                R.id.textView17,
                R.id.textView18,
                R.id.textView19,
                R.id.textView20,
                R.id.textView21,
                R.id.textView22,
                R.id.textView23,
                R.id.textView24,
                R.id.textView25,
                R.id.textView26,
                R.id.textView27,
                R.id.textView28,
                R.id.textView29,
                R.id.textView30,
        };

        for (int i=1; i<level; i++) {
            TextView tv = findViewById(x[i]);
            tv.setText(""+(i+1));
        }
    }
    //Системная кнопка "Назад"
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(GameLavels.this, MainActivity.class);
            startActivity(intent);
            //если ставить finish то закроется GameLavels активити
            finish();

        }catch (Exception e){

        }
    }
    //******
}
