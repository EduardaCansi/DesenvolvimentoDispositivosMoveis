package br.upf.ads.controlemanutencao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import br.upf.ads.appturma.dao.AppDatabase
import br.upf.ads.appturma.dao.ManutencaoDao
import br.upf.ads.appturma.domain.Manutencao

class AdicionarManutencao : AppCompatActivity() {
    var dao: ManutencaoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Adicionar manutenção");
        setContentView(R.layout.activity_adicionar_manutencao)

        dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "manutencao_app"
        ).allowMainThreadQueries().build().manutencaoDao()


    }

    fun handleInserir(v: View){
        val carro = findViewById<EditText>(R.id.editTextCarro).text.toString();
        val descricao = findViewById<EditText>(R.id.editTextDescricao).text.toString()

        var cod = dao?.getMaiorId()
        cod = cod!! + 1
        val p = Manutencao(cod, carro, descricao)
        dao?.insertOne(p)
    }

    fun handleVoltar (v: View) {
        val main = Intent(this, MainActivity::class.java)
        startActivity(main)
    }

    fun handleCriarManutencao(v: View) {
        handleInserir(v)
        handleVoltar(v)
    }




}