package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia"; // Constante e imutavel (static = valor sempre o mesmo para qualquer instancia)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0 );// mode 0 = so o app salva e le o arquivo
                SharedPreferences.Editor editor = preferences.edit(); // objeto para editar os arquivos de preferencia

                //Validar o Nome
                if(editNome.getText().toString().equals("")){ // verifica se o campo nome esta vazio
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                }else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit(); // salva os dados
                    textResultado.setText("Olá, " + nome);
                }
            }
        });
        //Recuperar os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0 );

        //Validar se temos o nome em preferencias
        if (preferences.contains("nome")){

            String nome = preferences.getString("nome", "Usuário não Definido");
            textResultado.setText("Olá, " + nome);
        }else {
            textResultado.setText("Olá, Usuário não Definido ");
        }
    }
}