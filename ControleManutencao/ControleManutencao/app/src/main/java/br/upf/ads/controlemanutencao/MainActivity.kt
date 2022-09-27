package br.upf.ads.controlemanutencao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.room.Room
import br.upf.ads.appturma.dao.AppDatabase
import br.upf.ads.appturma.dao.ManutencaoDao

class MainActivity : AppCompatActivity() {
    var dao:ManutencaoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Manutenção de Veículos");
        setContentView(R.layout.activity_main)

        dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "manutencao_app"
        ).allowMainThreadQueries().build().manutencaoDao()

        handleListar()
    }

    fun handleAdicionarManutencao (v: View) {
        val adicionarManutencao = Intent(this, AdicionarManutencao::class.java)
        startActivity(adicionarManutencao)
    }

    fun handleListar(){
        val lista = dao?.getAll()
        val listViewManutencao = findViewById<ListView>(R.id.listViewManutencao)
        listViewManutencao.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, lista!!)


        listViewManutencao.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            var selecionado = lista[i]
            //Toast.makeText(this, selecionado.carro, Toast.LENGTH_LONG).show();
            val editarManutencaoDao = Intent(this, EditarManutencao::class.java)
            editarManutencaoDao.putExtra("manutencaoObj", selecionado)

            startActivity(editarManutencaoDao)
        }
    }
}