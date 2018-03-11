package com.example.davegilbier.pokeapi.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.davegilbier.pokeapi.R
import com.example.davegilbier.pokeapi.models.Pokemon

class PokeAdapter(
        private val mContext : Context,
        private val mPokemonList: ArrayList<Pokemon>) : RecyclerView.Adapter<PokeAdapter.ViewHolder>(){


    fun add(pokemon: Pokemon){
        mPokemonList.add(pokemon)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mPokemonList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.poke_item, parent, false )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = mPokemonList[position]
            holder.pokemonNameLbl.text = pokemon.name.substring(0,1).toUpperCase() + pokemon.name.substring(1)
            Glide.with(mContext).load(pokemon.sprites.frontDefault).into(holder.imgPokemon)

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val imgPokemon = itemView.findViewById<ImageView>(R.id.imgPokemon)
        val pokemonNameLbl = itemView.findViewById<TextView>(R.id.pokemonNameLbl)
    }
}