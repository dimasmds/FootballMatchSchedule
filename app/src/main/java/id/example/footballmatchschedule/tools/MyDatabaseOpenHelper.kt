package id.example.footballmatchschedule.tools

import android.content.Context
import android.database.sqlite.SQLiteDatabase
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
            return instance!!
        }
    }



    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable("TABLE_FAVORITE", true,
                "ID_" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "TEAM_ID" to TEXT + UNIQUE,
                "TEAM_NAME" to TEXT,
                "TEAM_BADGE" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("TABLE_FAVORITE", true)
    }
}

val Context.database : MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)