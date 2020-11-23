package br.com.example.listasdasam

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.StringDef
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.FontResourcesParserCompat.parse
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import br.com.example.listasdasam.Database.tipoDao
import br.com.example.listasdasam.Modelo.Lista
import br.com.example.listasdasam.Modelo.TipoLista
import br.com.example.listasdasam.ViewModel.ListaViewModel
import br.com.example.listasdasam.ViewModel.TipoViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

lateinit var nomelista: EditText
lateinit var radiogrupo: RadioGroup
lateinit var tipoLista: TipoLista
//lateinit val btcriar: ExtendedFloatingActionButton

class CriaListaActivity : AppCompatActivity() {
    var id:String = "vazio"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cria_lista)



        var nomelista: EditText = findViewById(R.id.nomeLista)
        val btcriar: FloatingActionButton = findViewById(R.id.criar)

        var radiogrupo:RadioGroup = findViewById(R.id.radioGrupo)

        var sugestoes:String = getString(R.string.sugestoes)
        var listaSugestoes:ArrayList<String> = sugestoes.split(",") as ArrayList<String>
        var i:Int=listaSugestoes.count()

        var radios = listaSugestoes.size

        radiogrupo.removeAllViews()
        for (y in 0 until radios) {
            val rad = RadioButton(ContextThemeWrapper(radiogrupo.context, R.style.Widget_AppCompat_CompoundButton_RadioButton))
            rad.tag = listaSugestoes[y].subSequence(0,6)
            rad.text = listaSugestoes[y]
            rad.textSize = 12F
            rad.id = y
            radiogrupo.addView(rad)
        }


//        radiogrupo.setOnCheckedChangeListener { group, i ->
////            Toast.makeText(this, "Clicou em $i $listaSugestoes[i]", Toast.LENGTH_LONG)
////                .show()
//            var clic:Int  = i - 1
//        }


        btcriar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if (nomelista.getText().toString().trim().equals("")){
                val dialog = AlertDialog.Builder(this).create()
                dialog.setTitle("Você deve prencher o nome da lista")

//                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//
//                    }
//                dialog.setButton(Dialog.BUTTON_POSITIVE, "Você deve preencher o nome da lista!",
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener {
                        dialog, which ->
//                        finish()
                    })
                dialog.show()
            }
            else {
                var clic:Int = radiogrupo.checkedRadioButtonId
                if (clic  >= 0) {
                    var nomeString: String = listaSugestoes[clic].subSequence(0, 6) as String
                    MontaLista(nomeString.subSequence(0,6).toString(), nomelista.text.toString())
                    intent.putExtra("nomeLista",nomelista.text.toString())
                    intent.putExtra("idLista",id)
                    startActivity(intent)
                } else {
                    var nome:String = nomelista.text.toString()
                    gravaLista(nome,"" )
                    intent.putExtra("nomeLista",nome)
                    intent.putExtra("idLista",id)
                    startActivity(intent)
                }
            }
        }
    }
         fun MontaLista(rb:String, nome: String){
             var itensSug: String = ""



             when (rb){
                 "ATIVID" ->{
                     itensSug = getString(R.string.ATIVID)
                 }
                 "COMPRA" -> {
                     itensSug = getString(R.string.COMPRAS)
                 }
                 "MANUTE" ->{
                     itensSug = getString(R.string.MANUTE)
                 }
             }

             gravaLista(nome, itensSug)

//             var str ="R.string." + rb
//             var itensSug:Int = getString(rb)
//             var listaSug:ArrayList<String> = itensSug.split(",") as ArrayList<String>
//             var i:Int=listaSug.count()
            }

    fun gravaLista(nome:String, gravar: String){
        val tipoViewModel by viewModels<TipoViewModel>()
        val listaViewModel by viewModels<ListaViewModel>()
        val tipoLista = TipoLista(nomeLista = nome)
            try {
//                    tipoViewModel.jogafora(lista)
                tipoViewModel.insereTipo(tipoLista)
                id=tipoLista.idL
            } catch (e: Error) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        if (gravar !=""){
            var listaSug:ArrayList<String> = gravar.split(",") as ArrayList<String>
            var l:Int=listaSug.count()
            var i = 0
            while (i < l){
            var lista = Lista(
                checkb = 0,
                itemlist = listaSug[i],
                valor = 0.00F,
                posicao = i + 1,
                idL = id
            )
            try {
//                    listaViewModel.jogafora(lista)
                listaViewModel.insereLista(lista)
//                    args.putString("novoItem", "OK")
//                    fragment.arguments = args
            } catch (e: Error) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
                i += 1
        }
        }
//        intent.putExtra("nomeLista",nome)
//        intent.putExtra("idLista",id)
//        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }


}