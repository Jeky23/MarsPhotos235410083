package com.example.marsphotos235410083.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/** class MarsPhoto yang dapat diserialisasi dianotasi dengan @Serializable*/
@Serializable
data class MarsPhoto(
    val id: String,

    /**@SerialName digunakan untuk ntuk menggunakan nama variabel di class data yang berbeda dari nama kunci dalam respons JSON*/
    @SerialName(value = "img_src")
    val imgSrc: String
)

