package id.example.footballmatchschedule.adapter.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import id.example.footballmatchschedule.fragment.MatchFragment

/**
* Created by dimassaputra on 8/15/18.
*/

class MatchPagerAdapter (fm: FragmentManager?, private val noOfTabs : Int, private val idLiga : String) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
       val b = Bundle()
        b.putInt("position", position)
        b.putString("ligaId", idLiga)
        val frag = MatchFragment.newInstance()
        frag.arguments = b
        return frag
    }

    override fun getCount(): Int  = noOfTabs

}