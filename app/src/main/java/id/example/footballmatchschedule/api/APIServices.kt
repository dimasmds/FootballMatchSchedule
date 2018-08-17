package id.example.footballmatchschedule.api

import id.example.footballmatchschedule.model.league.LigaResponse
import id.example.footballmatchschedule.model.event.MatchResponse
import id.example.footballmatchschedule.model.team.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
* Created by dimassaputra on 8/15/18.
*/

interface APIServices {

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") ligaId: String): Call<MatchResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") ligaId: String) : Call<MatchResponse>

    @GET("api/v1/json/1/all_leagues.php")
    fun getAllLiga() : Call<LigaResponse>

    @GET("api/v1/json/1/lookupteam.php?")
    fun getDetailTeam(@Query("id") teamId: String) : Call<TeamResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetail(@Query("id") teamId: String) : Call<MatchResponse>

}
