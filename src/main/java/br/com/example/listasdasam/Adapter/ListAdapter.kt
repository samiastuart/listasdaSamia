package br.com.example.listasdasam.Adapter

//import br.com.example.listasdasam.SelectTracker.selectionTracker
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.R
import br.com.example.listasdasam.ViewModel.ListaViewModel


class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.listViewHolder>() {

    var list: List<Lista> = emptyList()
//    lateinit var selectionTracker: SelectionTracker<String>

//class ListAdapter(val list: List<Lista>) : RecyclerView.Adapter<ListAdapter.listViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.listViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itens_lista, parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.itens_lista, parent, false)
        return listViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListAdapter.listViewHolder, position: Int) {
        val lis: Lista = list[position]
        holder.cb.isChecked = checado(lis.checkb)
//        holder.cb.isChecked = lis.checkb
        holder.it.text = (lis.itemlist).capitalize()
//        holder.it.text = (lis.itemlist).split(" ").map { it.capitalize() }.joinToString(" ")
        holder.valor.text = lis.valor.toString()
        holder.posic.text = lis.posicao.toString()



        holder.it.setOnClickListener {

//            val dialog = AlertDialog.Builder(context).create()
//            var editT: EditText = EditText(context)
//            editT.setText(holder.it.text)
//            editT.selectAll()
//            dialog.setView(editT)

            val dialog = AlertDialog.Builder(context)
            var editT: EditText = EditText(context)
            editT.setText(holder.it.text)
            editT.selectAll()
            dialog.setView(editT)
            dialog.setPositiveButton("ALTERAR"){ _, _ ->
                var texto: String = editT.getText().toString().trim()
                lis.itemlist = texto
                ListaViewModel(application = Application()).atualiza(lis)
            }
            dialog.setNegativeButton("Cancelar"){ dialog, which ->
            }
            dialog.create()
            dialog.show()
        }



        holder.cb.setOnClickListener {
            if (holder.cb.isChecked) {
                Toast.makeText(
                    context,
                    (holder.it.text).toString() + " -> lista REALIZADA",
                    Toast.LENGTH_SHORT
                ).show()
                lis.checkb = 1
            } else {
                Toast.makeText(
                    context,
                    (holder.it.text).toString() + " -> lista ATUAL",
                    Toast.LENGTH_SHORT
                ).show()
                lis.checkb = 0
            }
            ListaViewModel(application = Application()).atualiza(lis)
        }


    }

        class listViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
            var cb: CheckBox = itemview.findViewById(R.id.cb_itens)
            var it: TextView = itemview.findViewById(R.id.txt_itens)
            var valor: TextView = itemview.findViewById(R.id.txt_valor)
            var posic:TextView = itemview.findViewById(R.id.posic)
//            var edit: ImageButton = itemview.findViewById(R.id.edit)
//            var lixo: ImageButton = itemview.findViewById(R.id.lixo)
        }




    override fun getItemCount(): Int= list.size

    private fun checado(che: Int): Boolean {
        if (che == 1) {
            return true
        } else
            return false
    }

    }



