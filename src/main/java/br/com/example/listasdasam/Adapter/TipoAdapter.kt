package br.com.example.listasdasam.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.Database.tipoDao
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista
import br.com.example.listasdasam.R

lateinit var idl: TextView
lateinit var item: TextView

class TipoAdapter (val context: Context) : BaseAdapter(){


    var tipo : List<TipoLista> = emptyList()

    override fun getCount(): Int = tipo.size


    override fun getItem(position: Int): Any {
       return tipo[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_tipo_lista,parent,false)//
        val tip: TipoLista = tipo[position]
        idl = view.findViewById(R.id.idTipo)
        item = view.findViewById(R.id.textTipo)
        idl.text = tip.idL
        item.text = tip.nomeLista
        return view
        }



    }
