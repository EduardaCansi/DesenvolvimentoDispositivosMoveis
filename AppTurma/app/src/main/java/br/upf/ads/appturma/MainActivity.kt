package br.upf.ads.appturma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickSegundaActivity(v:View){
        val tela = Intent(this, SegundaActivity::class.java)
        startActivity(tela)
    }
}