package com.example.myapplication

import android.R.layout.simple_spinner_item
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import java.text.FieldPosition


class MainActivity2 : AppCompatActivity() {
    lateinit var newSet:SharedPreferences
    lateinit var spin:Spinner
    lateinit var text: TextView
    lateinit var ttt:TextView
     var names = arrayListOf<String>("Фильм 1","Фильм 2","Фильм 3")
  var select =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        newSet = getSharedPreferences("mysettings", MODE_PRIVATE)
        text = findViewById(R.id.textView2)
        val login : String = newSet.getString("login", "") ?: ""
        text.setText(login)
        ttt = findViewById(R.id.textfromspin)
        spin = findViewById(R.id.spin)

        var ar = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,names)
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = ar
        spin.onItemSelectedListener =object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, itemSelected: View, selectedPosition: Int,SelectedId: Long) {
                select = names[selectedPosition]
                Toast.makeText(applicationContext, select, Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

}