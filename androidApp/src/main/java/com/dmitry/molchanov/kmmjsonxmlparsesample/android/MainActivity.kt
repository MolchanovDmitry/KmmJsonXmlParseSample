package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.loadTitleFromJson()

        setTextViewText(R.id.text_view, vm.greeting)

        vm.jsonTitle.bind { result ->
            val text = result?.let { "Json: $it" } ?: "error"
            setTextViewText(R.id.json_view, text)
        }
    }

    private fun setTextViewText(id: Int, text: String) {
        findViewById<TextView>(id).text = text
    }
}
