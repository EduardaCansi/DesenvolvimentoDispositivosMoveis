package br.upf.ads.controlemanutencao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import br.upf.ads.appturma.dao.AppDatabase
import br.upf.ads.appturma.dao.ManutencaoDao
import br.upf.ads.appturma.domain.Manutencao

class EditarManutencao : AppCompatActivity() {
    var dao: ManutencaoDao? = null
    var id: Number? = null
    var carro: EditText? = null
    var descricao: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Editar manutenção");
        setContentView(R.layout.activity_editar_manutencao)

        dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "manutencao_app"
        ).allowMainThreadQueries().build().manutencaoDao()

        carro = findViewById<EditText>(R.id.editTextCarro);
        descricao = findViewById<EditText>(R.id.editTextDescricao);
    }

    override fun onStart() {
        super.onStart()

        val o: Manutencao = intent.extras?.get("manutencaoObj") as Manutencao



        carro?.append(o.carro)
        descricao?.append(o.descricao)
    }

    fun handleAlterar(v: View){
        val o: Manutencao = intent.extras?.get("manutencaoObj") as Manutencao

        val p = Manutencao(o.id, carro?.text.toString(), descricao?.text.toString())
        dao?.updateOne(p)
    }
    fun handleExcluir(v: View){
        var cod = dao?.getMaiorId()
        val p = Manutencao(cod, null, null)
        dao?.delete(p)
    }

    fun handleVoltar (v: View) {
        val main = Intent(this, MainActivity::class.java)
        startActivity(main)
    }

    fun handleEditarManutencao(v: View) {
        handleAlterar(v)
        handleVoltar(v)
    }

    fun handleExcluirManutencao(v: View) {
        handleExcluir(v)
        handleVoltar(v)
    }

}