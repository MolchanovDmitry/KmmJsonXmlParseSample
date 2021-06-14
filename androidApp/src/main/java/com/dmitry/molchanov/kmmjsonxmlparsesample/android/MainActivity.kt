package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmitry.molchanov.kmmjsonxmlparsesample.Greeting
import android.widget.TextView
import androidx.activity.viewModels
import com.dmitry.molchanov.kmmjsonxmlparsesample.JsonInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.NetworkRepository

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainViewModel> {
        val repositry = NetworkRepository()
        val interacotor = JsonInteractor(repositry)
        MainViewModelFactory(interacotor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        vm.getTitleFromJson { title ->
            val text = "Json title field: $title"
            findViewById<TextView>(R.id.json_view).text = text
        }
    }
}
