package com.example.currencyconverter

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencyconvert.R

class MainActivity : AppCompatActivity() {

    lateinit var convertFromDropdownTextView: TextView
    lateinit var convertToDropdownTextView: TextView
    lateinit var conversionRateText: TextView
    lateinit var amountToConvert: EditText
    lateinit var toDialog: Dialog
    lateinit var fromDialog: Dialog
    lateinit var conversionButton: Button
    lateinit var convertFromValue: String
    lateinit var convertToValue: String
    lateinit var conversionValue: String
    var arrayList: ArrayList<String> = ArrayList()
    val country = arrayOf<String>("VietNam", "US", "UK", "THAILAND", "JAPAN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        convertFromDropdownTextView = findViewById(R.id.convert_from_dropdown_menu);
        convertToDropdownTextView = findViewById(R.id.convert_to_dropdown_menu);
        conversionButton = findViewById(R.id.conversionButton);
        conversionRateText = findViewById(R.id.conversionRateText);
        amountToConvert = findViewById(R.id.amountToConvertValueEditText);

        val arrayList = ArrayList<String>()
        for (i in country){
            arrayList.add(i)
        }

        convertFromDropdownTextView.setOnClickListener {
            fromDialog = Dialog(this)
            fromDialog.setContentView(R.layout.from_spinner)
            fromDialog.window?.setLayout(650, 800)
            fromDialog.show()

            val editText: EditText = fromDialog.findViewById(R.id.edit_text)
            val listView: ListView = fromDialog.findViewById(R.id.list_view)

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView.adapter = adapter

            editText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                    adapter.getFilter().filter(s)
                }

                override fun afterTextChanged(s: Editable?) {
                    TODO("Not yet implemented")
                }
            })

            listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    convertFromDropdownTextView.setText(adapter.getItem(position));
                    fromDialog.dismiss();
                    convertFromValue = adapter.getItem(position).toString();
                }
            }

        }

        convertToDropdownTextView.setOnClickListener {
            toDialog = Dialog(this)
            toDialog.setContentView(R.layout.from_spinner)
            toDialog.window?.setLayout(650, 800)
            toDialog.show()

            val editText: EditText = toDialog.findViewById(R.id.edit_text)
            val listView: ListView = toDialog.findViewById(R.id.list_view)

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView.adapter = adapter

            editText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                    adapter.getFilter().filter(s)
                }

                override fun afterTextChanged(s: Editable?) {
                    TODO("Not yet implemented")
                }
            })

            listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    convertToDropdownTextView.setText(adapter.getItem(position));
                    toDialog.dismiss();
                    convertToValue = adapter.getItem(position).toString();
                }
            }
        }

        conversionButton.setOnClickListener {
                v ->
            val amountToConvert = amountToConvert.text.toString().toDoubleOrNull() ?: 0.0
            getConversionRate(convertFromValue, convertToValue, amountToConvert)


        }

    }

    fun getConversionRate(convertFrom: String, convertTo: String, amountToConvert: Double): String {

        var conversionValue: String = ""

        if (convertFrom == "Vietnam" && convertTo == "US") {
            // Perform the conversion
            val value = amountToConvert * 22
            conversionValue = "%.2f".format(value) // Convert the result to a String with 2 decimal places
        } else if (convertFrom == "US" && convertTo == "Vietnam") {
            val value = amountToConvert / 22
            conversionValue = "%.2f".format(value) // Convert the result to a String with 2 decimal places
        } else {
            // Handle additional cases or set a default message
            conversionValue = "Conversion not available"
        }

        return conversionValue // Return the converted value as a String

    }

}