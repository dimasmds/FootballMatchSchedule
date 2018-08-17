package id.example.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.example.footballmatchschedule.R
import id.example.footballmatchschedule.adapter.recyclerview.MatchAdapter
import id.example.footballmatchschedule.api.APIServices
import id.example.footballmatchschedule.api.Client
import id.example.footballmatchschedule.model.event.Event
import id.example.footballmatchschedule.model.event.MatchResponse
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {


    private var matchs : MutableList<Event> = mutableListOf()

    private var position : Int = 0
    private lateinit var ligaId : String
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : MatchAdapter

    companion object {
        fun newInstance() : MatchFragment{
            return MatchFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)
        position = arguments!!.getInt("position")
        ligaId = arguments!!.getString("ligaId")
        loadJson(rootView)
        return rootView
    }

    private fun loadJson(view: View) {
        try {
            val services : APIServices = Client.getClient().create(APIServices::class.java)
            val call : Call<MatchResponse> =
                    if (position == 0) { services.getPrevMatch(ligaId) }
                    else { services.getNextMatch(ligaId) }

            call.enqueue(object : Callback<MatchResponse>{
                override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                    t?.message?.let { toast(it) }
                }

                override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                    matchs = response?.body()?.events ?: return

                    initViews(view)
                }

            })
        } catch (e : Exception) {
            e.message?.let { toast(it) }
        }
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = MatchAdapter(this.context!!, matchs as ArrayList<Event>)
        recyclerView.adapter = adapter
    }

}// Required empty public constructor
