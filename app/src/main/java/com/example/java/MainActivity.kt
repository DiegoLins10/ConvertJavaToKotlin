package com.example.java

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.os.Bundle
import com.example.java.R
import android.content.SharedPreferences
import com.example.java.MainActivity
import android.content.SharedPreferences.Editor
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnSalvar: Button
    private lateinit var txtNome: EditText
    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNome = findViewById(R.id.txtNome)
        btnSalvar = findViewById(R.id.btnSalvar)
        txtResultado = findViewById(R.id.txtResultado)

        btnSalvar.setOnClickListener(View.OnClickListener { //CRIANDO O ARQUIVO LOCAL (XML)
            val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,
                    0)
            val editor = preferences.edit()
            //validar nome
            if (txtNome.getText().toString() == "") {
                Toast.makeText(applicationContext, "Preencha seu nome",
                        Toast.LENGTH_LONG).show()
            } else {
                val nome = txtNome.getText().toString()
                editor.putString("nome", nome)
                editor.commit()
                txtResultado.setText("Olá, $nome")
                txtNome.setText("")
            }
        })
        //recuperar dados salvos
        val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,
                0)
        if (preferences.contains("nome")) {
            val nome = preferences.getString("nome",
                    "Olá, usuário não definido")
            txtResultado.setText("Olá, $nome")
        } else {
            txtResultado.setText("Olá, usuário não definido")
        }
    }

    companion object {
        private const val ARQUIVO_PREFERENCIA = "ArquivoPreferencia"
    }
}