package com.example.tradeoff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;


public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    EditText username;
    EditText pass;
    FirebaseAuth auth;
    int admin_user=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Welcom to my applicition", Toast.LENGTH_SHORT).show();
    }

    public void Admin(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_user);
        username= (EditText)dialog.findViewById(R.id.Username);
        pass=(EditText)dialog.findViewById(R.id.Pass);
        admin_user=-1;
        dialog.show();
    }

    public void connect(View view) {

        String un=username.getText().toString().trim();
        String password=pass.getText().toString().trim();

        System.out.println(un+password);
        auth.signInWithEmailAndPassword(un,password).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    if(admin_user==1) {
                        startActivity(new Intent(MainActivity.this, user.class));
                        Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_LONG).show();
                    }else{
                        startActivity(new Intent(MainActivity.this, Admin.class));
                        Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                    catch (FirebaseAuthEmailException e){
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    }
                    catch (FirebaseAuthException e){
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    public void User(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_user);
        username= (EditText)dialog.findViewById(R.id.Username);
        pass=(EditText)dialog.findViewById(R.id.Pass);
        admin_user=1;
        dialog.show();
    }

    public void create(View view) {
        startActivity(new Intent(this, create.class));
    }
}
