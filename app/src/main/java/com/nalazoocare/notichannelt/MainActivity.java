package com.nalazoocare.notichannelt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nalazoocare.notichannelt.service.JobSchedulerStart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);


//
//      DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
//            }
//        };
//        DatePickerDialog dialog = new DatePickerDialog(this, listener, 2013, 9, 22);
//        dialog.show();





        datePicker.init(datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

         long currentTime = System.currentTimeMillis();

                //사용자가 고른 날짜를 밀리초로 변화
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,monthOfYear,dayOfMonth);

                long choiceTime = calendar.getTimeInMillis();
                Log.d("meme","meme time choice millis : " + choiceTime);
                Log.d("meme","meme time  current millis : " + choiceTime);


                //초이스 타임 밀리초 -> 로그확인 데이트포맷
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
                Date choiceTimeDate = new Date(choiceTime);
                String choiceformat = sdf.format(choiceTimeDate);
                Log.d("meme", "meme -> choiceTime -> Mills -> dateFormat" + choiceformat);

                //현재 타임 밀리초  -> 로그확인 데이트포맷
                Date currentTimeDate = new Date(currentTime);
                String currentformat =sdf.format(currentTimeDate);
                Log.d("meme", "meme -> current -> Mills -> currentTime : " + currentformat);


                //두날짜간에 차이 밀리초
                long time = choiceTime - currentTime;


                //차이 밀리초 + 현재 밀리초 = 지난일수 밀리초 구하기
                long afterDays = currentTime + time;

                //지난일수 밀리초  포맷 으로 로그 확인하기
                Date diffDay = new Date(afterDays);
                String diff =sdf.format(diffDay);
                Log.d("meme", "meme -> current -> Mills -> choiceTime - currentTime :" + diff);

                int seconds = (int) (time /1000);
                int hours   = (int) ((time / (1000*60*60)));//시
                Log.d("meme","meme choice - current  second : " + seconds);
                Log.d("meme","meme choice - current  hours: " + hours);

                JobSchedulerStart.start(getApplicationContext(), seconds);


                String date = year + "/" + monthOfYear+ "/" + dayOfMonth;
                Toast.makeText(MainActivity.this, "meme"+choiceTime, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
