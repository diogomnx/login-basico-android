package com.example.appex1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword2 = findViewById(R.id.editTextPassword2);

        // Carrega os dados salvos e exibe nos campos
        loadPreferences();

        Button buttonConfirm = findViewById(R.id.button_confirm);
        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonConfirm.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    private void loadPreferences() {
        String usernameRecuperado = sharedPreferences.getString("username", "");
        String passwordRecuperado = sharedPreferences.getString("password", "");
        editTextUserName.setText(usernameRecuperado);
        editTextPassword.setText(passwordRecuperado);
        editTextPassword2.setText(passwordRecuperado);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {
            savePreferences();
        } else if (v.getId() == R.id.button_cancel) {
            finish();
        }
    }

    private void savePreferences() {
        String newUsername = editTextUserName.getText().toString().trim();
        String newPassword = editTextPassword.getText().toString();
        String newPassword2 = editTextPassword2.getText().toString();

        if (newUsername.isEmpty()) {
            Toast.makeText(this, "O nome de usuário não pode ser vazio.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPassword.length() < 8) {
            Toast.makeText(this, "A senha deve possuir no mínimo 8 caracteres.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(newPassword2)) {
            Toast.makeText(this, "As senhas não conferem.", Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString("username", newUsername);
        sharedPreferencesEditor.putString("password", newPassword);
        sharedPreferencesEditor.apply();

        Toast.makeText(this, "Configurações salvas com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}