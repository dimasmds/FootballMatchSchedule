package id.example.footballmatchschedule.adapter.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.example.footballmatchschedule.R
import id.example.footballmatchschedule.activity.EventDetailActivity
import id.example.footballmatchschedule.model.event.Event
import id.example.footballmatchschedule.tools.BadgeFetcher
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

/**
* Created by dimassaputra on 8/15/18.
*/

class MatchAdapter(private val context : Context, private val matches: MutableList<Event>) :
        RecyclerView.Adapter<MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_view, parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
        holder.itemView.setOnClickListener {
            context.startActivity<EventDetailActivity>("idEvent" to matches[position].idEvent,
                    "idHome" to matches[position].idHomeTeam, "idAway" to matches[position].idAwayTeam)
        }
    }

    override fun getItemCount(): Int = matches.size

}

class MatchViewHolder(view : View) :RecyclerView.ViewHolder(view) {
    private val textViewHomeName : TextView = view.findViewById(R.id.textViewHomeClub)
    private val textViewHomeScore : TextView = view.findViewById(R.id.textViewHomeScore)

    private val textViewAwayName : TextView = view.findViewById(R.id.textViewAwayClub)
    private val textViewAwayScore : TextView = view.findViewById(R.id.textViewAwayScore)


    private val imageViewAwayLogo : ImageView = view.findViewById(R.id.imageViewAwayLogo)
    private val imageViewHomeLogo : ImageView = view.findViewById(R.id.imageViewHomeLogo)

    private val textViewDate : TextView = view.findViewById(R.id.textViewDateMatch)

    fun bindItem(match : Event) {

        textViewHomeName.text = match.strHomeTeam
        if (match.intHomeScore != null){
            textViewHomeScore.text = match.intHomeScore.toString()
        } else {
            textViewHomeScore.text = "V"
        }
        textViewAwayName.text = match.strAwayTeam

        if(match.intAwayScore != null){
            textViewAwayScore.text = match.intAwayScore.toString()
        } else {
            textViewAwayScore.text = "S"
        }

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(match.dateEvent)
        val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()

        textViewDate.text = dateText

        match.idAwayTeam?.let { BadgeFetcher().loadBadges(it, imageViewAwayLogo) }
        match.idHomeTeam?.let { BadgeFetcher().loadBadges(it, imageViewHomeLogo) }
    }

}
