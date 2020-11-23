package br.com.example.listasdasam.ViewModel

import android.app.Application
import androidx.annotation.IntDef
import androidx.lifecycle.*
import br.com.example.listasdasam.Database.listaDao
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

    fun mostraListaC(idL:String) = listRepo.mostraAtualC(idL)

    fun mostraListaA(idL:String) = listRepo.mostraAtualA(idL)

    fun mostraListaE(idL:String) = listRepo.mostraAtualE(idL)

    fun mostraListaCO(idL:String) = listRepo.mostraAtualCO(idL)

    fun mostraListaAO(idL:String) = listRepo.mostraAtualAO(idL)

    fun mostraListaEO(idL:String) = listRepo.mostraAtualEO(idL)

    fun atualiza(lista: Lista) = listRepo.atualiza(lista)

    fun maxPosicao(idL: String) = listRepo.maxPos(idL)

    fun insereLista(lista:Lista) = listRepo.insere(lista)

    fun exclui(lista:Lista) = listRepo.exclui(lista)

    fun jogafora(idL:String) = listRepo.jogatudofora(idL)
}