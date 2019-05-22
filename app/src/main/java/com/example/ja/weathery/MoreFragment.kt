package com.example.ja.weathery

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ja.weathery.dummy.WeatherRecyclerAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_more.*
import org.json.JSONObject
import java.lang.Long
import java.util.*
import java.nio.file.Files.size
import java.text.SimpleDateFormat
import android.R.attr.data
import android.annotation.SuppressLint
import android.graphics.Color
import android.text.method.TextKeyListener.clear
import kotlinx.android.synthetic.main.fragment_more.view.*
import java.nio.file.Files.size
import kotlin.collections.ArrayList


/**
 * Provides UI for the view with Cards.
 */
class MoreFragment : Fragment() {

    private val CIcon = ArrayList<String>()
    private val CHour = ArrayList<String>()
    private val CTemperatures = ArrayList<String>()
    private val CDescriptions = ArrayList<String>()
    private val CWind = ArrayList<String>()
    private val CClouds = ArrayList<String>()
    private val MyIcon = ArrayList<String>()
    private val MyHour = ArrayList<String>()
    private val MyTemperatures = ArrayList<String>()
    private val MyDescriptions = ArrayList<String>()
    private val MyWind = ArrayList<String>()
    private val MyClouds = ArrayList<String>()
    private val DATE=ArrayList<String>()
    private val DateRange=ArrayList<String>()
    private val WeekdayRange=ArrayList<String>()
    private var colorAccent="#5e35b1"
    private var textColorPrimary = "#ede7f6"
    private var textColorWhite = "#fbfffe"
    private var textColorBlack= "#000000"


    var city=""
    var listsize=0


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_more, null)

        view.bDay1.setOnClickListener {
            parseData(1)
            view.bDay1.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay2.setBackgroundColor(Color.TRANSPARENT)
            view.bDay3.setBackgroundColor(Color.TRANSPARENT)
            view.bDay4.setBackgroundColor(Color.TRANSPARENT)
            view.bDay5.setBackgroundColor(Color.TRANSPARENT)
            view.bDay6.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[0]
        }
        view.bDay2.setOnClickListener {
            parseData(2)
            view.bDay2.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay1.setBackgroundColor(Color.TRANSPARENT)
            view.bDay3.setBackgroundColor(Color.TRANSPARENT)
            view.bDay4.setBackgroundColor(Color.TRANSPARENT)
            view.bDay5.setBackgroundColor(Color.TRANSPARENT)
            view.bDay6.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[1]
        }
        view.bDay3.setOnClickListener {
            parseData(3)
            view.bDay3.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay2.setBackgroundColor(Color.TRANSPARENT)
            view.bDay1.setBackgroundColor(Color.TRANSPARENT)
            view.bDay4.setBackgroundColor(Color.TRANSPARENT)
            view.bDay5.setBackgroundColor(Color.TRANSPARENT)
            view.bDay6.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[2]
        }
        view.bDay4.setOnClickListener {
            parseData(4)
            view.bDay4.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay2.setBackgroundColor(Color.TRANSPARENT)
            view.bDay3.setBackgroundColor(Color.TRANSPARENT)
            view.bDay1.setBackgroundColor(Color.TRANSPARENT)
            view.bDay5.setBackgroundColor(Color.TRANSPARENT)
            view.bDay6.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[3]
        }
        view.bDay5.setOnClickListener {
            parseData(5)
            view.bDay5.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay2.setBackgroundColor(Color.TRANSPARENT)
            view.bDay3.setBackgroundColor(Color.TRANSPARENT)
            view.bDay4.setBackgroundColor(Color.TRANSPARENT)
            view.bDay1.setBackgroundColor(Color.TRANSPARENT)
            view.bDay6.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[4]
        }
        view.bDay6.setOnClickListener {
            parseData(6)
            view.bDay6.setBackgroundColor(Color.parseColor(colorAccent))
            view.bDay2.setBackgroundColor(Color.TRANSPARENT)
            view.bDay3.setBackgroundColor(Color.TRANSPARENT)
            view.bDay4.setBackgroundColor(Color.TRANSPARENT)
            view.bDay5.setBackgroundColor(Color.TRANSPARENT)
            view.bDay1.setBackgroundColor(Color.TRANSPARENT)
            WeekDayTV.text=WeekdayRange[5]
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        CIcon.clear()
        CHour.clear()
        CTemperatures.clear()
        CDescriptions.clear()
        CWind.clear()
        CClouds.clear()
        DATE.clear()
        DateRange.clear()
        WeekdayRange.clear()
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        city=(activity as MainActivity).CITY

        initData()
    }

    @SuppressLint("ResourceAsColor")
    private fun initData(){

        val Lurl="https://api.openweathermap.org/data/2.5/forecast?q=$city,pl&units=metric&appid=ea574594b9d36ab688642d5fbeab847e&lang=pl"
        val Lrequest = JsonObjectRequest(
            Request.Method.GET, Lurl, null,
            Response.Listener<JSONObject> { response ->
                val list=response.getJSONArray("list")
                listsize = list.length()

                for (i in 0 until listsize) {
                    val listelement = list.get(i) as JSONObject
                    val datetime=listelement.getLong("dt")
                    val main_object=listelement.getJSONObject("main")
                    val temp=main_object.getDouble("temp").toString()
                    val weather = listelement.getJSONArray("weather")
                    val obj=weather.getJSONObject(0)
                    val description=obj.getString("description")
                    val icon=obj.getString("icon")
                    val clouds_object=listelement.getJSONObject("clouds")
                    val wind_object=listelement.getJSONObject("wind")
                    val clouds=clouds_object.getDouble("all").toString()
                    val wind=wind_object.getDouble("speed").toString()

                    val DTdv = Long.valueOf(datetime) * 1000// its need to be in milisecond
                    val DTdf = Date(DTdv)
                    val DTvv = SimpleDateFormat("HH:mm").format(DTdf)
                    DATE.add(SimpleDateFormat("dd.MM").format(DTdf))

                    val sdf = SimpleDateFormat("EEEE")
                    val dateFormat = java.util.Date(datetime * 1000)
                    val weekday = sdf.format(dateFormat)
                    val dateday=SimpleDateFormat("dd.MM").format(DTdf)
                    if(i==0) {
                        DateRange.add(dateday)
                        WeekdayRange.add(weekday)
                    }
                    if(i!=0&&dateday!=DateRange[DateRange.size-1]) {
                        DateRange.add(dateday)
                        WeekdayRange.add(weekday)
                    }

                    CIcon.add(icon)
                    CHour.add(DTvv)
                    CTemperatures.add(temp)
                    CDescriptions.add(description)
                    CWind.add(wind)
                    CClouds.add(clouds)
                }

            },
            Response.ErrorListener {
                Toast.makeText(context, "You need to turn on the Internet data!", Toast.LENGTH_LONG).show()
            })

        val Lqueue= Volley.newRequestQueue(context)
        Lqueue.add(Lrequest)
        //Toast.makeText(context, DateRange.size.toString(), Toast.LENGTH_LONG).show()
        if(DateRange.size>0)
        {
            //Toast.makeText(context, DateRange.toString(), Toast.LENGTH_LONG).show()
            bDay1.text=DateRange[0]
            bDay2.text=DateRange[1]
            bDay3.text=DateRange[2]
            bDay4.text=DateRange[3]
            bDay5.text=DateRange[4]
            bDay6.text=DateRange[5]
            bDay1.setBackgroundColor(Color.parseColor(colorAccent))
            bDay1.setTextColor(Color.parseColor(textColorWhite))
            WeekDayTV.text=WeekdayRange[0]
        }

        parseData(1)
    }

    fun parseData(day: Int) {
        if(MyDescriptions.size>0){
            MyIcon.clear()
            MyHour.clear()
            MyTemperatures.clear()
            MyDescriptions.clear()
            MyWind.clear()
            MyClouds.clear()
        }
        if(DateRange.size>0) {
            val init = DateRange[day-1]
            //Toast.makeText(context, init, Toast.LENGTH_LONG).show()
            var i=DATE.indexOf(init)
            while (DATE[i]==init){
                MyIcon.add(CIcon[i])
                MyHour.add(CHour[i])
                MyTemperatures.add(CTemperatures[i])
                MyDescriptions.add(CDescriptions[i])
                MyWind.add(CWind[i])
                MyClouds.add(CClouds[i])
                i++
            }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        weather_recycler.adapter=WeatherRecyclerAdapter(MyIcon, MyHour,MyTemperatures,MyDescriptions,MyWind,MyClouds,activity)
        //weather_recycler.adapter=WeatherRecyclerAdapter(CIcon, CHour,CTemperatures,CDescriptions,CWind,CClouds,activity)
        weather_recycler.layoutManager=LinearLayoutManager(activity)
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
    }
}

