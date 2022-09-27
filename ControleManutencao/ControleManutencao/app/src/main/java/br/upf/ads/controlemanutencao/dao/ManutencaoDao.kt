package br.upf.ads.appturma.dao

import androidx.room.*
import br.upf.ads.appturma.domain.Manutencao

@Dao
interface ManutencaoDao {
    @Query("SELECT * FROM manutencao")
    fun getAll(): List<Manutencao>

    @Query("SELECT max(id) FROM manutencao")
    fun getMaiorId(): Int

    @Query("SELECT * FROM manutencao WHERE carro = :nomeCarro")
    fun getManutencaoWithSearch(nomeCarro: String): List<Manutencao>

    @Query("SELECT * FROM manutencao WHERE id = :idManutencao")
    fun getManutencao(idManutencao: Int): Manutencao

    @Insert
    fun insertOne(manutencao: Manutencao)

    @Update
    fun updateOne(manutencao: Manutencao)

    @Delete
    fun delete(manutencao: Manutencao)
}