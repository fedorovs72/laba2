package com.example.lab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    private fun disc(a_variable: Double, b_variable: Double, c_variable: Double) =
        (b_variable*b_variable) - (4 * a_variable * c_variable)


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun score(view: View){

        //Проверка на корректность ввода
        try{
            var a_variable : Double = findViewById<EditText>(R.id.editTextA).text.toString().toDouble()
            var b_variable : Double = findViewById<EditText>(R.id.editTextB).text.toString().toDouble()
            var c_variable : Double = findViewById<EditText>(R.id.editTextC).text.toString().toDouble()
            val variables = listOf(a_variable, b_variable, c_variable)
            //String.format("%.2f", x1(a,b,c).toString())

            if((a_variable * b_variable * c_variable) != 0.0){
                if(disc(a_variable,b_variable,c_variable) < 0){
                    resultScore = "Нет корней (Дискриминант < 0)"
                }
                else{
                    if(disc(a_variable,b_variable,c_variable) == 0.0){
                        resultScore = "х1 = x2 = " + normalFormat(x1_variable(a_variable,b_variable,c_variable))
                    }
                    else{
                        resultScore = "х1 = " + normalFormat(x1_variable(a_variable,b_variable,c_variable)) +
                                    "\nх2 = " + normalFormat(x2_variable(a_variable,b_variable,c_variable))
                    }
                }
            }
            else {
                if(a_variable == 0.0 && b_variable == 0.0 && c_variable != 0.0){
                    resultScore = "Нет корней (с!=0)"
                }
                else if(a_variable != 0.0 && b_variable != 0.0 && c_variable == 0.0){
                    resultScore = "х1 = 0\nх2 = " + normalFormat((-b_variable/a_variable)).toString()
                }
                else if(a_variable == 0.0 && b_variable != 0.0){
                    resultScore = "Уравнение линейно \n x = "+ normalFormat((-c_variable / b_variable)).toString()
                }
                else if(variables.sum() == 0.0){
                    resultScore = "Верно при любом х"
                }

            }
            Toast.makeText(applicationContext,resultScore,Toast.LENGTH_LONG).show()
        }
        catch (e : Exception){
            Toast.makeText(applicationContext,"Проверьте корректность введенных данных",Toast.LENGTH_LONG).show()
        }
    }
    var resultScore = ""
    fun normalFormat(x : Double): Any {
        if(x == x.roundToInt().toDouble())
            return x.roundToInt()
        else
            return String.format("%.2f", x)
    }
    fun x1_variable(a_variable: Double, b_variable: Double, c_variable: Double) =
        ((-b_variable + sqrt(disc(a_variable,b_variable,c_variable))) / (2 * a_variable))
    fun x2_variable(a_variable: Double, b_variable: Double, c_variable: Double) =
        ((-b_variable - sqrt(disc(a_variable,b_variable,c_variable))) / (2 * a_variable))
}