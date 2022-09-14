package br.upf.ads.appturma.dao

import androidx.room.*
import br.upf.ads.appturma.domain.Produto

@Dao
interface ProdutoDao {
    @Query("select * from produto")
    fun getAll():List<Produto>

    @Query("select max(id) from produto")
    fun getMaiorId():Int

    @Query("select * from produto where id = :idProd")
    fun getProdutoPorId(idProd:Int):Produto

    @Query("select * from produto where nome like :filtro")
    fun getProdutoPorNome(filtro:String):List<Produto>

    @Insert
    fun inserir(prod: Produto)

    @Update
    fun alterar(prod: Produto)

    @Delete
    fun excluir(prod: Produto)
}