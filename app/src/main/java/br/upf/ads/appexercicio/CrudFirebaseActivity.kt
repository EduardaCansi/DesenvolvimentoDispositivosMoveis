package br.upf.ads.appexercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import br.upf.ads.appexercicio.domain.Contato
import br.upf.ads.appexercicio.firebase.FirebaseUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class CrudFirebaseActivity : AppCompatActivity() {

    var dao: DatabaseReference? = null
    var editContatoId: EditText? = null
    var editContatoNome:EditText? = null
    var editContatoEmail:EditText? = null
    var editContatoFone:EditText? = null
    var listViewContatos: ListView? = null;

    var lista:ArrayList<Contato> = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_firebase)

        dao = FirebaseUtil.getDatabaseReference("contatos")


        editContatoId = findViewById<EditText>(R.id.editContatoId)
        editContatoNome = findViewById<EditText>(R.id.editContatoNome)
        editContatoEmail = findViewById<EditText>(R.id.editContatoEmail)
        editContatoFone = findViewById<EditText>(R.id.editContatoFone)
        listViewContatos = findViewById<ListView>(R.id.listViewContatos)

        findViewById<Button>(R.id.buttonContatoInserir).setOnClickListener { inserir() }
        findViewById<Button>(R.id.buttonContatoAlterar).setOnClickListener { alterar() }
        findViewById<Button>(R.id.buttonContatoExcluir).setOnClickListener { excluir() }
        listar()
    }

    fun inserir(){
        var _id:String = dao!!.push()!!.key.toString();
        val obj = Contato(_id,"Fulano", "fulano@teste.com",
            "54 6666-9999")
        dao!!.child(_id)!!.setValue(obj)
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
                listViewContatos?.adapter = ArrayAdapter(this@CrudFirebaseActivity,
                    android.R.layout.simple_list_item_1, lista!!)

                listViewContatos?.setOnItemClickListener { parent, view, position, id ->
                    val selecionado:Contato = lista.get(position)
                    editContatoId?.setText(selecionado._id)
                    editContatoNome?.setText(selecionado.nome)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to read value. "+
                        error.toException(), Toast.LENGTH_LONG).show()
            }
        })
    }

    fun alterar(){
        var _id:String = editContatoId?.text.toString();
        val obj = Contato(_id, "Fulano",
            "fulano@teste.com", "54 6666-9999")
        dao!!.child(_id)!!.setValue(obj)
    }

    fun excluir(){
        var _id:String = editContatoId?.text.toString();
        dao!!.child(_id)!!.removeValue()
    }

}