package com.example.tradeoff;
//administrator
//stamm
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Administrator extends AppCompatActivity {
Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        home=(Button)findViewById(R.id.HomePage);
        home.setText("Home");
    }

    public void HomePage(View view) {
        Intent i = new Intent(Administrator.this, Home.class);
        Bundle extras = getIntent().getExtras();
        i.putExtra("email",extras.getString("email"));
        startActivity(i);
        System.out.println("Check")
    }

    public void Change(View view) {
        Intent i = new Intent(Administrator.this, changeCategory.class);
        Bundle extras = getIntent().getExtras();
        i.putExtra("email",extras.getString("email"));
        startActivity(i);
    }

    public void delete_post(View view) {
        Intent i = new Intent(Administrator.this, Delete_Post_Admin.class);
        Bundle extras = getIntent().getExtras();
        i.putExtra("email",extras.getString("email"));
        startActivity(i);
    }

    public void diagramRegion(View view) {
        Intent i = new Intent(Administrator.this, DiagramRegion.class);
        Bundle extras = getIntent().getExtras();
        i.putExtra("email",extras.getString("email"));
        startActivity(i);
    }

    public void diagramActivity(View view) {
        Intent i = new Intent(Administrator.this, DiagramActivity.class);
        Bundle extras = getIntent().getExtras();
        i.putExtra("email",extras.getString("email"));
        startActivity(i);
    }
}
