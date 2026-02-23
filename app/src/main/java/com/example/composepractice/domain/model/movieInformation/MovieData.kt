package com.example.composeproject.data.models

import com.google.gson.annotations.SerializedName

data class MovieData (

    @SerializedName("Title"   ) var Title   : String? = null,
    @SerializedName("Runtime" ) var Runtime : String? = null,

)
