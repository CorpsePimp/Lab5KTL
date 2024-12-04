package com.example.lab5ktl
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private val sharedPrefName = "MySharedPrefs"
    private val sharedPrefKey = "savedText"
    private val fileName = "saved_text.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        findViewById<Button>(R.id.saveSharedPrefButton).setOnClickListener {
            saveToSharedPreferences()
        }

        findViewById<Button>(R.id.loadSharedPrefButton).setOnClickListener {
            loadFromSharedPreferences()
        }

        findViewById<Button>(R.id.saveToFileButton).setOnClickListener {
            saveToFile()
        }

        findViewById<Button>(R.id.loadFromFileButton).setOnClickListener {
            loadFromFile()
        }
    }

    private fun saveToSharedPreferences() {
        val sharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(sharedPrefKey, editText.text.toString())
        editor.apply()
        textView.text = "Сохранено в  SharedPreferences."
    }

    private fun loadFromSharedPreferences() {
        val sharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val loadedText = sharedPreferences.getString(sharedPrefKey, "No data found")
        textView.text = "Загружено из SharedPreferences: $loadedText"
    }

    private fun saveToFile() {
        val file = File(filesDir, fileName)
        file.writeText(editText.text.toString())
        textView.text = "Сохранено в внешние файлы: ${file.absolutePath}"
    }

    private fun loadFromFile() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            val loadedText = file.readText()
            textView.text = "Загружено из внешних файлов: $loadedText"
        } else {
            textView.text = "Файлы не найдены"
        }
    }
}
