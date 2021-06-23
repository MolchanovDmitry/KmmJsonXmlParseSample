package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.molchanov.kmmjsonxmlparsesample.domain.XmlInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.loadTitleFromJson()

        setTextViewText(R.id.text_view, vm.greeting)

        GlobalScope.launch {
            val xml = XmlInteractor()
            val totalPrice = xml.getTotalPriceFromXml()
            val text = "Total price: $totalPrice"
            setTextViewText(R.id.xml_view, text)
        }

        vm.jsonTitle.bind { result ->
            val text = result?.let { "Json: $it" } ?: "error"
            setTextViewText(R.id.json_view, text)
        }
    }

    private fun setTextViewText(id: Int, text: String) {
        findViewById<TextView>(id).text = text
    }
}
