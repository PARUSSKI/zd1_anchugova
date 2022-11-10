package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.SharedPreferences
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.sql.Types.NULL
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    public final var APP_PREFERENCES = "mysettings"

    lateinit var settings:SharedPreferences
    lateinit var button1: Button
    lateinit var editPass:EditText
    lateinit var editLog:EditText
    var hasVisited:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get preferences
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        // get and check login
        val login : String = settings.getString("login", "") ?: ""
        hasVisited = login.isNotEmpty()

        // get views
        button1 = findViewById(R.id.button)
        editPass = findViewById((R.id.editPass))
        editLog = findViewById((R.id.editLog))


        if(hasVisited)
        {
            editLog.setText(login)
        }
        else
        {
            button1.text = "Сохранить"
        }
    }

    fun NextButton(view: View) {
        val hasLogin = editLog.text.toString().isNotEmpty()
        val hasPassword = editPass.text.toString().isNotEmpty()

        when {
            !hasLogin || !hasPassword ->
                AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Незаполнены поля!!!")
                    .setPositiveButton("Ок",null)
                    .create()
                    .show()
            !hasVisited -> {
                val login = editLog.getText().toString()
                val password = editPass.getText().toString()
                val prefEditor = settings.edit()
                prefEditor.putString("login", login)
                prefEditor.putString("password", password)
                prefEditor.apply()
            }
            else -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity((intent))
            }
        }
    }

    fun clear(view: View) {
        val e: SharedPreferences.Editor = settings.edit()
        e.clear()
        e.apply()
    }
}