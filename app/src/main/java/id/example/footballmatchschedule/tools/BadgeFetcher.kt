package id.example.footballmatchschedule.tools

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import id.example.footballmatchschedule.api.APIServices
import id.example.footballmatchschedule.api.Client
import id.example.footballmatchschedule.model.team.Team
import id.example.footballmatchschedule.model.team.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
* Created by dimassaputra on 8/16/18.
*/
class BadgeFetcher {

    fun loadBadges(id : String, image : ImageView) {
        image.setImageDrawable(null)
        try {
            val service : APIServices = Client.getClient().create(APIServices::class.java)
            val call : Call<TeamResponse> = service.getDetailTeam(id)
            call.enqueue(object : Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>?, t: Throwable?) {
                    Log.d("BADGES!", t?.message)
                }

                override fun onResponse(call: Call<TeamResponse>?, response: Response<TeamResponse>?) {
                    if (response != null) {
                        getBadge(response.body()?.teams!!)
                    }
                }

                private fun getBadge(teams: MutableList<Team>) {
                    if(!teams[0].strTeamBadge.isNullOrEmpty()){
                        Picasso.get().load(teams[0].strTeamBadge).into(image)
                    }
                }
            })

        } catch (e: Exception){
            Log.d("BADGES!", e.message)
        }
    }

}