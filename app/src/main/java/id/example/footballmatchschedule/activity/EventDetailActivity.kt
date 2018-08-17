package id.example.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.example.footballmatchschedule.R
import id.example.footballmatchschedule.api.APIServices
import id.example.footballmatchschedule.api.Client
import id.example.footballmatchschedule.model.event.Event
import id.example.footballmatchschedule.model.event.MatchResponse
import id.example.footballmatchschedule.tools.BadgeFetcher
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EventDetailActivity : AppCompatActivity() {

    private lateinit var event : Event

    private lateinit var idHome : String
    private lateinit var idAway : String
    private lateinit var idEvent : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idEvent = intent.getStringExtra("idEvent")
        idHome = intent.getStringExtra("idHome")
        idAway = intent.getStringExtra("idAway")

        loadJson()

        BadgeFetcher().loadBadges(idHome, imageViewBadgeHome)
        BadgeFetcher().loadBadges(idAway, imageViewBadgeAway)
    }

    private fun loadJson() {
        try {
            val service : APIServices = Client.getClient().create(APIServices::class.java)
            val call : Call<MatchResponse> = service.getEventDetail(idEvent)
            call.enqueue(object : Callback<MatchResponse>{
                override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                    t?.message?.let { toast(it) }
                }

                override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                    event = response?.body()?.events?.get(0) ?: return
                    initView()
                }

                private fun initView() {

                    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val date = format.parse(event.dateEvent)
                    val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                            .format(date).toString()

                    textViewEventDate.text = dateText

                    textViewHomeGoal.text = event.strHomeGoalDetails?.replace(";", "\n")
                    textViewAwayGoal.text = event.strAwayGoalDetails?.replace(";", "\n")

                    if(event.intHomeScore != null) textViewHomeScore.text = event.intHomeScore.toString()
                    if(event.intAwayScore != null)textViewAwayScore.text = event.intAwayScore.toString()


                    if(event.intHomeShots != null) TextViewShotHome.text = event.intHomeShots.toString()
                    if(event.intAwayShots != null) TextViewShotAway.text = event.intAwayShots.toString()



                    TextViewGKHome.text = event.strHomeLineupGoalkeeper
                    TextViewGKAway.text = event.strAwayLineupGoalkeeper

                    TextViewDefenseHome.text = event.strHomeLineupDefense?.replace("; ", "\n")
                    TextViewDefenseAway.text = event.strAwayLineupDefense?.replace("; ", "\n")

                    TextViewMidFieldHome.text = event.strHomeLineupMidfield?.replace("; ", "\n")
                    TextViewMidFieldAway.text = event.strAwayLineupMidfield?.replace("; ", "\n")

                    TextViewForwardHome.text = event.strHomeLineupForward?.replace("; ", "\n")
                    TextViewForwardAway.text = event.strAwayLineupForward?.replace("; ", "\n")

                    TextViewSubstitutesHome.text = event.strHomeLineupSubstitutes?.replace("; ", "\n")
                    TextViewSubstitutesAway.text = event.strAwayLineupSubstitutes?.replace("; ", "\n")
                }

            })
        } catch (e : Exception){
            toast(e.message!!)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item != null) {
            when(item.itemId){
                android.R.id.home -> onBackPressed()
            }
            true
        } else {
            false
        }
    }
}
