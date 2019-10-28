package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Report_a_bug extends AppCompatActivity {

    TextView t18,t20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_a_bug);
        t18=findViewById(R.id.textView18);
        t20=findViewById(R.id.textView20);
        t18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse("tel:+91 8087347255");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(Report_a_bug.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse("tel:+91 9469087466");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(Report_a_bug.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
