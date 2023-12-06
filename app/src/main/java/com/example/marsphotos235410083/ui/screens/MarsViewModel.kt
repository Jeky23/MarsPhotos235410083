package com.example.marsphotos235410083.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.marsphotos235410083.network.MarsApi
import java.io.IOException

    /**sealed interface memudahkan pengelolaan status dengan membatasi kemungkinan nilai
     * yaitu membatasi respons web marsUiState ke tiga status (objek class data): memuat, berhasil, dan error*/
sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {
    /** Status yang dapat berubah yang menyimpan status permintaan terbaru */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**Panggil getMarsPhotos () di init sehingga dapat segera menampilkan status*/
    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {


        /**viewModelScope untuk meluncurkan coroutine dan membuat permintaan layanan web di latar belakang*/
        viewModelScope.launch {


            /**blok try-catch untuk menangani pengecualian dalam runtime
             * blok try akan menambahkan kode tempat mengantisipasi pengecualian. Di aplikasi ini adalah panggilan jaringan*/
            marsUiState = try {


                /**objek singleton MarsApi untuk memanggil metode getPhotos() dari antarmuka retrofitService
                 *Simpan respons yang ditampilkan dalam val yang bernama listResult */
                val listResult = MarsApi.retrofitService.getPhotos()

                /**Tetapkan hasil yang baru saja diterima dari server backend ke marsUiState
                 * marsUiState adalah objek status yang dapat diubah yang mewakili status permintaan web terbaru*/
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            }
            /**blok catch dijalankan untuk pulih dari eror dan mencegah penghentian aplikasi secara tiba-tiba*/
            catch (e: IOException) {
                MarsUiState.Error /**untuk menangani respons kegagalan*/

            }
        }
    }
}
