package br.upf.ads.appturma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TerceiraActivity : AppCompatActivity() {

    var editDados:EditText? = null;
    var buttonVoltar:Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terceira)
        editDados = findViewById<EditText>(R.id.editDados)
        buttonVoltar = findViewById<Button>(R.id.buttonVoltar)
        buttonVoltar?.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        editDados?.append(intent.extras?.getString("descricao"))
        editDados?.append(intent.extras?.getBoolean("editar").toString())
        editDados?.append(intent.extras?.getInt("id").toString())
        editDados?.append(intent.extras?.get("objeto").toString())
    }
}