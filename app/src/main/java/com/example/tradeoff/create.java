package com.example.tradeoff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create extends AppCompatActivity {

    EditText Fname;
    EditText Lname;
    EditText email;
    EditText password;
    EditText phone;
    EditText activ;
    EditText adress;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Fname= (EditText)findViewById(R.id.FirstName);
        Lname=(EditText)findViewById(R.id.LastName);
        email= (EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.Password);
        phone= (EditText)findViewById(R.id.Phone);
        activ=(EditText)findViewById(R.id.Work);
        adress= (EditText)findViewById(R.id.Address);

    }

    public void connect(View view) {
        startActivity(new Intent(this, user.class));
    }

    public void create(View view) {

        String s = email.getText().toString().trim();
        String empty="";
        for(int i=0 ; i<s.length(); i ++){
            if(s.charAt(i)!='.'){
                empty+=s.charAt(i);
            }else{
                empty+='_';
            }
        }
        System.out.println(empty);
        s.replaceAll(".","_");
        if (s.length() >20) {
            Toast.makeText(getApplicationContext(), "is number not 10 char", Toast.LENGTH_LONG).show();
        } else {
            details a = new details(
                    Fname.getText().toString().trim(),
                    Lname.getText().toString().trim(),
                    empty,
                    password.getText().toString().trim(),
                    phone.getText().toString().trim(),
                    activ.getText().toString().trim(),
                    adress.getText().toString().trim());

            String b = a.getEmail();
            mDatabase.child(b).setValue(a)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Write was successful!
                            // ...
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            // ...
                        }
                    });
        }
    }
}
