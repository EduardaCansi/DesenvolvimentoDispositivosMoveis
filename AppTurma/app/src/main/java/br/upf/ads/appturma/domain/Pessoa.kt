package br.upf.ads.app02.domain

import java.io.Serializable

data class Pessoa(var id:Int, var nome:String, var idade:Int):Serializable{

    fun getDadosPessoa():String{
        var calc = idade*365;
        var res = nome+" você viveu aproximadamente $calc dias";
        return res;
    }
}
