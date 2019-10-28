package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contactus extends AppCompatActivity {

    TextView t13,t15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        t13=findViewById(R.id.textView13);
        t15=findViewById(R.id.textView15);
        t13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse("tel:+91 7798401391");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(Contactus.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse("tel:+91 8999412539");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(Contactus.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
