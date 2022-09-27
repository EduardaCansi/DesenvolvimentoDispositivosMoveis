package br.upf.ads.appexercicio.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseUtil {
    fun getDatabaseReference(colecao:String): DatabaseReference {
        return Firebase.database.getReference(colecao)
    }
}