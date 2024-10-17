package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

// Kelas utama (MainActivity) yang merupakan subclass dari ComponentActivity
class MainActivity : ComponentActivity() {

    // Fungsi onCreate yang dipanggil saat activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Menyeting tema aplikasi AffirmationsTheme
            AffirmationsTheme {
                // Surface untuk menampilkan konten utama dengan latar belakang dari tema
                Surface(
                    modifier = Modifier.fillMaxSize(), // Mengisi seluruh layar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna background dari tema
                ) {
                    // Memanggil fungsi composable AffirmationsApp untuk menampilkan daftar afirmasi
                    AffirmationsApp()
                }
            }
        }
    }
}

// Fungsi composable yang menampilkan daftar afirmasi
@Composable
fun AffirmationsApp() {
    // Memanggil fungsi AffirmationList dan mengirim data afirmasi dari Datasource
    AffirmationList(
        affirmationList = Datasource().loadAffirmations(),
    )
}

// Fungsi composable yang menampilkan daftar afirmasi dalam bentuk LazyColumn (daftar bergulir)
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        // Menggunakan items untuk memetakan setiap item (afirmasi) dalam daftar
        items(affirmationList) { affirmation ->
            // Menampilkan setiap afirmasi dalam bentuk kartu (Card)
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp) // Memberikan padding pada setiap kartu
            )
        }
    }
}

// Fungsi composable untuk menampilkan setiap afirmasi dalam sebuah kartu (Card)
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    // Menggunakan Card untuk mengemas tampilan afirmasi
    Card(modifier = modifier) {
        // Mengatur layout dengan Column untuk menempatkan gambar dan teks secara vertikal
        Column {
            // Menampilkan gambar dari resource ID yang terdapat di Affirmation
            Image(
                painter = painterResource(affirmation.imageResourceId), // Mengambil gambar dari resource
                contentDescription = stringResource(affirmation.stringResourceId), // Deskripsi gambar dari resource string
                modifier = Modifier
                    .fillMaxWidth() // Mengisi lebar penuh layar
                    .height(194.dp), // Tinggi gambar ditetapkan menjadi 194 dp
                contentScale = ContentScale.Crop // Gambar di-crop untuk mengisi area yang tersedia
            )
            // Menampilkan teks afirmasi
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId), // Mengambil teks dari resource string
                modifier = Modifier.padding(16.dp), // Memberikan padding pada teks
                style = MaterialTheme.typography.headlineSmall // Menggunakan gaya teks headlineSmall dari tema
            )
        }
    }
}

// Fungsi preview untuk melihat bagaimana AffirmationCard akan tampil saat dijalankan
@Preview
@Composable
private fun AffirmationCardPreview() {
    // Menampilkan preview kartu afirmasi dengan contoh data
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}
