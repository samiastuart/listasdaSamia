package br.com.example.listasdasam

import android.R.attr.*
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.Adapter.ListAdapter
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.ViewModel.ListaViewModel
import java.util.*


lateinit var rvList: RecyclerView


//lateinit var btn_ins: ImageButton
//lateinit var item: EditText

//lateinit var viewHolder: RecyclerView.ViewHolder
lateinit var ord: ImageButton
//        ** Para guardar a posição do ItemHelper
var inicio: Int = -37
var ident: String = "0"
var itensmovidos :String = ""


class FragmentLista() : Fragment() {
//    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //****Recebendo dados da AbasAdapter
        var abaSelec = this.arguments?.getString("aba")
//        var itemInserido = this.arguments?.getString("novoItem")

        //****Recebendo dados da MainActivity
//        val main = arguments
        val idlista = this.arguments?.getString("idL")

//        var posicao: Int = 0
        val view: View = inflater.inflate(R.layout.fragment_lista, container, false)

        ord = view.findViewById(R.id.ordem)



        val listaViewModel by viewModels<ListaViewModel>()

        val listaAdpt = ListAdapter(requireActivity())

        rvList = view.findViewById(R.id.rv_lista)

        rvList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

       rvList.adapter = listaAdpt

        ord.setOnClickListener {
//            ordenado = 1
            when (abaSelec) {

                "ATUAL" -> {
                    listaViewModel.mostraListaAO(idlista.toString()).observe(
                        requireActivity(),
                        Observer {
                            listaAdpt.list = it
                            listaAdpt.notifyDataSetChanged()
//                        rvList.smoothScrollToPosition(it.size)
                        })
                }
                "REALIZADO" -> {
                    listaViewModel.mostraListaEO(idlista.toString()).observe(
                        requireActivity(),
                        Observer {
                            listaAdpt.list = it
                            listaAdpt.notifyDataSetChanged()
//                    rvList.smoothScrollToPosition(it.size)
                        })
                }
                "TUDO" -> {
                    listaViewModel.mostraListaCO(idlista.toString()).observe(
                        requireActivity(),
                        Observer {
                            listaAdpt.list = it
                            listaAdpt.notifyDataSetChanged()
//                    rvList.smoothScrollToPosition(it.size)
                        })
                }
            }
//            return view
        }

        val helper = androidx.recyclerview.widget.ItemTouchHelper(
            br.com.example.listasdasam.ItemTouchHelper(
                ItemTouchHelper.UP
                        or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                listaAdpt,
                listaViewModel
//                abaSelec, false
            )
        )
            helper.attachToRecyclerView(rvList)


        when (abaSelec) {

            "ATUAL" -> {
                listaViewModel.mostraListaA(idlista.toString()).observe(
                    requireActivity(),
                    Observer {
                        listaAdpt.list = it
                        listaAdpt.notifyDataSetChanged()
//                    rvList.smoothScrollToPosition(it.size)
                    })
            }
            "REALIZADO" -> {
                listaViewModel.mostraListaE(idlista.toString()).observe(
                    requireActivity(),
                    Observer {
                        listaAdpt.list = it
                        listaAdpt.notifyDataSetChanged()
//                    rvList.smoothScrollToPosition(it.size)
                    })
            }
            "TUDO" -> {
                listaViewModel.mostraListaC(idlista.toString()).observe(
                    requireActivity(),
                    Observer {
                        listaAdpt.list = it
                        listaAdpt.notifyDataSetChanged()
//                    rvList.smoothScrollToPosition(it.size)
                    })
            }
                }
        return view
    }


}

 class ItemTouchHelper(
     drag: Int,
     swipe: Int,
     var adpt: ListAdapter,
     var listaViewModel: ListaViewModel
//     private val abaSel: String?
// var mudou: Boolean
 ) :
     androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
         drag, swipe
     ) {


     override fun onMove(
         recyclerView: RecyclerView,
         viewHolder: RecyclerView.ViewHolder,
         target: RecyclerView.ViewHolder
     ): Boolean {

         val from: Int = viewHolder.adapterPosition
         var to: Int = target.adapterPosition
         viewHolder.itemView.setBackgroundColor(
             ContextCompat.getColor(viewHolder.itemView.context, R.color.CorFab)
         )


//         val layoutManager = rvList.layoutManager
//         if (layoutManager is ViewDropHandler) {
//             (layoutManager as ViewDropHandler).prepareForDrop(
//                 viewHolder.itemView,
//                 target.itemView, x, y
//             )
////             return true
//         }

         if (inicio == -37) {
             inicio = from
         }


         Collections.swap(adpt.list, from, to)

         itensmovidos += "inicio = $inicio from = $from  to = $to "

         adpt.notifyItemMoved(from, to)

         return true
     }

     override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
         var lista: Lista
         lista = adpt.list.get(viewHolder.adapterPosition)
         listaViewModel.exclui(lista)
         adpt.notifyItemRemoved(viewHolder.adapterPosition)
         inicio = -37
     }


     override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
         super.clearView(recyclerView, viewHolder)

         if (inicio != -37) {

             var lista: Lista

             val to: Int = viewHolder.adapterPosition
             viewHolder.itemView.setBackgroundColor(
                 ContextCompat.getColor(viewHolder.itemView.context, R.color.CorFab)
             )

             var i: Int

//             itensmovidos = "TO $to$itensmovidos"
//             var itensDalista: String = ""

             if (inicio > to) {
                 i = to
             } else {
                 i = inicio
                 inicio = to
             }
             while (i <= inicio) {
//                 itensDalista += "I - $i = ${adpt.list[i].itemlist} "
                 lista = Lista(
                     adpt.list[i].id,
                     adpt.list[i].checkb,
                     adpt.list[i].itemlist,
                     adpt.list[i].valor,
                     i,
                     adpt.list[i].idL
                 )
                 listaViewModel.atualiza(lista)
                 i += 1
             }
             inicio = -37
//             itensmovidos = ""
         }
     }


 }

