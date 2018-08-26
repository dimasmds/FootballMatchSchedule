package id.example.footballmatchschedule.tools

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.example.footballmatchschedule.model.favorite.Favorite
import org.jetbrains.anko.db.*

/**
* Created by dimassaputra on 8/17/18.
*/
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance : MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : MyDatabaseOpenHelper{
            if(instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }



    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.EVENT_DATE to TEXT,
                Favorite.HOME_ID to TEXT,
                Favorite.HOME_NAME to TEXT,
                Favorite.HOME_SCORE to INTEGER,
                Favorite.AWAY_ID to TEXT,
                Favorite.AWAY_NAME to TEXT,
                Favorite.AWAY_SCORE to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database : MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)