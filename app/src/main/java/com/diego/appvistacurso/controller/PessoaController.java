package com.diego.appvistacurso.controller;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.diego.appvistacurso.R;
import com.diego.appvistacurso.model.Pessoa;
import com.diego.appvistacurso.view.MainActivity;

import java.util.List;


public class PessoaController {

    SharedPreferences preferences;
    public static final String NONE_PREFERENCES = "pref_listvip";
    SharedPreferences.Editor listaVip;

    public PessoaController(MainActivity mainActivity){
        preferences = mainActivity.getSharedPreferences(NONE_PREFERENCES, 0);
        listaVip = preferences.edit();
    }

    @Override
    public String toString() {

        Log.d("MVC_CONTROLLER", "CONTROLLER INICIADA");

        return super.toString();
    }



    public void salvar(Pessoa pessoa) {

        Log.d("MVC_Controller", "Salvo: " + pessoa.toString());

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("SobreNome", pessoa.getSobreNome());
        listaVip.putString("CursoDesejado", pessoa.getNomeCurso());
        listaVip.putString("TelefoneContato", pessoa.getTelefoneContato());
        listaVip.apply();
    }

    public void limpar() {
        listaVip.clear();
        listaVip.apply();
    }


    public Pessoa buscar(Pessoa pessoa){
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", "NA"));
        pessoa.setSobreNome(preferences.getString("SobreNome", "NA"));
        pessoa.setNomeCurso(preferences.getString("CursoDesejado", "NA"));
        pessoa.setTelefoneContato(preferences.getString("TelefoneContato", "NA"));
        return pessoa;
    }

}


