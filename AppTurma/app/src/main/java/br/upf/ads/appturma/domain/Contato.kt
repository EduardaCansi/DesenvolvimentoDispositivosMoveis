package br.upf.ads.appturma.domain

import java.io.Serializable

data class Contato(
    var _id:String? = null,
                   var nome:String? = null,
                   var email:String? = null,
                   var fone:String? = null
):Serializable {
    override fun toString(): String {
        return nome+" | "+fone
    }
}
