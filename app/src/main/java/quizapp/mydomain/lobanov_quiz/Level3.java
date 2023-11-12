package quizapp.mydomain.lobanov_quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная для правой картинки + текст
    Array array = new Array();// Создали новый объект из класса Array
    Random random = new Random();//Объект для генерации случайных чисел
    public int count = 0;//Счетчик правильных ответов

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level3);//Установили текст (номер уровня)
        text_levels.setTextColor(R.color.black95);


        /* *****************
        Отключаем скругление углов картинок т.к работает только с API выше
        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //Код который скруглит углы картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //Код который скруглит углы картинки
        img_right.setClipToOutline(true);
        */
        /*Так как скругление оключено и в этом коде идет поиск объектов на макете мне нужно
        * сделать отдельно это ниже чтоб далее использовать в присвоении картинок*/
        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);//Установка цвета левой TextView
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);//Установка цвета правой TextView


        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Устанавливаем фон основного - начало
        ImageView background =(ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);
        //Устанавливаем фон основного - Конец


        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //Создание нового диалогового окна
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //Скрываем заголовок
        dialog.setContentView(R.layout.previewdialog);//Связь диалога с макетом
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialog.setCancelable(false);//Окно нельзя закрыть кнопкой назад

        //Уснанавливаем картинку в диалоговое окнео - начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg3);
        //Уснанавливаем картинку в диалоговое окнео - конец

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackgroundone3);
        //Устанавливаем фон диалогового окна - конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelthree);
        //Устанавливаем описание задания - конец

        //Кнопка которая закрывает окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатываем нажатие кнопки - начало
                try{
                    //Веруться к назад к выбору уровня - начало
                    Intent intent = new Intent(Level3.this, GameLavels.class);//Создали намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish();// Закрыть этот класс
                    //Веруться к назад к выбору уровня - конец
                }catch (Exception e){
                    //Здесь обрабатываются ошибки
                }
                dialog.dismiss();// Закрываем диалоговое окно
                //Обрабатываем нажатие кнопки - конец
            }
        });
        //Кнопка которая закрывает окно - начало


        //Кнопка "Продолжить" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //Закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" - конец

        dialog.show();//Показать диалоговое окно

        //__________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //Создание нового диалогового окна
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //Скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend);//Связь диалога с макетом
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);//Установить диалоговое окно о ширине экрана (хотя у меня работало и без этого)
        dialogEnd.setCancelable(false);//Окно нельзя закрыть кнопкой назад

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackgroundone3);
        //Устанавливаем фон диалогового окна - конец

        //Интересный факт - начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelthreeEnd);
        //Интересный факт - конец

        //Кнопка которая закрывает окно - начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатываем нажатие кнопки - начало
                try{
                    //Веруться к назад к выбору уровня - начало
                    Intent intent = new Intent(Level3.this, GameLavels.class);//Создали намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish();// Закрыть этот класс
                    //Веруться к назад к выбору уровня - конец
                }catch (Exception e){
                    //Здесь обрабатываются ошибки
                }
                dialogEnd.dismiss();// Закрываем диалоговое окно
                //Обрабатываем нажатие кнопки - конец
            }
        });

        //Кнопка которая закрывает окно - начало


        //Кнопка "Продолжить" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //здесь кода не будет
                }
                dialogEnd.dismiss(); //Закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" - конец


        //__________________________________


        //Конопка "Назад" - начало
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setBackgroundResource(R.drawable.button_sroke_black90_press_white); //Установить стиль кнопки назад т.к белый не подходит
        btn_back.setTextColor(R.color.black95);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Обрабатываем нажатие кнопки "Назад" - начало
                try{
                    //Вернуться наза к выбору уровня - начало
                    Intent intent = new Intent(Level3.this, GameLavels.class);
                    startActivity(intent);
                    finish();
                    //Вернуться наза к выбору уровня - конец
                }catch (Exception e){
                    //Тут должна быть обработака ошибок
                }
                //Обрабатываем нажатие кнопки "Назад" - конец
            }
        });
        //Конопка "Назад" - конец

        //Массив для прогрессбара - начало
            final int[] progress = {
                    R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,R.id.point6,
                    R.id.point7,R.id.point8,R.id.point9,R.id.point10,R.id.point11,R.id.point12,
                    R.id.point13,R.id.point14,R.id.point15,R.id.point16,R.id.point17,R.id.point18,
                    R.id.point19,R.id.point20
            };
        //Массив для прогрессбара - конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level3.this,R.anim.alpha);
        //Подключаем анимацию - конец

        //Выбираем рандомные картинки - начало
        numLeft = random.nextInt(21);//Генерируем случайное число
        img_left.setImageResource(array.images3[numLeft]);//Достаем из массива рандомную картинку
        text_left.setText(array.text3[numLeft]);//Достаем из массива текст

        numRight = random.nextInt(21);//Генерируем случайное число

        //Цикл с предусловием проверяющий равенство чисел - начало (чтоб картинки не совпадали)
        while (numLeft==numRight){
            numRight = random.nextInt(21);//Генерируем случайное число от 0 до 9
        }
        //Цикл с предусловием проверяющий равенство чисел - конец

        img_right.setImageResource(array.images3[numRight]);//Достаем из массива рандомную картинку
        text_right.setText(array.text3[numRight]);//Достаем из массива текст

        //Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);//Блокируем правую картинку (от одновременного нажатия правой и левой)
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {img_left.setImageResource(R.drawable.img_false);
                    //Если коснулся картинки - конец
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (numLeft>numRight){
                        //Если левая картинка больше
                        if (count<20){
                            count=count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]); // можно так TextView tv = (TextView)findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Орпеделяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Орпеделяем правильные ответы и закрашиваем зеленым - конец
                    }else {
                        //Если левая катинка меньше
                        if (count>0) {
                            if (count==1) {
                                count=0;
                            }else {
                                count=count-2; //обрабатываем неправильный ответ
                            }
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]); // можно так TextView tv = (TextView)findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Орпеделяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Орпеделяем правильные ответы и закрашиваем зеленым - конец
                    }
                    //Если отпустил палец - конец
                    if(count==20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);//Параметр Level будет запоминать цифры на каждом уровне и передавать их переменной level
                        //в начале игры цифр еще не существует и для параметра Level устанавливается цифра 1
                        if (level>3) {
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 4);//Установили для параметра Level цифру 2 потому что уровень пройден и мы пишем цифру следующего уровня
                            editor.commit();
                        }
                        dialogEnd.show();;
                    }else {
                        //Обновляем картинки
                        //Выбираем рандомные картинки - начало
                        numLeft = random.nextInt(21);//Генерируем случайное число
                        img_left.setImageResource(array.images3[numLeft]);//Достаем из массива рандомную картинку
                        img_left.startAnimation(a); //Вызываем анимацию
                        text_left.setText(array.text3[numLeft]);//Достаем из массива текст

                        numRight = random.nextInt(21);//Генерируем случайное число

                        //Цикл с предусловием проверяющий равенство чисел - начало (чтоб картинки не совпадали)
                        while (numLeft==numRight){
                            numRight = random.nextInt(21);//Генерируем случайное число
                        }
                        //Цикл с предусловием проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images3[numRight]);//Достаем из массива рандомную картинку
                        img_right.startAnimation(a);//Вызываем анимацию
                        text_right.setText(array.text3[numRight]);//Достаем из массива текст
                        img_right.setEnabled(true);//Включаем обратно правую картинку
                    }
                }
                //Условие касания картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец


        //Обрабатываем нажатие на правую - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);//Блокируем левую картинку (от одновременного нажатия правой и левой)
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else {img_right.setImageResource(R.drawable.img_false);
                        //Если коснулся картинки - конец
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (numLeft<numRight){
                        //Если правая картинка больше
                        if (count<20){
                            count=count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]); // можно так TextView tv = (TextView)findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Орпеделяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Орпеделяем правильные ответы и закрашиваем зеленым - конец
                    }else {
                        //Если правая катинка меньше
                        if (count>0) {
                            if (count==1) {
                                count=0;
                            }else {
                                count=count-2; //обрабатываем неправильный ответ
                            }
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]); // можно так TextView tv = (TextView)findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Орпеделяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Орпеделяем правильные ответы и закрашиваем зеленым - конец
                    }
                    //Если отпустил палец - конец
                    if(count==20) {
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);//Параметр Level будет запоминать цифры на каждом уровне и передавать их переменной level
                        //в начале игры цифр еще не существует и для параметра Level устанавливается цифра 1
                        if (level>3) {
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 4);//Установили для параметра Level цифру 2 потому что уровень пройден и мы пишем цифру следующего уровня
                            editor.commit();
                        }
                        dialogEnd.show(); //Вызвать диалог конца уровня
                    }else {
                        //Обновляем картинки
                        //Выбираем рандомные картинки - начало
                        numLeft = random.nextInt(21);//Генерируем случайное число
                        img_left.setImageResource(array.images3[numLeft]);//Достаем из массива рандомную картинку
                        img_left.startAnimation(a); //Вызываем анимацию
                        text_left.setText(array.text3[numLeft]);//Достаем из массива текст

                        numRight = random.nextInt(21);//Генерируем случайное число

                        //Цикл с предусловием проверяющий равенство чисел - начало (чтоб картинки не совпадали)
                        while (numLeft==numRight){
                            numRight = random.nextInt(21);//Генерируем случайное числ3
                        }
                        //Цикл с предусловием проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images3[numRight]);//Достаем из массива рандомную картинку
                        img_right.startAnimation(a);//Вызываем анимацию
                        text_right.setText(array.text3[numRight]);//Достаем из массива текст

                        img_left.setEnabled(true);//Включаем обратно левую картинку
                    }
                }
                //Условие касания картинки - конец
                return true;
            }
        });
        //Обрабатываем нажатие на левую правую - конец


        //Выбираем рандомные картинки - конец

    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        //Обрабатываем нажатие кнопки "Назад" - начало
        try{
            //Вернуться наза к выбору уровня - начало
            Intent intent = new Intent(Level3.this, GameLavels.class);
            startActivity(intent);
            finish();
            //Вернуться наза к выбору уровня - конец
        }catch (Exception e){
            //Тут должна быть обработака ошибок
        }
        //Обрабатываем нажатие кнопки "Назад" - конец
    }
    //Системная кнопка "Назад" - конец
}
