package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import es.dmoral.toasty.Toasty;
import static com.example.animation.qrcode.mydatabase;

public class MainActivity extends AppCompatActivity {

    boolean backbutton=false;
    public Button btn;

       EditText emailid,password;
    public FirebaseAuth mAuth;
    static String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase=this.openOrCreateDatabase("Information",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS registeredevents(date VARCHAR,reciept VARCHAR,events VARCHAR,amount_paid int(4))");
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    verify();

            }
        });


        emailid = findViewById(R.id.email);
        password =findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onBackPressed() {
        if(backbutton) {
            super.onBackPressed();
            return;
        }
        this.backbutton=true;
        Toasty.info(this, "Press back again to exit", Toasty.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backbutton=false;
            }
        },2000);
    }

    public void verify()
    {

         m = emailid.getText().toString();
        String n = password.getText().toString();

        if(m.isEmpty())
            Toasty.info(this, "Enter the email", Toasty.LENGTH_SHORT).show();
        else if(n.isEmpty())
            Toasty.info(this, "Enter the password", Toasty.LENGTH_SHORT).show();
        else {
            mAuth.signInWithEmailAndPassword(m, n)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(getApplicationContext(),"Logged in successfully",Toasty.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Details.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toasty.error(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}
