package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel.AndroidMainViewModel

class MainActivity : AppCompatActivity() {

    private val vm = AndroidMainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Печатаем приветствие
        setTextViewText(R.id.text_view, vm.greeting)

        // Запрашиваем url c XML
        vm.getTotalPriceFromXml()
        // Подписываемся на изменение значения общей суммы из распаршенного XML
        vm.totalPrice.bind { totalPrice ->
            val text = "Total price: $totalPrice"
            setTextViewText(R.id.xml_view, text)
        }

        // Запрашиваем url с JSON
        vm.loadTitleFromJson()
        // Подписываемся на изменение значения заголовка из распаршенного JSON
        vm.jsonTitle.bind { result ->
            val text = result?.let { "Json: $it" } ?: "error"
            setTextViewText(R.id.json_view, text)
        }

        val editText = findViewById<EditText>(R.id.edit_text)
        // Подписываемся на изменение значения из БД
        vm.dbValue.bind(editText::setText)
        // Берем из поля значение и записываем в БД
        findViewById<Button>(R.id.set_db_value).setOnClickListener {
            vm.setDbValue(editText.text.toString())
        }
        // Запрос на чтение из БД
        findViewById<Button>(R.id.get_db_value_button).setOnClickListener {
            vm.getDbValue()
        }
    }

    private fun setTextViewText(id: Int, text: String) {
        findViewById<TextView>(id).text = text
    }
}
