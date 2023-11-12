package quizapp.mydomain.lobanov_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long baskPressedTime; //Перременная для хранения времени при нажатии си-ой кнопки "Назад"
    private Toast backToast; //Переменная нужна чтобы сообщение Toast закрывлось при закрытии игры

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Открываем окно выбора уровней
        Button buttonStart = (Button)findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
               public void onClick(View v)  {
            try{
                Intent intent = new Intent(MainActivity.this, GameLavels.class);
                startActivity(intent); finish();;
            }catch (Exception e){

            }
            }

        });
//******

//Убираем строку с зарядом батареи и временем
                Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//******


    }
    //Системная кнопка "Назад" - начало

    @Override
    public void onBackPressed() {

        if (baskPressedTime + 2000 > System.currentTimeMillis()){ //Если сохраненное время + 2 сек. больше чем текущее время
            backToast.cancel();//Закрываем сообщение Toast при выходе из игры чтоб не висело при закрытии главного экрана
            super.onBackPressed();  //Закрыть игру
            return;
        }else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите еще раз чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();;
        }

        baskPressedTime = System.currentTimeMillis();
    }

    //Системная кнопка "Назад" - конец
}
