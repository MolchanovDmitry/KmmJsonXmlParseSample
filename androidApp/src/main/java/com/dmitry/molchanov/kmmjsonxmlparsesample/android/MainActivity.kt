package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.molchanov.kmmjsonxmlparsesample.domain.XmlInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel.AndroidMainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm = AndroidMainViewModel(this)

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

        val editText = findViewById<EditText>(R.id.edit_text)
        vm.dbValue.bind(editText::setText)
        findViewById<Button>(R.id.set_db_value).setOnClickListener {
            vm.setDbValue(editText.text.toString())
        }

        findViewById<Button>(R.id.get_db_value_button).setOnClickListener {
            vm.getDbValue()
        }
    }

    private fun setTextViewText(id: Int, text: String) {
        findViewById<TextView>(id).text = text
    }
}
