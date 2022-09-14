package br.upf.ads.appturma.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.upf.ads.appturma.domain.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoDao():ProdutoDao
}