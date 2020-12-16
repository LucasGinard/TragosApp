package com.example.tragosapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trago(
    @SerializedName("idDrink")
    val tragoId: String = "",
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = ""
) : Parcelable

data class TragoList(
    @SerializedName("drinks")
    val listtrago :List<Trago>)

@Entity
data class TragosEntity(
    @PrimaryKey
    val tragoid:String,
    @ColumnInfo(name ="trago_imagen")
    val imagen: String = "",
    @ColumnInfo(name = "trago_nombre")
    val nombre: String = "",
    @ColumnInfo(name ="trago_descripcion")
    val descripcion: String = ""

)