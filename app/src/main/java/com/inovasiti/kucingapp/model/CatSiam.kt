package com.inovasiti.kucingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//Room will treat this class an entity/model can be put into DB
//or @Entity(tablename ="")
@Entity
data class CatSiam(
    @ColumnInfo(name = "siamId")
    @SerializedName("id")
    val siamId: String?,

    @ColumnInfo(name = "cat_name")
    @SerializedName("name")
    val catName: String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val siamGroup: String?,

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val bredFor: String?,

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    val temperament: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val imgUrl: String?
) {
    //PrimaryKey will hold this data class & create inside the body of this class,
    //primary key = is unique id for our DB, so that DB system knows what to retrieve from our Db
    @PrimaryKey(autoGenerate = true)
    var uuid : Long = 0

}

data class CatPalette(var color: Int)