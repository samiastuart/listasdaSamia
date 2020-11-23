package br.com.example.listasdasam.Repositorio

import android.app.Application
import br.com.example.example.database.DadosSqlite
import br.com.example.listasdasam.Database.tipoDao
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista


class TipoRepositorio (application: Application) {

    private val tipoDao = DadosSqlite.getDatabase(application).tipoDao()

    val mostraTipo = tipoDao.mostra_tipo()

    fun insereT(tipoLista: TipoLista) =  tipoDao.insereT(tipoLista)

    fun atualizaT(tipoLista: TipoLista) = tipoDao.atualizaT(tipoLista)

    fun excluiT(tipoLista: TipoLista) = tipoDao.excluiT(tipoLista)

    fun jogatudoforaT() = tipoDao.exclui_tudoT()
}
