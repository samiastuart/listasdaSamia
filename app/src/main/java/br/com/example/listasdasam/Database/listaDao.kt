package br.com.example.listasdasam.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.example.listasdasam.Modelo.Lista

@Dao
interface listaDao {

    @Query("SELECT * FROM itensLista")
    fun mostra_lista() : LiveData<List<Lista>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insere(lista: Lista)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun atualiza(lista: Lista)

    @Delete
    fun exclui(lista: Lista)

    @Query("Delete from itensLista")
    fun exclui_tudo()

}