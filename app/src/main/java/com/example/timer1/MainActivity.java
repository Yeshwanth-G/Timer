package com.example.timer1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
public class MainActivity extends AppCompatActivity {
public TextView time;
 long starttime;
Button startbutton;
Button resetbutton;
Button setinput;
long l;
int hours,sec,min,flag1,flag2,flag3;
long mytimeleft;
    float f;
    int flags;
ProgressBar pb;
int count;
    long ihour,isec,imin;
    CountDownTimer countDownTimer;
    boolean timerrunning;
    long timeleft,endtime;
    TextView textview;
    long mystarttime;
    int percentprogress=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=(TextView)findViewById(R.id.time);

       // ed=(EditText) findViewById(R.id.input);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        pb.setProgress(percentprogress);
        setinput=(Button)findViewById(R.id.setinput);
        startbutton=(Button)findViewById(R.id.startbutton);
        resetbutton=(Button)findViewById(R.id.resetbutton);
        setinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }

        });
        updatebutton();
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerrunning) {
                    pausetimer();
                } else
                {
                    if(time.getText().toString().equals("00:00:00"))
                        return;
                    starttimer();
                }
            }
        });
updatetext();
updatebutton();
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
resetimer();
            }
        });
    }

    void starttimer(){

        endtime=System.currentTimeMillis()+timeleft;
        countDownTimer=new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long l) {
                timeleft=l;
                timerrunning=true;
                if(starttime!=0)
                    percentprogress=(int)(((starttime-timeleft+1000)*100)/starttime);
                else percentprogress=0;
                pb.setProgress(100-percentprogress);
                    updatetext();
                    updatebutton();
            }
            @Override
            public void onFinish() {
timerrunning=false;
pb.setProgress(100);
updatebutton();
            }
        }.start();
        updatebutton();
    }
   void pausetimer(){
        countDownTimer.cancel();
        timerrunning=false;
        updatebutton();
   }
   void resetimer(){
        flags=0;
        timeleft=starttime;
        pb.setProgress(100);
        updatetext();
       updatebutton();
   }
   void updatetext(){

       hours=(int)(timeleft/1000)/3600;
       min=(int)((timeleft/1000)%3600)/60;
       sec=(int)((timeleft)/1000)%60;
String s=String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,min,sec);
       time.setText(s);
   }
   void updatebutton(){
        if(timerrunning){
            setinput.setVisibility(View.INVISIBLE);
            resetbutton.setVisibility(View.INVISIBLE);
            startbutton.setVisibility(View.VISIBLE);
            startbutton.setText("pause");

        }
        else{
            //ed.setVisibility(View.VISIBLE);
            setinput.setVisibility(View.VISIBLE);
            startbutton.setText("start");
            startbutton.setVisibility(View.VISIBLE);
            resetbutton.setVisibility(View.VISIBLE);
        }
   }
    public void show()
    {
        flag1=1;
        flag2=1;
        flag3=1;
        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker hourpicker = (NumberPicker) d.findViewById(R.id.numberPicker1);
        hourpicker.setMaxValue(100); // max value 100
        hourpicker.setMinValue(0);   // min value 0
        hourpicker.setWrapSelectorWheel(false);
        final NumberPicker minutepicker = (NumberPicker) d.findViewById(R.id.numberPicker2);
        minutepicker.setMaxValue(60); // max value 100
        minutepicker.setMinValue(0);   // min value 0
        minutepicker.setWrapSelectorWheel(false);
        final NumberPicker secpicker = (NumberPicker) d.findViewById(R.id.numberPicker3);
        secpicker.setMaxValue(60); // max value 100
        secpicker.setMinValue(0);   // min value 0
        secpicker.setWrapSelectorWheel(false);
        hourpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // sec=newV
                flag1=0;
                ihour=newVal*3600000;
               // imin=0;isec=0;
                // updatetext();
            }
        });
        minutepicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // sec=newVal;
                imin=newVal*60000;
                flag2=0;
                //starttime=newVal*1000;
                //timeleft=starttime;
                // updatetext();
            }
        });
        secpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
               isec=newVal*1000;
                // sec=newVal;
                flag3=0;
               // starttime=newVal*1000;
               // timeleft=starttime;
               // updatetext();
            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               // tv.setText(String.valueOf(np.getValue())); //set the value to textview
                if(flag1==1)ihour=0;
                if(flag2==1)imin=0;
                if(flag3==1)isec=0;
                starttime=ihour+imin+isec;
                //timeleft=starttime;
                resetimer();
                updatebutton();
                startbutton.setVisibility(View.VISIBLE);
                percentprogress=100;
                flags=0;
                //updatetext();
                d.dismiss();
                return;
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //timeleft=mytimeleft;
                d.dismiss();
                return;// dismiss the dialog
            }
        });
        d.show();
//timeleft=mytimeleft;

    }
}