package com.example.ja.weathery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import android.annotation.SuppressLint
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.widget.Toast
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var data: JSONObject? = null
    var CITY="Gliwice"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        //setSupportActionBar(toolbar)
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)
        val tabs = findViewById<View>(R.id.tabs) as TabLayout
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(1)?.select()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(CityFragment(), "Zmień miasto")
        adapter.addFragment(NowFragment(), "Teraz")
        adapter.addFragment(MoreFragment(), "Szczegóły")
        viewPager.adapter = adapter

    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }
    override fun onResume() {
        super.onResume()
        MainLayout.setBackgroundResource(R.drawable.background1)
        //Toast.makeText(this@MainActivity, "MAIN: "+CITY, Toast.LENGTH_LONG).show()
    }

}
