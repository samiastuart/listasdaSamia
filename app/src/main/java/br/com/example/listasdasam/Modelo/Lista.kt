package br.com.example.listasdasam.Modelo

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "itensLista")
data class Lista(

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var checkb: Int,
    var itemlist: String,
    var valor: Float

)
