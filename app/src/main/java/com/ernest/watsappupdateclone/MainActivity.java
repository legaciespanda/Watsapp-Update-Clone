package com.ernest.watsappupdateclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ZonedDateTime zonedDateTime;
    private SimpleDateFormat sdf;
    private Date today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ZonedDateTime getZonedDateTimeNow(){
        return ZonedDateTime.parse(String.valueOf(zonedDateTime.now()));
    }

    @TargetApi(26)
    private ZonedDateTime getZoneDateTime30DaysAgo(){
        return zonedDateTime.plusDays(-30);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isUpto30days(){
        if (getZonedDateTimeNow().isBefore(getZoneDateTime30DaysAgo()))
            return true;
        else{
            return false;
        }
    }

//    @TargetApi(24)
//    private String today(){
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//        today = new Date();
//        return String.valueOf(today);
//    }
//
//    private String thirtydays(){
//        Calendar thirtDaysAgo = Calendar.getInstance();
//        thirtDaysAgo.add(Calendar.DAY_OF_MONTH, -30);
//        return sdf.format(thirtDaysAgo);
//    }

}
