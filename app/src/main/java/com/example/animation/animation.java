package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import es.dmoral.toasty.Toasty;
import static com.example.animation.Details.flag;
import static com.example.animation.adapter.total;



public class animation extends AppCompatActivity {

    List<Model>arr;
    RecyclerView recycler;
    Button btn;
    static int count,count1;

    static int[] pay1={150,50,100,200,150,100,50,50,50,0,200,150,80,200,50,150,0};
    static int[] payieee={120,40,80,180,120,80,40,40,40,0,180,120,60,160,40,120,0};
    static int[] pay={0};
    static String evnt;
    static String evnt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr=new ArrayList<>();
        if(flag==1)
            pay=payieee;
        else if(flag==0)
            pay=pay1;
        count=0;
        count1=0;
        evnt1=" ";
        setContentView(R.layout.activity_animation);
        recycler= findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
            arr.add(new Model(R.drawable.bplan, "B-PLAN", false,3));//3
            arr.add(new Model(R.drawable.quiz, "BIZ-TECH QUIZ", false,2));//2
            arr.add(new Model(R.drawable.clash, "CLASH", false,2));//2
            arr.add(new Model(R.drawable.contraption, "CONTRAPTION", false,4));//4
            arr.add(new Model(R.drawable.cretronix, "CRETRONIX", false,2));//2
            arr.add(new Model(R.drawable.datawizwhite, "DATAWIZ", false,2));//2
            arr.add(new Model(R.drawable.enigma, "ENIGMA", false,2));//2
            arr.add(new Model(R.drawable.quiz, "GENERAL QUIZ", false,2));//2
            arr.add(new Model(R.drawable.quiz, "MELA QUIZ", false,2));//2
            arr.add(new Model(R.drawable.nth, "NTH", false,1));//1
            arr.add(new Model(R.drawable.paper, "PAPER PRESENTATION", false,3));//3
            arr.add(new Model(R.drawable.pixelate, "PIXELATE", false,2));//2
            arr.add(new Model(R.drawable.rc, "RC", false,2));//2
            arr.add(new Model(R.drawable.roboliga, "ROBOLIGA", false,3));//3
            arr.add(new Model(R.drawable.wallstreet, "WALLSTREET", false,1));//1
            arr.add(new Model(R.drawable.web, "WEB WEAVER", false,3));//3
            arr.add(new Model(R.drawable.xodia, "XODIA", false,1));//1
        recycler.setAdapter(new adapter(arr,getApplicationContext()));
        btn=findViewById(R.id.button1);
        total=0;


        new Timer().schedule(new TimerTask() {
                                      public void run() {
                                          runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  btn.setText("Total is "+total);
                                              }
                                          });

                                      }
                                  },
                0, 500);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0) {

                    evnt = "";
                    for (int i = 0; i < arr.size(); i++) {
                        Model model = arr.get(i);
                        if (model.test) {
                            Log.i("Count1",String.valueOf(count1));

                            if(count1>1) {
                                evnt = evnt + model.getDes() + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                                evnt1= evnt1 + model.getDes() + ",";
                            }
                            else {
                                evnt = evnt + model.getDes();
                                evnt1 = evnt1 + model.getDes();
                            }
                            count1--;
                        }
                    }
                        Intent intent = new Intent(getApplicationContext(), qrcode.class);
                        startActivity(intent);
                }
                else
                {
                    Toasty.info(animation.this, "Select an event", Toasty.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.events:
                Intent intent = new Intent(getApplicationContext(),registered_events.class);
                startActivity(intent);
                return true;
            case R.id.contactus:
                Intent intent1 = new Intent(getApplicationContext(),Contactus.class);
                startActivity(intent1);
                return true;
            case R.id.report:
                Intent intent2 = new Intent(getApplicationContext(),Report_a_bug.class);
                startActivity(intent2);
                return true;
            default:return  false;
        }
    }
}
