package br.com.example.listasdasam.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.example.listasdasam.AbasFragment
import br.com.example.listasdasam.R
import kotlinx.coroutines.withContext


private val aba_titulo = arrayOf(
    R.string.aba_atual,
    R.string.aba_exec,
    R.string.aba_completa
)

class AbasAdapter(private val context: Context, fm: FragmentManager):FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return AbasFragment(context.resources.getString(aba_titulo[position]))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(aba_titulo[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}