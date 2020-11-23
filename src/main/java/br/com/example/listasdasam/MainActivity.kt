package br.com.example.listasdasam

import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.example.listasdasam.Adapter.AbasAdapter
import br.com.example.listasdasam.Adapter.ListAdapter
import br.com.example.listasdasam.Adapter.idl
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista
import br.com.example.listasdasam.ViewModel.ListaViewModel
import br.com.example.listasdasam.ViewModel.TipoViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lista.*


class MainActivity() : AppCompatActivity() {

//
    lateinit var btn_ins: ImageButton
    lateinit var item: EditText
    lateinit var lista: Lista
    lateinit var titulolista: TextView
    lateinit var lixo: ImageButton

//    lateinit var checkb: CheckBox
//    lateinit var valor: TextView
//    lateinit var atual: TabItem
//    lateinit var exec : TabItem
//    lateinit var compl:TabItem

//    lateinit var nome:String
//    lateinit var idlista: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var nome : String? = intent.getStringExtra("nomeLista")
        var idlista: String? = intent.getStringExtra("idLista")


//
        btn_ins = findViewById(R.id.btn_inserir)
        item = findViewById(R.id.et_inserir)
        titulolista = findViewById(R.id.tituloLista)
        var abaAt: Int = 0
        titulolista.text = nome
        lixo = findViewById(R.id.delLista)



//
//
//        tabs.setSelectedTabIndicatorColor(Color.WHITE)
//        tabs.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
//        tabs.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)
//
//        // Set different Text Color for Tabs for when are selected or not
//        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)
//
//        // Number Of Tabs
//        val numberOfTabs = 3
//
//        // Set Tabs in the center
//        //tab_layout.tabGravity = TabLayout.GRAVITY_CENTER
//
//        // Show all Tabs in screen
//        tabs.tabMode = TabLayout.MODE_FIXED
//
//        // Scroll to see all Tabs
//        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
//
//        // Set Tab icons next to the text, instead above the text
//        tabs.isInlineLabel = true
//
//



//        val sectionsPagerAdapter = AbasAdapter(this, supportFragmentManager,idlista.toString())
        val sectionsPagerAdapter = AbasAdapter(supportFragmentManager, lifecycle, 3, idlista.toString())
//        val viewPager:ViewPager = findViewById(R.id.view_pager)
        view_pager.adapter = sectionsPagerAdapter
//        viewPager.isUserInputEnabled = true

        TabLayoutMediator(tabs, view_pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "ATUAL"
//                    tab.setIcon(R.drawable.ic_music)
                }
                1 -> {
                    tab.text = "REALIZADO"
//                    tab.setIcon(R.drawable.ic_movie)

                }
                2 -> {
                    tab.text = "TUDO"
//                    tab.setIcon(R.drawable.ic_book)
                }

            }
            // Change color of the icons
//            tab.icon?.colorFilter =
//                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
//                    Color.WHITE,
//                    BlendModeCompat.SRC_ATOP
//                )
        }.attach()




//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)

        val listaViewModel by viewModels<ListaViewModel>()
        val tipoViewModel : TipoViewModel by viewModels<TipoViewModel>()

        val listaAdpt = ListAdapter(this)

        lixo.setOnClickListener {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("A lista $nome e seus itens serão eliminados. Confirma?")

                 dialog.setPositiveButton("Sim"){dialog, which ->
                   DeletaLista(listaViewModel,idlista, nome)
                }
                dialog.setNegativeButton("Não"){dialog,which ->

                }
                dialog.create()
                dialog.show()
            }

        titulolista.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            var editT: EditText = EditText(this)
            editT.setText(titulolista.text)
            editT.selectAll()
            dialog.setView(editT)
            dialog.setPositiveButton("ALTERAR"){ _, _ ->
                var texto: String = editT.getText().toString().trim()
                titulolista.text = texto
                tipoLista = TipoLista(idlista.toString(), texto)
                tipoViewModel.atualizaT(tipoLista = tipoLista)
            }
            dialog.setNegativeButton("Cancelar"){dialog,which ->
            }
            dialog.create()
            dialog.show()
        }




        btn_ins.setOnClickListener {

//            tabs.getTabAt(2)?.select()
            var quant: Int = rvList.size
            var pos: Int = listaViewModel.maxPosicao(idlista.toString())
//            rvList.smoothScrollToPosition(pos+1)
            if ((quant == 0) && (pos == 0)) pos = -1

            if (item.text.toString() != "") {
                val lista = Lista(
                    checkb = 0,
                    itemlist = item.text.toString(),
                    valor = 0.00F,
                    posicao = pos+1,
                    idL = idlista.toString()
                )
                try {
//                    listaViewModel.jogafora(lista)
                    listaViewModel.insereLista(lista)
                    rvList.smoothScrollToPosition(pos+1)
//                    args.putString("novoItem", "OK")
//                    fragment.arguments = args
                } catch (e: Error) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
//                rvList.smoothScrollToPosition(pos)
                item.setText("")
            }

        }

    }

    private fun DeletaLista(lvm: ListaViewModel, idlista: String?, nome: String?) {

        var id = idlista.toString()
        var nom = nome.toString()
        var mens: String = "Problema ao eliminar!"

        try {
            DeletaItens(lvm, id)
            val tipoViewModel by viewModels<TipoViewModel>()
            tipoLista = TipoLista(id, nom)
            tipoViewModel.excluiT(tipoLista = tipoLista)
        } catch (e: Error) {
            Toast.makeText(this, mens, Toast.LENGTH_LONG).show()
        } finally {
            val intent = Intent(this, SelListaActivity::class.java)
            startActivity(intent)
        }
    }

        private fun DeletaItens(listvm: ListaViewModel,idlista: String){
            listvm.jogafora(idlista)
        }

    override fun onStop() {
        super.onStop()
        finish()
    }

    }


