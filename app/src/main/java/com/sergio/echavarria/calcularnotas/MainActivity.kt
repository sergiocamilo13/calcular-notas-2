package com.sergio.echavarria.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nombreIngresado: EditText
    private lateinit var notasIngresadas: EditText
    private lateinit var porcentajesIngresados: EditText
    private lateinit var siguienteNota: Button
    private lateinit var Finalizar: Button
    private lateinit var progreso: ProgressBar
    private lateinit var vistaPromedioFinal : TextView
    private lateinit var vistaNotaFinal : TextView
    private lateinit var Reiniciar : Button


    var porcentajeAcumulado  = 0

    val listaDePorcentaje: MutableList<Int> = mutableListOf()

    val listaDeNotas: MutableList<Double> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreIngresado = findViewById(R.id.nombre_estudiante)
        notasIngresadas = findViewById(R.id.agregar_nota)
        porcentajesIngresados = findViewById(R.id.porcentaje_nota)
        siguienteNota = findViewById(R.id.siguiente_nota)
        Finalizar = findViewById(R.id.Finalizar)
        progreso = findViewById(R.id.progressBar)
        vistaPromedioFinal = findViewById(R.id.vistaPromedioFinal)
        vistaNotaFinal = findViewById(R.id.vistaNotaFinal)
        Reiniciar = findViewById(R.id.Reiniciar)

        Reiniciar.setOnClickListener {

            vistaNotaFinal.visibility = View.GONE

            vistaPromedioFinal.visibility = View.GONE

            Finalizar.visibility = View.GONE

            Reiniciar.visibility = View.GONE

            nombreIngresado.text.clear()
            notasIngresadas.text.clear()
            porcentajesIngresados.text.clear()
            progreso.progress = 0
            nombreIngresado.isEnabled = true
            vistaPromedioFinal.text = ""
            vistaNotaFinal.text = ""
            porcentajeAcumulado = 0
        }

        Finalizar.setOnClickListener {

            Reiniciar.visibility = View.VISIBLE

            vistaNotaFinal.visibility = View.VISIBLE

            vistaPromedioFinal.visibility = View.VISIBLE

            vistaNotaFinal.text = "nota final : " + porcentajeAcumulado

            vistaPromedioFinal.text = "promedio : " + calcularpromedio()
        }


        siguienteNota.setOnClickListener {

            val nombre = nombreIngresado.text.toString()
            val nota = notasIngresadas.text.toString()
            val porcentaje = porcentajesIngresados.text.toString()


            if (validarVacio(nombre, nota, porcentaje)){
                if (validarNombre(nombre) &&
                    validarNota(nota.toDouble()) &&
                    validarPorcentaje((porcentaje.toInt()))
                ){
                    listaDeNotas.add(nota.toDouble())
                    listaDePorcentaje.add(porcentaje.toInt())

                    porcentajeAcumulado += porcentaje.toInt()

                    actualizarProgress(porcentajeAcumulado)

                    nombreIngresado.isEnabled = false
                    notasIngresadas.text.clear()
                    porcentajesIngresados.text.clear()

                    mostrarMensaje("la nota fue ingresada correctamente")
                }else{
                    mostrarMensaje("verifique los datos ingresados")
                }
            }else{
                mostrarMensaje("Datos incompletos")
            }
        }
    }
    fun actualizarProgress(porcentaje : Int) {
        progreso.progress = porcentaje
        if (porcentaje >= 100){
            Finalizar.isEnabled = true
        }
    }
    fun mostrarMensaje(mensaje : String){
        Toast.makeText(this,
            mensaje,
            Toast.LENGTH_LONG).show()
    }
    fun validarVacio(nombre: String, nota : String, porcentaje: String): Boolean{
        return !nombre.isNullOrEmpty() && !nota.isNullOrEmpty() && !porcentaje.isNullOrEmpty()
    }
    fun validarNombre(nombre : String): Boolean {
        return !nombre.matches(Regex(".*\\d.*"))

    }
    fun validarNota(nota : Double) : Boolean{
        return nota >= 0 && nota <= 5
    }
    fun validarPorcentaje(porcentaje : Int ) : Boolean{
        return porcentajeAcumulado + porcentaje <=100
    }
    fun calcularpromedio(): Double{

        var p = 0.0

        for (n in listaDeNotas){

            p += n

        }
        return Math.round((p/ listaDeNotas.size) * 1000.0) / 1000.0
    }
    fun notafinal(): Double {

        var notaFinal : Double = 0.0

        var contador = 0

        for (n in listaDeNotas){

            notaFinal += (n * listaDePorcentaje[contador]) / 100
            contador++
        }
        return Math.round(notaFinal * 1000.0) / 1000.0
    }
}