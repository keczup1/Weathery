package com.example.ja.weathery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_now.*
import org.json.JSONObject
import java.lang.reflect.Method
import java.text.SimpleDateFormat
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.fragment_now.view.*
import android.support.v4.widget.SwipeRefreshLayout




/**
 * Provides UI for the view with Cards.
 */
class NowFragment : Fragment() {

    var city=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_now, null)
        view.ExternalLinkTV.setOnClickListener {
            val url = "https://www.twojapogoda.pl"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        view.Brefresh.setOnClickListener {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
        return view

    }

    override fun onResume() {
        super.onResume()


        //(activity as MainActivity).CITY="Pyskowice"
        //Toast.makeText(context,"jestem", Toast.LENGTH_LONG).show()
        city=(activity as MainActivity).CITY
        //val city="Rybnik"

        val url="http://api.openweathermap.org/data/2.5/weather?q=$city&APPID=ea574594b9d36ab688642d5fbeab847e&units=metric&lang=pl"
        //var jor: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, )

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                /*val news = response
                    .getJSONObject("query")
                    .getJSONObject("results")
                    .getJSONArray("item")

                newsRecyclerView.adapter = NewsAdapter(news)*/
                val main_object=response.getJSONObject("main")
                val array = response.getJSONArray("weather")
                val clouds_object=response.getJSONObject("clouds")
                val sys=response.getJSONObject("sys")
                val wind_object=response.getJSONObject("wind")
                val obj=array.getJSONObject(0)

                val temp=main_object.getDouble("temp").toString()
                val pressure=main_object.getDouble("pressure").toString()
                val humidity=main_object.getDouble("humidity").toString()
                val temp_min=main_object.getDouble("temp_min").toString()
                val temp_max=main_object.getDouble("temp_max").toString()
                val clouds=clouds_object.getDouble("all").toString()
                val sunrise=sys.getLong("sunrise")
                val sunset=sys.getLong("sunset")
                val description=obj.getString("description")
                val icon=obj.getString("icon")
                val wind=wind_object.getDouble("speed").toString()
                val datetime=response.getLong("dt")
                val city=response.getString("name")


                val DTdv = java.lang.Long.valueOf(datetime) * 1000// its need to be in milisecond
                val DTdf = java.util.Date(DTdv)
                val DTvv = SimpleDateFormat("dd.MM.yyyy HH:mm").format(DTdf)

                val SUNRdv = java.lang.Long.valueOf(sunrise) * 1000// its need to be in milisecond
                val SUNRdf = java.util.Date(SUNRdv)
                val SUNRvv = SimpleDateFormat("HH:mm").format(SUNRdf)

                val SUNSdv = java.lang.Long.valueOf(sunset) * 1000// its need to be in milisecond
                val SUNSdf = java.util.Date(SUNSdv)
                val SUNSvv = SimpleDateFormat("HH:mm").format(SUNSdf)

                val sdf = SimpleDateFormat("EEEE")
                val dateFormat = java.util.Date(datetime * 1000)
                val weekday = sdf.format(dateFormat)

                datetimeTV.text=weekday+" "+DTvv.toString()
                TemperatureTV.text=temp+"°C"
                cityTV.text=city
                DescriptionTV.text=description
                MinTemptv.text=temp_min+"°C"
                MaxTemptv.text=temp_max+"°C"
                Sunrisetv.text=SUNRvv.toString()
                Sunsettv.text=SUNSvv.toString()
                Cloudstv.text=clouds+"%"
                Windtv.text=wind+"m/s"
                Humiditytv.text=humidity+"%"
                Pressuretv.text=pressure+"hPa"

                if(icon=="01d")
                    weatherIcon.setImageResource(R.drawable.i01d)
                if(icon=="01n")
                    weatherIcon.setImageResource(R.drawable.i01n)
                if(icon=="02d")
                    weatherIcon.setImageResource(R.drawable.i02d)
                if(icon=="02n")
                    weatherIcon.setImageResource(R.drawable.i02n)
                if(icon=="03d")
                    weatherIcon.setImageResource(R.drawable.i03d)
                if(icon=="03n")
                    weatherIcon.setImageResource(R.drawable.i03n)
                if(icon=="04d")
                    weatherIcon.setImageResource(R.drawable.i04d)
                if(icon=="04n")
                    weatherIcon.setImageResource(R.drawable.i04n)
                if(icon=="09d")
                    weatherIcon.setImageResource(R.drawable.i09d)
                if(icon=="09n")
                    weatherIcon.setImageResource(R.drawable.i09n)
                if(icon=="10d")
                    weatherIcon.setImageResource(R.drawable.i10d)
                if(icon=="10n")
                    weatherIcon.setImageResource(R.drawable.i10n)
                if(icon=="11d")
                    weatherIcon.setImageResource(R.drawable.i11d)
                if(icon=="11n")
                    weatherIcon.setImageResource(R.drawable.i11n)
                if(icon=="13d")
                    weatherIcon.setImageResource(R.drawable.i13d)
                if(icon=="13n")
                    weatherIcon.setImageResource(R.drawable.i13n)
                if(icon=="50d")
                    weatherIcon.setImageResource(R.drawable.i50d)
                if(icon=="50n")
                    weatherIcon.setImageResource(R.drawable.i50n)


            },
            Response.ErrorListener {
                Toast.makeText(context, "You need to turn on the Internet data!", Toast.LENGTH_LONG).show()
                TemperatureTV.text=""
                DescriptionTV.text=""
                weatherIcon.setImageResource(R.drawable.offline)
            })

        val queue=Volley.newRequestQueue(context)
        queue.add(request)


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
    }


}

