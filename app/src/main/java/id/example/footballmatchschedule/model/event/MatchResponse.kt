package id.example.footballmatchschedule.model.event

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
* Created by dimassaputra on 8/15/18.
*/

data class MatchResponse(
    @SerializedName("events")
    @Expose
    var events: MutableList<Event>? = null
)
