package br.com.example.listasdasam.Repositorio


import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import br.com.example.example.database.DadosSqlite
import br.com.example.listasdasam.Database.listaDao
import br.com.example.listasdasam.Modelo.Lista

class ListaRepositorio (application: Application) {

    private val listaDao = DadosSqlite.getDatabase(application).listaDao()

    fun mostraAtualC(idl:String) = listaDao.mostra_listaC(idl)

    fun mostraAtualA(idl:String) = listaDao.mostra_listaA(idl)

    fun mostraAtualE(idl:String) = listaDao.mostra_listaE(idl)

    fun mostraAtualCO(idl:String) = listaDao.mostra_listaCO(idl)

    fun mostraAtualAO(idl:String) = listaDao.mostra_listaAO(idl)

    fun mostraAtualEO(idl:String) = listaDao.mostra_listaEO(idl)

    fun maxPos(idL:String) = listaDao.maxPosition(idL)

    fun insere(lista: Lista) =  listaDao.insere(lista)

    fun atualiza(lista: Lista) = listaDao.atualiza(lista)

    fun exclui(lista: Lista) = listaDao.exclui(lista)

    fun jogatudofora(idl: String) = listaDao.exclui_tudo(idl)
}