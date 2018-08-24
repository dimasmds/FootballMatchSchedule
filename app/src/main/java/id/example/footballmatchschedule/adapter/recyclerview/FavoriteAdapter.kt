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
import id.example.footballmatchschedule.model.favorite.Favorite
import id.example.footballmatchschedule.tools.BadgeFetcher
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

/**
* Created by dimassaputra on 8/15/18.
*/

class FavoriteAdapter(private val context : Context, private val matches: MutableList<Favorite>) :
        RecyclerView.Adapter<FavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_view, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(matches[position])
        holder.itemView.setOnClickListener {
            context.startActivity<EventDetailActivity>("idEvent" to matches[position].eventId,
                    "idHome" to matches[position].homeId, "idAway" to matches[position].awayId)
        }
    }

    override fun getItemCount(): Int = matches.size
}

class FavoriteViewHolder(view : View) :RecyclerView.ViewHolder(view) {
    private val textViewHomeName : TextView = view.findViewById(R.id.textViewHomeClub)
    private val textViewHomeScore : TextView = view.findViewById(R.id.textViewHomeScore)

    private val textViewAwayName : TextView = view.findViewById(R.id.textViewAwayClub)
    private val textViewAwayScore : TextView = view.findViewById(R.id.textViewAwayScore)


    private val imageViewAwayLogo : ImageView = view.findViewById(R.id.imageViewAwayLogo)
    private val imageViewHomeLogo : ImageView = view.findViewById(R.id.imageViewHomeLogo)

    private val textViewDate : TextView = view.findViewById(R.id.textViewDateMatch)

    fun bindItem(match : Favorite) {

        textViewHomeName.text = match.homeName
        if (match.homeScore != null){
            textViewHomeScore.text = match.homeScore.toString()
        } else {
            textViewHomeScore.text = "V"
        }
        textViewAwayName.text = match.awayName

        if(match.awayScore != null){
            textViewAwayScore.text = match.awayScore.toString()
        } else {
            textViewAwayScore.text = "S"
        }

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(match.eventDate)
        val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()

        textViewDate.text = dateText

        match.awayId?.let { BadgeFetcher().loadBadges(it, imageViewAwayLogo) }
        match.homeId?.let { BadgeFetcher().loadBadges(it, imageViewHomeLogo) }
    }

}
