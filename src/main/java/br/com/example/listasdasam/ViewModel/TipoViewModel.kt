package br.com.example.listasdasam.ViewModel

import android.app.Application
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferencesName
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista
import br.com.example.listasdasam.Repositorio.ListaRepositorio
import br.com.example.listasdasam.Repositorio.TipoRepositorio
import br.com.example.listasdasam.tipoLista

@Suppress("DEPRECATION")
class TipoViewModel(application: Application): AndroidViewModel(application) {

        private val tipoRepo = TipoRepositorio(getApplication())

        val mostraTipo = tipoRepo.mostraTipo

//        val pegaID = tipoRepo.pegaId

        fun atualizaT(tipoLista: TipoLista) = tipoRepo.atualizaT(tipoLista)

        fun insereTipo(tipoLista: TipoLista) = tipoRepo.insereT(tipoLista)
//            .also{tipoRepo ->
////                var id: String
//                var tl: TipoLista
//                getDefaultSharedPreferences(getApplication()).let{
//                        var id=it.all.keys
//                }
//
//        }



        fun excluiT(tipoLista: TipoLista) = tipoRepo.excluiT(tipoLista)

        fun jogaforaT() = tipoRepo.jogatudoforaT()

    }