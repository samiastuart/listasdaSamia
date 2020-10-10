package br.com.example.listasdasam

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.Adapter.ListAdapter
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.ViewModel.ListaViewModel


class MainActivity() : AppCompatActivity() {

    lateinit var rvList: RecyclerView
    lateinit var btn_ins: ImageButton
    lateinit var item: EditText
    lateinit var lista: Lista
    lateinit var checkb: CheckBox
    lateinit var valor: TextView
//    lateinit var atual: TabItem
//    lateinit var exec : TabItem
//    lateinit var compl:TabItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rv_lista)
        btn_ins = findViewById(R.id.btn_inserir)
        item = findViewById(R.id.et_inserir)


//        val sectionsPagerAdapter = AbasAdapter(this, supportFragmentManager)
//        val viewPager: ViewPager = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)

//        val fragment = AbasFragment("ATUAL")
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.rv_lista, fragment)
//            .commit()


        val listaViewModel by viewModels<ListaViewModel>()

        val listaAdpt = ListAdapter(this)

//        listaAdpt.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//            override fun onItemRangeInserted(
//                positionStart: Int,
//                itemCount: Int
//            ) {
//                rvList.smoothScrollToPosition(itemCount)
//            }
//
//            override fun onItemRangeRemoved(
//                positionStart: Int,
//                itemCount: Int
//            ) {
//                rvList.smoothScrollToPosition(itemCount)
//            }
//        })



        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvList.adapter = listaAdpt

        listaViewModel.mostraLista.observe(this, Observer {
            listaAdpt.list = it
            listaAdpt.notifyDataSetChanged()
//            rvList.scrollToPosition(listaAdpt.list.size-1)
            rvList.smoothScrollToPosition(listaAdpt.list.size - 1)
        })

//        rvList.post(Runnable { rvList.smoothScrollToPosition(listaAdpt.list.size - 1) })

//        rvList.smoothScrollToPosition(listaAdpt.list.size)

        btn_ins.setOnClickListener {
            Toast.makeText(this, "CLICADO", Toast.LENGTH_LONG).show()
            if (item.text.toString() != "") {
                val lista = Lista(checkb = 0, itemlist = item.text.toString(), valor = 7.33F)
                try {
//                    listaViewModel.jogafora(lista)
                    listaViewModel.insereLista(lista)
                } catch (e: Error) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
                item.setText("")
            }
        }

    }
}

