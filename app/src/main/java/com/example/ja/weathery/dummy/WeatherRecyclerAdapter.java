package com.example.ja.weathery.dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ja.weathery.MainActivity;
import com.example.ja.weathery.MoreFragment;
import com.example.ja.weathery.R;

import java.util.ArrayList;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {
    private static final String TAG = "WeatherRecyclerAdapter";

    private ArrayList<String> CIcon = new ArrayList<>();
    private ArrayList<String> CHour = new ArrayList<>();
    private ArrayList<String> CTemperatures = new ArrayList<>();
    private ArrayList<String> CDescriptions = new ArrayList<>();
    private ArrayList<String> CWind = new ArrayList<>();
    private ArrayList<String> CClouds = new ArrayList<>();
    private Context CContext;

    public WeatherRecyclerAdapter(ArrayList<String> CIcon, ArrayList<String> CHour, ArrayList<String> CTemperatures, ArrayList<String> CDescriptions, ArrayList<String> CWind, ArrayList<String> CClouds, Context CContext) {
        this.CIcon = CIcon;
        this.CHour = CHour;
        this.CTemperatures = CTemperatures;
        this.CDescriptions = CDescriptions;
        this.CWind = CWind;
        this.CClouds = CClouds;
        this.CContext = CContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Glide.with(CContext).asBitmap().load("i"+CIcon.get(i)).into(viewHolder.icon);
        //Glide.with(CContext).asBitmap().load("https://i.redd.it/xt3lm2mu91q21.jpg").into(viewHolder.icon);

        if(CIcon.get(i).equals("01d"))
            viewHolder.icon.setImageResource(R.drawable.i01d);
        if(CIcon.get(i).equals("01n"))
            viewHolder.icon.setImageResource(R.drawable.i01n);
        if(CIcon.get(i).equals("02d"))
            viewHolder.icon.setImageResource(R.drawable.i02d);
        if(CIcon.get(i).equals("02n"))
            viewHolder.icon.setImageResource(R.drawable.i02n);
        if(CIcon.get(i).equals("03d"))
            viewHolder.icon.setImageResource(R.drawable.i03d);
        if(CIcon.get(i).equals("03n"))
            viewHolder.icon.setImageResource(R.drawable.i03n);
        if(CIcon.get(i).equals("04d"))
            viewHolder.icon.setImageResource(R.drawable.i04d);
        if(CIcon.get(i).equals("04n"))
            viewHolder.icon.setImageResource(R.drawable.i04n);
        if(CIcon.get(i).equals("09d"))
            viewHolder.icon.setImageResource(R.drawable.i09d);
        if(CIcon.get(i).equals("09n"))
            viewHolder.icon.setImageResource(R.drawable.i09n);
        if(CIcon.get(i).equals("10d"))
            viewHolder.icon.setImageResource(R.drawable.i10d);
        if(CIcon.get(i).equals("10n"))
            viewHolder.icon.setImageResource(R.drawable.i10n);
        if(CIcon.get(i).equals("11d"))
            viewHolder.icon.setImageResource(R.drawable.i11d);
        if(CIcon.get(i).equals("11n"))
            viewHolder.icon.setImageResource(R.drawable.i11n);
        if(CIcon.get(i).equals("13d"))
            viewHolder.icon.setImageResource(R.drawable.i13d);
        if(CIcon.get(i).equals("13n"))
            viewHolder.icon.setImageResource(R.drawable.i13n);
        if(CIcon.get(i).equals("50d"))
            viewHolder.icon.setImageResource(R.drawable.i50d);
        if(CIcon.get(i).equals("50n"))
            viewHolder.icon.setImageResource(R.drawable.i50n);

        viewHolder.hour.setText(CHour.get(i));
        viewHolder.temperature.setText(CTemperatures.get(i)+"°C");
        viewHolder.description.setText(CDescriptions.get(i));
        viewHolder.wind.setText("Wiatr: "+CWind.get(i)+"m/s");
        viewHolder.clouds.setText("Zachmurzenie: "+CClouds.get(i)+"%");

    }

    @Override
    public int getItemCount() {
        return CDescriptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView hour;
        TextView temperature;
        TextView description;
        TextView wind;
        TextView clouds;
        RelativeLayout parentlayout;
        CardView card;
        ConstraintLayout cardparent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.weatherIcon);
            hour=itemView.findViewById(R.id.HourTV);
            temperature=itemView.findViewById(R.id.TemperatureTV);
            description=itemView.findViewById(R.id.DescriptionTV);
            wind=itemView.findViewById(R.id.WindTV);
            clouds=itemView.findViewById(R.id.CloudsTV);
            parentlayout=itemView.findViewById(R.id.ParentLayout);
            card=itemView.findViewById(R.id.Card);
            cardparent=itemView.findViewById(R.id.CardHolder);
        }
    }
}
