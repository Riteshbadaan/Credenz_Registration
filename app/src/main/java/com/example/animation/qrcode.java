package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import es.dmoral.toasty.Toasty;
import static com.example.animation.Details.college;
import static com.example.animation.Details.e1;
import static com.example.animation.Details.e2;
import static com.example.animation.Details.p1;
import static com.example.animation.Details.p2;
import static com.example.animation.Details.p3;
import static com.example.animation.Details.p4;
import static com.example.animation.Details.ph1;
import static com.example.animation.Details.ph2;
import static com.example.animation.Details.year;
import static com.example.animation.adapter.total;
import static com.example.animation.animation.evnt;
import static com.example.animation.animation.evnt1;



public class qrcode extends AppCompatActivity {

    public ImageView imgqr;
    public static Button btn;
    public static String receiptno;
    public Random ran=new Random();
    public DatabaseReference reference,reference1,dr;
    public String rno;
    int id;
    String mydate;
    public boolean button = false;
    static SQLiteDatabase mydatabase;
    int uni=1,try1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        receiptno="CREDENZ-2K19-";
        btn=findViewById(R.id.end);
        imgqr=findViewById(R.id.qrcode);

        mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String abcd =  MainActivity.m;
        String as ="";

        for(int i=0;i<abcd.length();i++)
        {
            if(abcd.charAt(i)=='.'||abcd.charAt(i)=='#')
            {

            }
            else
            {
                as+=abcd.charAt(i);
            }
        }

        reference = FirebaseDatabase.getInstance().getReference("Events");
        reference1 = FirebaseDatabase.getInstance().getReference(as);

        generate();
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try
        {
            BitMatrix bitMatrix=multiFormatWriter.encode(receiptno, BarcodeFormat.QR_CODE,280,280);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            imgqr.setImageBitmap(bitmap);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            Log.i("phone",ph1);
            mydatabase=this.openOrCreateDatabase("Information",MODE_PRIVATE,null);

               mydatabase.execSQL("CREATE TABLE IF NOT EXISTS registeredevents(date VARCHAR,reciept VARCHAR,events VARCHAR,amount_paid int(4))");
                mydatabase.execSQL("INSERT INTO registeredevents (date,reciept,events,amount_paid)  VALUES("+"'"+mydate+"',"+"'"+receiptno+"',"+"'"+evnt+"',"+total+")");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),Details.class);
                startActivity(intent);
            }
        });

    }

    public void generate()
    {
        id=1+ran.nextInt(50000);
        rno = String.valueOf(id);
        receiptno="CREDENZ-2K19-";
        receiptno = receiptno + rno;
        Log.i("receipt",receiptno);
        dr=FirebaseDatabase.getInstance().getReference().child("Events");
        Query query=dr.orderByChild("receiptno").equalTo(receiptno);
        query.addValueEventListener(valueEventListener);
    }
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists())
            {
                Log.i("try",String.valueOf(try1));
                if(try1==0) {
                    Log.i("duplicate",String.valueOf(id));
                    uni=0;
                    generate();
                }
            }
            else
            {
                Log.i("unique value set",String.valueOf(id));
                Details.id =  reference.push().getKey();
                events eve = new events(Details.id,mydate,p1,p2,p3,p4,ph1,ph2,e1,e2,evnt1,receiptno,college,year,total);
                reference.child(Details.id).setValue(eve);
                reference1.child(Details.id).setValue(eve);
                sendMail();
                try1=1;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @Override
    public void onBackPressed() {
        if(button)
        {
            super.onBackPressed();
            return;
        }

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




    protected void sendMail() {
        final String username = "Credenz19@gmail.com";
        final String password = "infinity2018";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(e1));
            message.setSubject("Greetings from PICT IEEE Student Branch!");
            message.setText("Dear Participant,\n\nWe have received your registration and payment. Kindly note the following details pertaining to your registration.\n\n"+
                    "Receipt number:"+receiptno+"\nRegistered events:"+evnt1+"\n\nYou can select your slots for the respective events " +
                    "on our website:\ncredenz.in\n\nEvent details:\nDate: September 20th, 21st, 22nd, 2019\nVenue: Pune Institute of Computer Technology\n\n"+
                    "Check out our website for further details on the registered events.\nFor any questions leading up to the event,feel free "
                    +"to reply to this email.\n\nThank you for your registration. We look forward to seeing you there!\n\nFollow us on:\nFackbook:"+
                    "www.facebook.com/pisb.credenz\nInstagram:www.instagram.com/pisbcredenz");

            new SendMailTask().execute(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }

    private class SendMailTask extends AsyncTask<Message,String, String> {
       // private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressDialog = ProgressDialog.show(getApplicationContext(),null, "Sending mail", true, false);
        }



        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            }
            catch(SendFailedException ee)
            {
                return "error1";
            }catch (MessagingException e) {
                e.printStackTrace();
                return "error2";
            }

        }


        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Success"))
            {

                super.onPostExecute(result);
               // progressDialog.dismiss();
                Toasty.success(getApplicationContext(), "Mail Sent Successfully", Toasty.LENGTH_LONG).show();

            }
            else
            if(result.equals("error1"))
                Toasty.error(getApplicationContext(), "Email Failure", Toasty.LENGTH_LONG).show();
            else
            if(result.equals("error2"))
                Toasty.error(getApplicationContext(), "Email Sent problem2", Toasty.LENGTH_LONG).show();

        }
    }


}
