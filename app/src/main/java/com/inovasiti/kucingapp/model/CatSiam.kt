package com.inovasiti.kucingapp.model

import com.google.gson.annotations.SerializedName

data class CatSiam (
    @SerializedName("id")
    val siamId : String?,

    @SerializedName("name")
    val catSiam: String?,

    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val siamGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imgUrl: String?
)