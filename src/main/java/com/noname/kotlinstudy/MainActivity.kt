package com.noname.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.noname.kotlinstudy.databinding.ActivityMainBinding
import com.noname.kotlinstudy.model.People
import com.noname.kotlinstudy.model.StarWars
import com.noname.kotlinstudy.view.StarAdapterAlter
import com.noname.kotlinstudy.view.StarWarsAdapter
import com.noname.kotlinstudy.viewmodel.CustomViewModel
import com.noname.kotlinstudy.viewmodel.CustomViewModelAlter
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var TAG : String
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var customViewModel: CustomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = this.packageName
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        customViewModel = ViewModelProviders.of(this@MainActivity).get(CustomViewModel::class.java)

        val starAdapterAlter = StarAdapterAlter()
        activityMainBinding.recycler.adapter = starAdapterAlter
        activityMainBinding.recycler.layoutManager = LinearLayoutManager(this)

        val res : Call<StarWars> = NetRetrofit.getInstance().service.getPeople("json")
        res.enqueue(object : Callback<StarWars> {

            override fun onResponse(call: Call<StarWars>, response: Response<StarWars>) {

                val list : List<People> = response.body()?.results as List<People>
                for (people in list){
                    Log.d(TAG, "체크" + people.name)
                    customViewModel.insert(people)
                }

                customViewModel.listLiveData.observe(this@MainActivity , object : Observer<PagedList<People>> {
                    override fun onChanged(t: PagedList<People>?) {
                        if (t != null) {
                            Log.d(TAG, "체크2" + t.size)
                        }

                        starAdapterAlter.submitList(t)
                    }
                })
            }

            override fun onFailure(call: Call<StarWars>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}
