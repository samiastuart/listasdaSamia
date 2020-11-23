package br.com.example.listasdasam.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista

@Dao
    interface tipoDao {

        @Transaction
//        @Query("SELECT * FROM tipoLista")
        @Query("SELECT 0 as idL, '***Clique aqui para selecionar a lista desejada***' as nomeLista UNION SELECT * FROM tipoLista order by nomeLista COLLATE UNICODE")
        fun mostra_tipo() : LiveData<List<TipoLista>>

//        @Transaction
//        @Query("SELECT SCOPE_IDENTITY()")
//        fun pegaId() : String

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insereT(tipoLista: TipoLista)

        @Update(onConflict = OnConflictStrategy.REPLACE)
        fun atualizaT(tipoLista: TipoLista)

        @Delete
        fun excluiT(tipoLista: TipoLista)

        @Query("Delete from tipoLista")
        fun exclui_tudoT()
    }