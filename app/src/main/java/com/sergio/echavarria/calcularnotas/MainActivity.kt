package com.sergio.echavarria.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var Nombre : EditText
    private lateinit var Notas : EditText
    private lateinit var Porcentajes : EditText
    private lateinit var Retornar : Button
    private lateinit var Finalizar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Nombre = findViewById(R.id.Nombre)
        Notas = findViewById(R.id.Notas)
        Porcentajes = findViewById(R.id.Porcentajes)
        Retornar = findViewById(R.id.Retornar)
        Finalizar = findViewById(R.id.Finalizar)
    }
}