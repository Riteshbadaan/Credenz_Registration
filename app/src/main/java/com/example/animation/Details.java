package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Details extends AppCompatActivity {

     static Spinner spinner ;
   public  Button btn;
   public  EditText par1,par2,par3,par4,phone1,phone2,email1,email2;

    public static int flag,nofp;
    static  String p1,p2,p3,p4,ph1,ph2,e1,e2,id,college,receiptno,year;
    public boolean but = false;

    public  String MobilePattern = "[0-9]{10}";
   public  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        flag=0;
        nofp=0;
        year="FE";
        btn=findViewById(R.id.detailbutton);
        par1=findViewById(R.id.editText);
        par2=findViewById(R.id.editText2);
        par3=findViewById(R.id.editText3);
        par4=findViewById(R.id.editText4);

        phone1=findViewById(R.id.editText5);
        phone2=findViewById(R.id.editText6);
        email1=findViewById(R.id.editText7);
        email2=findViewById(R.id.editText8);
        spinner=findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Select a College   ---------");
        list.add("Pune Institute Of Computer Technology");
        list.add("AISSMS College of Engineering");
        list.add("AISSMS Institute of Information Technology");
        list.add("Army Institute of Technology,(AIT)");
        list.add("Bharati Vidyapeeth Deemed University");
        list.add("College Of Engineering Pune");
        list.add("D.Y. Patil College Of Engineering Akurdi");
        list.add("D.Y. Patil College Of Engineering Pimpri");
        list.add("Govt. Polytechnic ");
        list.add("Indian Institute of Information Technology,(IIIT)");
        list.add("JSPM's Jayawantrao Sawant College of Engineering");
        list.add("MIT College of Engineering");
        list.add("MIT Arts, Commerce & Science College,Alandi");
        list.add("Modern Education Society's College of Engineering");
        list.add("MKSSS's Cummins College of Engineering");
        list.add("P.E.S. Modern College of Engineering");
        list.add("Pune Vidhyarthi Griha's College of Engineering and Technology");
        list.add("Pimpri Chinchwad College Of Engineering,(PCCOE)");
        list.add("RMD Sinhgad School of Engineering");
        list.add("Sinhgad Academy Of Engineering");
        list.add("Sinhgad Institute of Technology and Science");
        list.add("Sinhgad College of Architecture");
        list.add("Sinhgad College of Engineerting");
        list.add("Symbiosis College");
        list.add("Symbiosis Institute of Business Management");
        list.add("Symbiosis Institute of Management Studies");
        list.add("Symbiosis Institute of Technology,(SIT)");
        list.add("Trinity College of Engineering and Research");
        list.add("Vishwakarma Institute of Technology");
        list.add("Vishwakarma Institute of Information Technology,(VIIT)");
        list.add("Zeal Institute of Management and Computer Application");
        list.add("Others");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        RadioGroup radioGroup2=findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radiogroup2=radioGroup.getCheckedRadioButtonId();
                    if(radiogroup2==R.id.radioButton3)
                    year="FE";
                else if(radiogroup2==R.id.radioButton4)
                    year="SE";
                else if(radiogroup2==R.id.radioButton5)
                    year="TE";
                else if(radiogroup2==R.id.radioButton6)
                    year="BE";
            }
        });

        RadioGroup radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               int radioid=radioGroup.getCheckedRadioButtonId();
               if(radioid==R.id.radioButton)
                   flag=1;
               else if(radioid==R.id.radioButton2)
                   flag=0;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void register()
    {

        college = spinner.getSelectedItem().toString();
         p1 = par1.getText().toString();
         p2 = par2.getText().toString();
         p3 = par3.getText().toString();
         p4 = par4.getText().toString();


         ph1 = phone1.getText().toString();
         ph2 = phone2.getText().toString();
         e1 = email1.getText().toString();
         e2 = email2.getText().toString();

        if(!p1.isEmpty())
            nofp=1;
        if(!p2.isEmpty())
            nofp=2;
        if(!p3.isEmpty())
            nofp=3;
        if(!p4.isEmpty())
            nofp=4;

        if(par1.getText().toString().isEmpty())
            Toasty.info(Details.this, "Enter the name of 1st Participant", Toasty.LENGTH_SHORT).show();
               else if (phone1.getText().toString().isEmpty()||!(phone1.getText().toString().matches(MobilePattern)))
                    Toasty.info(getApplicationContext(), "Enter the Valid Phone Number1", Toasty.LENGTH_SHORT).show();
               else if (email1.getText().toString().isEmpty()||!(email1.getText().toString().matches(emailPattern)))
                    Toasty.info(getApplicationContext(), "Enter the valid Email1", Toasty.LENGTH_SHORT).show();
               else if(!(phone2.getText().toString().isEmpty())&&!(phone2.getText().toString().matches(MobilePattern)))
                    Toasty.info(getApplicationContext(), "Enter the Valid Phone Number2", Toasty.LENGTH_SHORT).show();
               else if(!(email2.getText().toString().isEmpty())&&!(email2.getText().toString().matches(emailPattern)))
                   Toasty.info(getApplicationContext(), "Enter the Valid Email2", Toasty.LENGTH_SHORT).show();
               else if(college.equals("Select a College   ---------"))
                    Toasty.info(getApplicationContext(), "Select a College", Toasty.LENGTH_SHORT).show();

        else {
            Intent intent = new Intent(getApplicationContext(), animation.class);
            startActivity(intent);
        }
        Log.i("number",String.valueOf(nofp));
    }

    @Override
    public void onBackPressed() {
        if(but) {
            super.onBackPressed();
            finishAffinity();
            finish();
            return;
        }
        this.but=true;
        Toasty.info(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                but=false;
            }
        },2000);
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
