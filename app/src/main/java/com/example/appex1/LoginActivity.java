package com.example.appex1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        showLoginDialog();
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.activity_login, null);

        builder.setView(dialogView);
        builder.setTitle("Login");
        builder.setCancelable(false);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextPassword = dialogView.findViewById(R.id.editTextPassword);
        Button buttonEntrar = dialogView.findViewById(R.id.button_entrar);
        Button buttonSair = dialogView.findViewById(R.id.button_sair);

        final AlertDialog dialog = builder.create();

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = editTextName.getText().toString();
                String inputPassword = editTextPassword.getText().toString();

                String usernameRecuperado = sharedPreferences.getString("username", "");
                String passwordRecuperado = sharedPreferences.getString("password", "");

                if (inputName.equals(usernameRecuperado) && inputPassword.equals(passwordRecuperado)) {
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    dialog.dismiss();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Nome de usuário ou senha inválidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }
}