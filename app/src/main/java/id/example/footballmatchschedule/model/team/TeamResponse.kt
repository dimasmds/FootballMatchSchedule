package id.example.footballmatchschedule.model.team

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
* Created by dimassaputra on 8/16/18.
*/

data class TeamResponse (

    @SerializedName("teams")
    @Expose
    var teams: MutableList<Team>? = null

)
