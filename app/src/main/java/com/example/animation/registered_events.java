package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import static com.example.animation.qrcode.mydatabase;



public class registered_events extends AppCompatActivity {

    public static boolean flag=true;
    public static ListView listView;
        public static ArrayAdapter arrayAdapter;
    public static ArrayList<String>arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_events);

        listView=findViewById(R.id.listview);
        mydatabase=this.openOrCreateDatabase("Information",MODE_PRIVATE,null);
        Cursor c=mydatabase.rawQuery("SELECT * FROM registeredevents",null);

        int dateindex=c.getColumnIndex("date");
        int eventindex=c.getColumnIndex("events");
        int recieptindex=c.getColumnIndex("reciept");
        int totalindex=c.getColumnIndex("amount_paid");
       arr=new ArrayList<>();
        c.moveToFirst();
        if (c!= null) {

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                  String d,n,e,r,p;
                  int t;
                  d=c.getString(dateindex);
                  e=c.getString(eventindex);
                  r=c.getString(recieptindex);
                  t=c.getInt(totalindex);
               arr.add("\nDATE AND TIME : "+"\t"+d+"\n"+"RECEIPTNO. : "+"\u0009"+r+"\n"+"AMOUNT PAID : \t"+t+"\n"+"EVENTS TAKEN : \t"+e);
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);

                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height =550;                                                                     //view height
                view.setLayoutParams(params);

                text.setAlpha(0.88f);
                if (flag == true) {
                    text.setTextColor(Color.parseColor("#ffffff"));                                                     //view colour
                }
                text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);                                   //text size in view
                text.setGravity(Gravity.LEFT);
                if(position %2 == 1)
                {
                    view.setBackgroundColor(Color.parseColor("#373635"));                       //view background
                }
                else
                {
                    view.setBackgroundColor(Color.parseColor("#5F5F5F"));
                }

                return view;
            }
        };

        listView.setAdapter(arrayAdapter);
        //mydatabase.execSQL("Delete from registeredevents");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
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
