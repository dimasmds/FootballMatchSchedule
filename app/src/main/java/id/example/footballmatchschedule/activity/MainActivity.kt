package id.example.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import id.example.footballmatchschedule.R
import id.example.footballmatchschedule.adapter.viewpager.MatchPagerAdapter
import id.example.footballmatchschedule.api.APIServices
import id.example.footballmatchschedule.api.Client
import id.example.footballmatchschedule.model.league.League
import id.example.footballmatchschedule.model.league.LigaResponse
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var matchAdapter : MatchPagerAdapter
    private lateinit var ligas : MutableList<League>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Football Match Schedule"
        tabLayout.addTab(tabLayout.newTab().setText("Prev Match"))
        tabLayout.addTab(tabLayout.newTab().setText("Next Match"))


        loadLiga()
    }

    private fun loadLiga() {
        try{
            val service : APIServices = Client.getClient().create(APIServices::class.java)
            val call : Call<LigaResponse> = service.getAllLiga()
            call.enqueue(object : Callback<LigaResponse>{
                override fun onFailure(call: Call<LigaResponse>?, t: Throwable?) {
                    t?.message?.let { toast(it) }
                }

                override fun onResponse(call: Call<LigaResponse>?, response: Response<LigaResponse>?) {
                    if (response != null) {
                        ligas = response.body()?.leagues!!
                        loadViews()
                    }
                }

            })
        } catch (e: Exception){
            e.message?.let { toast(it) }
        }
    }

    private fun loadViews() {
        val arraySpinner : MutableList<String> = mutableListOf()
        for (index in ligas.indices){
            if(index == 22){
                break
            }
            ligas[index].strLeague?.let { arraySpinner.add(index, it) }
        }

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,
                R.layout.spinner_item, arraySpinner)

        spinner.adapter = arrayAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val idLiga = ligas[spinner.selectedItemPosition].idLeague
                matchAdapter = MatchPagerAdapter(supportFragmentManager, tabLayout.tabCount, idLiga!!)
                viewPager.adapter = matchAdapter
                viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            }

        }
    }
}
