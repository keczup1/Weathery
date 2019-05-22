package com.example.ja.weathery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_city.*
import android.support.design.widget.TabLayout
import android.widget.TabHost
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView







/**
 * Provides UI for the view with Cards.
 */
class CityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, null)
    }

    override fun onResume() {
        super.onResume()

        bCity.setOnClickListener{
            if(cityInput.text.toString()!="") {
                (activity as MainActivity).CITY=cityInput.text.toString()
            val tabhost = activity!!.findViewById(R.id.tabs) as TabLayout
            tabhost.getTabAt(1)!!.select()

                //val host = activity!!.findViewById(android.R.id.tabhost) as TabHost
                //host.currentTab = 1

            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
    }
}

