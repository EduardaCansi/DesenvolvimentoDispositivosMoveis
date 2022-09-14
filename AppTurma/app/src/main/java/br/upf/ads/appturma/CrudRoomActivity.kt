package br.upf.ads.appturma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import br.upf.ads.appturma.dao.AppDatabase
import br.upf.ads.appturma.dao.ProdutoDao
import br.upf.ads.appturma.domain.Produto

class CrudRoomActivity : AppCompatActivity() {

    var dao: ProdutoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_room2)

        dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "baseturma"
        ).allowMainThreadQueries().build().produtoDao()

        listar()

        findViewById<Button>(R.id.buttonInserir).setOnClickListener { inserir() }
        findViewById<Button>(R.id.buttonAlterar).setOnClickListener { alterar() }
        findViewById<Button>(R.id.buttonExcluir).setOnClickListener { excluir() }

    }

    fun listar(){
        val lista = dao?.getAll()
        val listViewProduto = findViewById<ListView>(R.id.listViewProdutos)
        listViewProduto.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, lista!!)

        listViewProduto.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var selecionado = lista[position]
            Toast.makeText(this, selecionado.nome, Toast.LENGTH_LONG).show()
        }


    }

    fun inserir(){
        var cod = dao?.getMaiorId()
        cod = cod!! + 1
        val p = Produto(cod, "Produto $cod", 123f+cod)
        dao?.inserir(p)
        listar()
    }

    fun alterar(){
        var cod = dao?.getMaiorId()
        val p = Produto(cod, "Último produto alterado", 456f)
        dao?.alterar(p)
        listar()
    }
    fun excluir(){
        var cod = dao?.getMaiorId()
        val p = Produto(cod, null, null)
        dao?.excluir(p)
        listar()
    }
}