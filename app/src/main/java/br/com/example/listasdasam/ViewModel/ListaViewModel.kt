package br.com.example.listasdasam.ViewModel

import android.app.Application
import androidx.annotation.IntDef
import androidx.lifecycle.*
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Repositorio.ListaRepositorio

class ListaViewModel (application: Application) : AndroidViewModel(application) {


//
//        private val _index = MutableLiveData<Int>()
//        val text: LiveData<String> = Transformations.map(_index) {
//            "Hello world from section: $it"
//        }
//
//        fun setIndex(index: Int) {
//            _index.value = index
//        }

//    private val indice = MutableLiveData<Int>()
//    val aba: LiveData<String> = Transformations.map(indice) {
//            when (it) {
//                1->  "aba 1"
//                2-> "aba2"
//                3-> "aba3"
//                else -> "aba"
//            }
//        }

    private val listRepo = ListaRepositorio(getApplication())

    val mostraLista = listRepo.mostraAtual

    fun insereLista(lista:Lista) = listRepo.insere(lista)
    fun jogafora(lista: Lista) = listRepo.jogatudofora(lista)
}