package br.upf.ads.appturma.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Produto(
    @PrimaryKey
    var id:Int?,
    @ColumnInfo(name="nome")
    @NonNull
    var nome:String?,
    @ColumnInfo(name="preco")
    var preco:Float?
)


