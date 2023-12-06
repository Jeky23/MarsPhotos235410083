package com.example.marsphotos235410083.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

/** URL dasar untuk layanan web*/
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


    /** builder Retrofit untuk membangun dan membuat objek Retrofit.*/
private val retrofit = Retrofit.Builder()
    /** factory pengonversi untuk membangun API layanan web*/
         .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
         .baseUrl(BASE_URL)/**URL dasar untuk layanan web */
         .build()/**untuk membuat objek Retrofit.*/


    /**antarmuka yang disebut MarsApiService yang menentukan cara Retrofit
    * berkomunikasi dengan server web menggunakan permintaan HTTP.*/
interface MarsApiService {
    /**anotasi @GET untuk memberi tahu Retrofit bahwa ini adalah permintaan GET dan tentukan endpoint
     * berupa foto untuk metode layanan web tersebut. */
    @GET("photos")
    /** fungsi yang bernama getPhotos(): List<MarsPhoto> untuk Retrofit guna menampilkan daftar objek MarsPhoto, bukan String.*/
    suspend fun getPhotos(): List<MarsPhoto>
}

    /** objek publik bernama MarsApi untuk menginisialisasi layanan Retrofit*/
object MarsApi {
        /**properti objek retrofit yang diinisialisasi dengan lambat bernama retrofitService dari jenis MarsApiService*/
    val retrofitService : MarsApiService by lazy {
        /**retrofit.create() dengan antarmuka MarsApiService untuk inisialisasi variabel retrofitService*/
        retrofit.create(MarsApiService::class.java)
    }
}
