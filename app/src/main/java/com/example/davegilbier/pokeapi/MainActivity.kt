package com.example.davegilbier.pokeapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.example.davegilbier.pokeapi.adapters.PokeAdapter
import com.example.davegilbier.pokeapi.models.Pokemon
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val mTag ="PokeAPI"
    private val mLimit = 20

    private var mCounter= 1

    private lateinit var mAdapter: PokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = PokeAdapter(this, ArrayList())
        val layoutManager = GridLayoutManager(this,2)

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager= layoutManager

        fetchPokemons()
    }

    private fun fetchPokemons() {
        val url =  "https://pokeapi.co/api/v2/pokemon/$mCounter/"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e(mTag,"Failed to get pokemon", e)
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response!= null && response.isSuccessful){
                   val json= response.body()?.string()
                   addToList(json)
                }
            }


        })
    }

    private fun addToList(json: String?) {
        runOnUiThread {
            val gson = GsonBuilder().create()
            val pokemon = gson.fromJson(json , Pokemon::class.java)
            mAdapter.add(pokemon)

            if(mCounter == 1){
                pokemonUpdateLbl.text = "Great! You already have a pokemon!"
            }else{
                pokemonUpdateLbl.text = "Yayyy! You now have $mCounter pokemons!"
            }
            mCounter++

            if (mCounter <= mLimit){
                fetchPokemons()
            } else {
                progressBar.visibility= View.GONE
            }
        }
    }
}
