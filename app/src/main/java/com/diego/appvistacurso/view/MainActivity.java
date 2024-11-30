package com.diego.appvistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.diego.appvistacurso.R;
import com.diego.appvistacurso.controller.CursoController;
import com.diego.appvistacurso.controller.PessoaController;
import com.diego.appvistacurso.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PessoaController controller;
    CursoController cursoController;
    Pessoa pessoa;
    List<String> nomesCursos;
    Spinner edit_spinner;
    EditText edit_primeiro_nome;
    EditText edit_sobrenome;
    EditText edit_curso_desejado;
    EditText edit_telefone_contato;
    Button btn_limpar;
    Button btn_finalizar;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PessoaController(MainActivity.this);
        controller.toString();

        cursoController = new CursoController();
        nomesCursos = cursoController.dadosSpinner();

        pessoa = new Pessoa();
        controller.buscar(pessoa);

        edit_primeiro_nome = findViewById(R.id.edit_primeiro_nome);
        edit_sobrenome = findViewById(R.id.edit_sobrenome);
        edit_curso_desejado = findViewById(R.id.edit_curso_desejado);
        edit_telefone_contato = findViewById(R.id.edit_telefone_contato);

        edit_spinner = findViewById(R.id.edit_spinner);

        edit_primeiro_nome.setText(pessoa.getPrimeiroNome());
        edit_sobrenome.setText(pessoa.getSobreNome());
        edit_curso_desejado.setText(pessoa.getNomeCurso());
        edit_telefone_contato.setText(pessoa.getTelefoneContato());

        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_finalizar= findViewById(R.id.btn_finalizar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosSpinner());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        edit_spinner.setAdapter(adapter);

        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_primeiro_nome.setText(" ");
                edit_sobrenome.setText(" ");
                edit_curso_desejado.setText(" ");
                edit_telefone_contato.setText(" ");

                controller.limpar();
            }
        });

        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Volte Sempre",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(edit_primeiro_nome.getText().toString());
                pessoa.setSobreNome(edit_sobrenome.getText().toString());
                pessoa.setNomeCurso(edit_curso_desejado.getText().toString());
                pessoa.setTelefoneContato(edit_telefone_contato.getText().toString());

                Toast.makeText(MainActivity.this, "Salvo: " +
                        pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);
            }
        });

    }

}