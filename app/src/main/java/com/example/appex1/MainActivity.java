package com.example.appex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonconfig = findViewById(R.id.button_config);
        Button buttonexit = findViewById(R.id.button_exit);
        
        buttonconfig.setOnClickListener(this);
        buttonexit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_config) {
            Intent i = new Intent(this, ConfigActivity.class);
            startActivity(i);
        } else {
            finish();
        }
    }
}
