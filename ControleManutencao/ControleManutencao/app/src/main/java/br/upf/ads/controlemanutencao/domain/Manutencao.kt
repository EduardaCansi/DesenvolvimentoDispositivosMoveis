package br.upf.ads.appturma.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Manutencao(
    @PrimaryKey
    var id: Int?,

    @ColumnInfo(name = "carro")
    @NonNull
    var carro: String?,

    @ColumnInfo(name = "descricao")
    @NonNull
    var descricao: String?
): Serializable
{
    @Override
    override fun toString(): String {
        return "${carro} - ${descricao}"
    }
}