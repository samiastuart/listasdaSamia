package br.com.example.listasdasam.Repositorio


import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import br.com.example.example.database.DadosSqlite
import br.com.example.listasdasam.Database.listaDao
import br.com.example.listasdasam.Modelo.Lista

class ListaRepositorio (application: Application) {

    private val listaDao = DadosSqlite.getDatabase(application).listaDao()

    val mostraAtual = listaDao.mostra_lista()

    fun insere(lista: Lista) =  listaDao.insere(lista)

    fun atualiza(lista: Lista) = listaDao.atualiza(lista)

    fun jogatudofora(lista: Lista) = listaDao.exclui_tudo()
}