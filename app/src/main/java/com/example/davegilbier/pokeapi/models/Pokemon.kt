package com.example.davegilbier.pokeapi.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Dave Gilbier on 11/03/2018.
 */
data class Pokemon(val name : String , val sprites: Sprite )

data class Sprite(@SerializedName("front_default") val frontDefault : String)