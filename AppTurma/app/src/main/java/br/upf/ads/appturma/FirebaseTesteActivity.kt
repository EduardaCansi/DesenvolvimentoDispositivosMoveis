package br.upf.ads.appturma

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.upf.ads.appturma.domain.Contato
import br.upf.ads.appturma.firebase.FirebaseUtil
import com.google.firebase.database.*


class FirebaseTesteActivity : AppCompatActivity() {
    var database:FirebaseDatabase? = null
    var myRef:DatabaseReference? = null

    var dao: DatabaseReference? = null
    var lista:ArrayList<Contato> = ArrayList();
    var listViewContatos:ListView? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_teste)
        findViewById<Button>(R.id.buttonOk).setOnClickListener { enviar() }

        dao = FirebaseUtil.getDatabaseReference("contatos")
        listViewContatos = findViewById<ListView>(R.id.listViewContatos)

        database = FirebaseDatabase.getInstance()
        myRef = database?.getReference("message")

        val editNaBase = findViewById<EditText>(R.id.editNaBase)
        myRef!!.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue()
                editNaBase?.setText(value.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                editNaBase?.setText("Failed to read value. "+ error.toException())
            }
        })

        findViewById<Button>(R.id.buttonContatoInserir).setOnClickListener { inserir() }
        findViewById<Button>(R.id.buttonContatoAlterar).setOnClickListener { alterar() }
        findViewById<Button>(R.id.buttonContatoExcluir).setOnClickListener { excluir() }
        listar()

    }

    fun enviar(){
        val editMensagem = findViewById<EditText>(R.id.editMensagem)
        myRef!!.setValue(editMensagem?.text.toString())
    }

    fun inserir(){
        var _id:String = dao!!.push()!!.key.toString();
        val obj = Contato(_id,"Fulano", "fulano@teste.com",
            "54 6666-9999")
        dao!!.child(_id)!!.setValue(obj)
    }

    fun alterar(){
        var _id:String = ""
        val obj = Contato(_id, "Fulano alterado",
            "fulano@teste.com", "54 6666-9999")
        dao!!.child(_id)!!.setValue(obj)
    }

    fun excluir(){
        var _id:String = ""
        dao!!.child(_id)!!.removeValue()
    }

    fun listar(){
        dao!!.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lista = ArrayList();
                val children = snapshot!!.children
                children.forEach {
                    val obj:Contato?? = it.getValue(Contato::class.java)
                    lista.add(obj!!)
                }
                listViewContatos?.adapter = ArrayAdapter(this@FirebaseTesteActivity,
                    android.R.layout.simple_list_item_1, lista!!)
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to read value. "+
                        error.toException(), Toast.LENGTH_LONG).show()
            }
        })
    }


}