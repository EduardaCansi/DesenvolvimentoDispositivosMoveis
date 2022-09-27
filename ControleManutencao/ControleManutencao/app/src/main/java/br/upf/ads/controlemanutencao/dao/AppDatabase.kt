package br.upf.ads.appturma.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.upf.ads.appturma.domain.Manutencao

@Database(entities = [Manutencao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun manutencaoDao(): ManutencaoDao
}