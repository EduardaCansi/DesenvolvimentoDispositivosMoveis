package br.upf.ads.appturma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.upf.ads.app02.domain.Pessoa

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)
    }

    fun irParaTerceiraActivity(v:View){
        val tela = Intent(this, TerceiraActivity::class.java)
        //passando dados para a terceira tela
        tela.putExtra("descricao", "Teste")
        tela.putExtra("editar", true)
        tela.putExtra("id", 1)
        val p = Pessoa(1, "Fulano", 20)
        tela.putExtra("objeto", p)
        startActivity(tela)
    }
}