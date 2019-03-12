package com.example.zhongdun.verifydemo.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.MainActivity;
import com.example.a7invensun.verifydemo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmClockActivity extends AppCompatActivity {
    private static final String TAG = "AlarmClockActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
    }

    public void alarmClockClick(View view) {
//        Calendar instance = Calendar.getInstance();
//        new TimePickerDialog( this, 0, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Intent intent = new Intent(AlarmClockActivity.this, HintActivity.class);
//                PendingIntent pi = PendingIntent.getActivity(AlarmClockActivity.this,0,intent,0);
//                Calendar c = Calendar.getInstance();
//                c.setTimeInMillis(System.currentTimeMillis());
//                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
//                c.set(Calendar.MINUTE,minute);
//                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
//                Log.e(TAG, "onTimeSet: "+c.getTimeInMillis() );
//                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
//                Toast.makeText(AlarmClockActivity.this,"成功",Toast.LENGTH_LONG).show();
//
//
//            }
//        },instance.get(Calendar.HOUR_OF_DAY),instance.get(Calendar.MINUTE),false).show();
        List<String> list = new ArrayList();
        list.add("2019-03-08 23:59:59");
        list.add("2019-03-08 23:59:58");
        list.add("2019-03-08 23:59:57");
        list.add("2019-03-08 23:59:58");
        list.add("2019-03-08 23:59:59");
        list.add("2019-03-08 23:59:53");
        list.add("2019-03-08 23:59:54");
        list.add("2019-03-08 23:59:56");
        list.add("2019-03-08 23:59:50");
        list.add("2019-03-09 00:00:00");
        list.add("2019-03-09 00:00:01");

        for (int i = 0; i < list.size(); i++) {
            if ("2019-03-08 23:59:59".compareTo(list.get(i))>=0){
                Log.e(TAG, "alarmClockClick: "+list.get(i) );
            }
        }
    }

}
