package br.com.example.listasdasam

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.MenuPopupWindow
import androidx.collection.CircularArray
import androidx.core.graphics.toColorInt
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.savedstate.SavedStateRegistry
import br.com.example.listasdasam.Adapter.ListAdapter
import br.com.example.listasdasam.Adapter.TipoAdapter
import br.com.example.listasdasam.Database.tipoDao
import br.com.example.listasdasam.Modelo.TipoLista
import br.com.example.listasdasam.Repositorio.ListaRepositorio
import br.com.example.listasdasam.Repositorio.TipoRepositorio
import br.com.example.listasdasam.ViewModel.ListaViewModel
import br.com.example.listasdasam.ViewModel.TipoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SelListaActivity : AppCompatActivity() {

    lateinit var spinner: Spinner


    lateinit var criaLista: FloatingActionButton
//    lateinit var comboLista: Spinner
//    var combo: ArrayList<String> = arrayListOf()
//    var comboID: ArrayList<String> = arrayListOf()


//    lateinit var textocombo: TextView
//    lateinit var cur: CursorAdapter
//    lateinit var tipoLista: TipoLista


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sel_lista)

        val criaLista: FloatingActionButton = findViewById(R.id.btFab)
//        var comboListas: Spinner = findViewById(R.id.comboLista)

        val tipoAdpt = TipoAdapter(this)

        spinner = findViewById<View>(R.id.comboLista) as Spinner
        spinner.adapter = tipoAdpt

        val tipoViewModel by viewModels<TipoViewModel>()

        tipoViewModel.mostraTipo.observe(this, Observer {
            tipoAdpt.tipo = it
            if (it.count() ==1) spinner.isInvisible = true
            tipoAdpt.notifyDataSetChanged()
        })



//        tipoViewModel.mostraTipo.observe(this, Observer {
//            if (it.count() > 0) {
//                var i: Int = 0
//                combo.clear()
//                comboID.clear()
//                combo.add("Clique aqui e selecione a lista a ser utilizada")
//                comboID.add("0")
//                while (i < (it.count())) {
//                    combo.add(it[i].nomeLista.toString())
//                    comboID.add(it[i].idL.toString())
//                    i += 1
//                }
//            } else {
//                comboListas.isInvisible = true
//            }
//            spinnerAdapter.addAll(combo)
//            spinnerAdapter.notifyDataSetChanged()
////
//        })
        //************************************
        //COLOCAR O ID DO TIPOLISTA PRA ENVIAR AO MAINACTIVITY. TAMBÉM ENVIAR O NOME DA LISTA.

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                textView.text = "Selected: "
//                // get selected item text
//                textView.append(parent.getItemAtPosition(position).toString())
//            }

//        spinner.onItemSelectedListener = tipoAdpt.context
//
//
//
//        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val tipoLista : TipoLista = spinner.selectedItem as TipoLista
                val idL: String = tipoLista.idL
                val tipoL: String = tipoLista.nomeLista
                spinner.setSelection(0)
                AbreMain(tipoL, idL)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }


//        if (spinner.isSelected) {
//            val tipoL: String = spinner.selectedItem.toString()
//            val idL: String = spinner.selectedItemId.toString()
//            val intent = Intent(this, MainActivity::class.java)
//            var nome: String = tipoL.toString()
//            var id: String = idL.toString()
//            intent.putExtra("nomeLista", nome)
//            intent.putExtra("idLista", id)
//            startActivity(intent)
//        }

        criaLista.setOnClickListener {
            val intent = Intent(this, CriaListaActivity::class.java)
            startActivity(intent)
        }

    }



    fun AbreMain(tipo:String, id: String){
        if(id != "0" ) {
            val intent = Intent(this, MainActivity::class.java)
            var nome: String = tipo.toString()
            var id: String = id.toString()
            intent.putExtra("nomeLista", nome)
            intent.putExtra("idLista", id)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }

}






