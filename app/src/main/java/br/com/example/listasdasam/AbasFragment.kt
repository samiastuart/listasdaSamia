package br.com.example.listasdasam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.example.listasdasam.Adapter.AbasAdapter
import br.com.example.listasdasam.Adapter.ListAdapter
import br.com.example.listasdasam.ViewModel.ListaViewModel
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"


class AbasFragment(aba: String) : Fragment() {
    private lateinit var pageViewModel: ListaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val listaViewModel by viewModels<ListaViewModel>()
//
//        val listaAdpt = ListAdapter(this)
//
//        listaViewModel.mostraLista.observe(this, Observer{
//            listaAdpt.list = it
//            listaAdpt.notifyDataSetChanged()
////            rvList!!.smoothScrollToPosition(it.size-1)
//        })


//        val sectionsPagerAdapter = AbasAdapter(context, supportFragmentManager)
//        val viewPager: ViewPager = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.itens_lista,container,false)
        val tela = inflater.inflate(R.layout.itens_lista, container, false)
//        val textView: TextView = root.findViewById(R.id.listaItens)
//        ListaViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })

//        val listaViewModel by viewModels<ListaViewModel>()
//
//        val listaAdpt = ListAdapter(this)

//        listaViewModel.mostraLista.observe(viewLifecycleOwner, Observer{
//            listaAdpt.list = it
//            listaAdpt.notifyDataSetChanged()
////            rvList!!.smoothScrollToPosition(it.size-1)
//        })
        return tela
    }
}

//    companion object {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private const val ARG_SECTION_NUMBER = "section_number"
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        @JvmStatic
//        fun newInstance(sectionNumber: Int): AbasFragment {
//            return AbasFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_SECTION_NUMBER, sectionNumber)
//                }
//            }
//        }
//    }
//}