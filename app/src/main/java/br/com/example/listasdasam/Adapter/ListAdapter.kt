package br.com.example.listasdasam.Adapter

import android.content.Context
import br.com.example.listasdasam.Modelo.Lista
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.AbasFragment
import br.com.example.listasdasam.R


class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.listViewHolder>() {

    var list: List<Lista> = emptyList()


//class ListAdapter(val list: List<Lista>) : RecyclerView.Adapter<ListAdapter.listViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.listViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itens_lista, parent, false)
        return listViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.listViewHolder, position: Int) {
        val lis: Lista = list[position]
        holder.cb.isChecked = checado(lis.checkb)
//        holder.cb.isChecked = lis.checkb
        holder.it.text = (lis.itemlist).capitalize()
//        holder.it.text = (lis.itemlist).split(" ").map { it.capitalize() }.joinToString(" ")
//        holder.valor.text = lis.valor.toString()
    }

    override fun getItemCount(): Int= list.size

//
//    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
//
//    }


    class listViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
        var cb: CheckBox = itemview.findViewById(R.id.cb_itens)
        val it: TextView = itemview.findViewById(R.id.txt_itens)
        var valor: TextView = itemview.findViewById(R.id.txt_valor)

    }

    fun checado(che: Int): Boolean {
        if (che == 1) {
            return true
        } else
            return false
    }

}



