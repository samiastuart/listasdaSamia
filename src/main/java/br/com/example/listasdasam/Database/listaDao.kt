package br.com.example.listasdasam.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.example.example.database.DadosSqlite
import br.com.example.listasdasam.MainActivity
import br.com.example.listasdasam.Modelo.Lista
import com.google.android.material.theme.MaterialComponentsViewInflater



@Dao
interface listaDao {

    @Transaction
    @Query("SELECT * FROM itensLista where checkb = 0 and idL = :idL order by posicao" )
    fun mostra_listaA(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM itensLista where checkb = 1 and idL = :idL order by posicao")
    fun mostra_listaE(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM itensLista WHERE idL = :idL order by posicao")
    fun mostra_listaC(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM itensLista where checkb = 0 and idL = :idL order by itemlist COLLATE UNICODE")
    fun mostra_listaAO(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM itensLista where checkb = 1 and idL = :idL order by itemlist COLLATE UNICODE")
    fun mostra_listaEO(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT * FROM itensLista  WHERE idL = :idL order by itemlist COLLATE UNICODE")
    fun mostra_listaCO(idL:String) : LiveData<List<Lista>>

    @Transaction
    @Query("SELECT MAX(posicao) FROM itensLista WHERE idL = :idL")
    fun maxPosition(idL:String):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insere(lista: Lista)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun atualiza(lista: Lista)

    @Delete
    fun exclui(lista: Lista)

    @Query("Delete from itensLista where idL = :idL")
    fun exclui_tudo(idL:String)



}