package id.example.footballmatchschedule.model.favorite

/**
* Created by dimassaputra on 8/17/18.
*/
data class Favorite(val id : Long?, val eventId: String?, val eventDate: String?,
                    val homeId: String?, val homeName: String?, val homeScore: Int?,
                    val awayId: String?, val awayName: String?, val awayScore: Int?) {
    companion object {
        const val TABLE_FAVORITE : String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE : String = "EVENT_DATE"
        const val HOME_ID: String = "HOME_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE : String = "HOME_SCORE"
        const val AWAY_ID : String = "AWAY_ID"
        const val AWAY_NAME : String = "AWAY_NAME"
        const val AWAY_SCORE : String = "AWAY_SCORE"
    }
}