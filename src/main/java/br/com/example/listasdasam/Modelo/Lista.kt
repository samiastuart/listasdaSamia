package br.com.example.listasdasam.Modelo

import androidx.room.*
import androidx.room.util.TableInfo
import java.io.Serializable
import java.util.*


@Entity(tableName = "itensLista",
    foreignKeys = [
        ForeignKey(
            entity = TipoLista::class,
            parentColumns = ["idL"],
            childColumns = ["idL"]
        )])
data class Lista(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var checkb: Int,
    var itemlist: String,
    var valor: Float,
    var posicao: Int,
    var idL:String
//    @Embedded val tipoLista: TipoLista
)

@Entity(tableName = "tipoLista")
data class TipoLista(
    @PrimaryKey val idL: String = UUID.randomUUID().toString(),
    var nomeLista: String
)

//data class ListaComItens(
//    @Embedded val tipoLista: TipoLista,
//    @Relation(
//        parentColumn = "idL",
//        entityColumn = "id"
//    )
//    val listas :List<Lista>
//)


