package id.example.footballmatchschedule.model.league

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
* Created by dimassaputra on 8/15/18.
*/

data class LigaResponse (

    @SerializedName("leagues")
    @Expose
    var leagues: MutableList<League>? = null
)
