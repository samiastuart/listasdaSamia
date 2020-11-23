package br.com.example.listasdasam.Adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.example.listasdasam.FragmentLista
import br.com.example.listasdasam.R

//
//private val aba_titulo = arrayOf(
//    R.string.tab_atual,
//    R.string.tab_realizada,
//    R.string.tab_tudo
//
//)


////class AbasAdapter(private val context: Context, fm: FragmentManager, idl:String):FragmentPagerAdapter(fm){

class AbasAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int, idl:String) : FragmentStateAdapter(fm, lifecycle)  {

    var idL: String = idl

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FragmentLista()

        when (position) {
            0 -> {
                val args = Bundle()
                args.putString("aba", "ATUAL")
                args.putString("idL", idL)
                fragment.arguments = args
//                return fragment
            }
            1 -> {
                val args = Bundle()
                args.putString("aba", "REALIZADO")
                args.putString("idL", idL)
                fragment.arguments = args
//                return fragment
            }
            2 -> {
                val args = Bundle()
                args.putString("aba", "TUDO")
                args.putString("idL", idL)
                fragment.arguments = args
//                return fragment
            }
        }
    return fragment
}


//    override fun getItem(position: Int): Fragment {
//        val args = Bundle()
//        val fragment = FragmentLista()
//
//         when (position) {
//             0 -> args.putString("aba", "ATUAL")
//             1 -> args.putString("aba", "REALIZADO")
//             2 -> args.putString("aba", "TUDO")
//             else -> {}
//         }
//        args.putString("idL", idL)
//        fragment.arguments = args
//        return fragment
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return context.resources.getString(aba_titulo[position])
//    }
//
//    override fun getCount(): Int {
//        // São 3 abas (TUDO, REALIZADO e ATUAL)
//        return 3
//    }

}