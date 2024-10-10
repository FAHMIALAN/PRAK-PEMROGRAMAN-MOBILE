package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var currentArtworkIndex by remember { mutableStateOf(0) } // TODO 1: Menggunakan 'remember' untuk menyimpan index karya seni yang sedang ditampilkan
    val artworks = listOf(
        R.drawable.moutain1 to R.string.montain1_title,
        R.drawable.moutain2 to R.string.montain2_title,
        R.drawable.moutain3 to R.string.montain3_title,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp) // TODO 2: Menambahkan padding atas untuk jarak dari tepi layar
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally // TODO 3: Mengatur alignmen horizontal menjadi center
    ) {
        // Menentukan tinggi dan lebar gambar
        ArtworkDisplay(
            artworkResId = artworks[currentArtworkIndex].first,
            width = 350.dp,  // TODO 4: Mengatur lebar gambar sesuai kebutuhan
            height = 550.dp  // TODO 5: Mengatur tinggi gambar sesuai kebutuhan
        )
        Spacer(modifier = Modifier.height(20.dp)) // TODO 6: Menambahkan ruang antara gambar dan judul
        ArtworkTitle(artworks[currentArtworkIndex].second) // TODO 7: Menampilkan judul karya seni berdasarkan index saat ini
        Spacer(modifier = Modifier.height(20.dp)) // TODO 8: Menambahkan ruang antara judul dan tombol navigasi
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), // TODO 9: Menambahkan padding di sekitar baris tombol
            horizontalArrangement = Arrangement.SpaceBetween // TODO 10: Mengatur jarak antara tombol di baris
        ) {
            Button(onClick = {
                if (currentArtworkIndex > 0) currentArtworkIndex-- // TODO 11: Mengurangi index jika bukan yang pertama
                else currentArtworkIndex = artworks.size - 1 // TODO 12: Pindah ke yang terakhir jika sudah di awal
            }) {
                Text(stringResource(R.string.previous_button)) // TODO 13: Menampilkan teks untuk tombol sebelumnya
            }
            Button(onClick = {
                if (currentArtworkIndex < artworks.size - 1) currentArtworkIndex++ // TODO 14: Meningkatkan index jika bukan yang terakhir
                else currentArtworkIndex = 0 // TODO 15: Kembali ke yang pertama jika sudah di akhir
            }) {
                Text(stringResource(R.string.next_button)) // TODO 16: Menampilkan teks untuk tombol berikutnya
            }
        }
    }
}

@Composable
fun ArtworkDisplay(artworkResId: Int, width: Dp, height: Dp) {
    Card(
        modifier = Modifier
            .width(width)  // TODO 17: Mengatur lebar gambar berdasarkan parameter yang diterima
            .height(height),  // TODO 18: Mengatur tinggi gambar berdasarkan parameter yang diterima
        elevation = CardDefaults.cardElevation(defaultElevation = 50.dp) // TODO 19: Menambahkan efek elevasi pada card
    ) {
        Image(
            painter = painterResource(artworkResId),
            contentDescription = null, // TODO 20: Menyediakan deskripsi untuk aksesibilitas (null jika tidak ada)
            modifier = Modifier.fillMaxSize(), // TODO 21: Memastikan gambar mengisi seluruh ukuran card
            contentScale = ContentScale.Crop // TODO 22: Mengatur skala gambar agar sesuai dengan ukuran card
        )
    }
}

@Composable
fun ArtworkTitle(titleResId: Int) {
    Card(
        modifier = Modifier
            .width(400.dp) // TODO 23: Mengatur lebar card untuk judul
            .height(100.dp) // TODO 24: Mengatur tinggi card untuk judul
            .padding(16.dp), // TODO 25: Menambahkan padding di sekitar card
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // TODO 26: Mengatur elevasi card menjadi 0
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),  // TODO 27: Mengisi seluruh ukuran card dengan Box
            contentAlignment = Alignment.Center  // TODO 28: Menjaga teks agar tetap di tengah card
        ) {
            Text(
                text = stringResource(titleResId), // TODO 29: Mengambil dan menampilkan teks judul dari sumber daya
                fontSize = 20.sp, // TODO 30: Mengatur ukuran font untuk teks judul
                modifier = Modifier.padding(horizontal = 16.dp), // TODO 31: Menambahkan padding horizontal pada teks
                fontWeight = FontWeight.Bold // TODO 32: Mengatur berat font menjadi tebal
            )
        }
    }
}
