package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid.data.DataSource
import com.example.grid.model.Topic
import com.example.grid.theme.CoursesTheme

// MainActivity adalah activity utama yang menjalankan aplikasi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Mengaktifkan mode tampilan edge-to-edge
        super.onCreate(savedInstanceState)
        setContent {
            // Mengatur tema aplikasi menggunakan CoursesTheme
            CoursesTheme {
                // Surface sebagai kontainer utama dengan warna background dari tema
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi seluruh ukuran layar
                        .statusBarsPadding(), // Menambahkan padding di bagian status bar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna background dari tema
                ) {
                    // Memanggil fungsi composable TopicGrid untuk menampilkan grid topik
                    TopicGrid(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small), // Padding kiri
                            top = dimensionResource(R.dimen.padding_small), // Padding atas
                            end = dimensionResource(R.dimen.padding_small) // Padding kanan
                        )
                    )
                }
            }
        }
    }
}

// Fungsi composable untuk menampilkan grid topik dengan dua kolom
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // LazyVerticalGrid menampilkan item dalam bentuk grid yang bisa di-scroll
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Grid dengan 2 kolom
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak antar item secara vertikal
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak antar item secara horizontal
        modifier = modifier
    ) {
        // Mengiterasi data topik dari DataSource dan menampilkan setiap topik dalam bentuk kartu (Card)
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

// Fungsi composable untuk menampilkan kartu topik
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    // Menampilkan Card untuk membungkus tampilan topik
    Card(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)) // Memberikan padding pada Card
    ) {
        // Row menampilkan gambar dan teks secara horizontal (satu baris)
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)) // Padding di dalam Row
        ) {
            // Menampilkan gambar topik
            Image(
                painter = painterResource(id = topic.imageRes), // Mengambil gambar dari resource
                contentDescription = null, // Gambar tidak membutuhkan deskripsi konten
                modifier = Modifier
                    .size(68.dp) // Ukuran gambar 68 dp
                    .aspectRatio(1f), // Rasio aspek gambar 1:1
                contentScale = ContentScale.Crop // Gambar di-crop untuk mengisi area
            )
            // Kolom untuk menampilkan teks secara vertikal
            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)) // Padding kiri pada Kolom
            ) {
                // Menampilkan nama topik
                Text(
                    text = stringResource(id = topic.name), // Mengambil nama topik dari resource string
                    style = MaterialTheme.typography.bodyMedium, // Menggunakan gaya teks bodyMedium dari tema
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)) // Padding bawah pada teks
                )
                // Baris untuk menampilkan ikon dan jumlah kursus
                Row(
                    verticalAlignment = Alignment.CenterVertically // Ikon dan teks disejajarkan secara vertikal
                ) {
                    // Menampilkan ikon info
                    Icon(
                        painter = painterResource(android.R.drawable.ic_menu_info_details), // Ikon default dari Android
                        contentDescription = null, // Ikon tidak membutuhkan deskripsi konten
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_small)) // Padding kanan pada ikon
                    )
                    // Menampilkan jumlah kursus yang tersedia untuk topik ini
                    Text(
                        text = topic.availableCourses.toString(), // Menampilkan jumlah kursus
                        style = MaterialTheme.typography.labelMedium // Menggunakan gaya teks labelMedium dari tema
                    )
                }
            }
        }
    }
}

// Fungsi preview untuk menampilkan tampilan kartu topik saat dikembangkan
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    // Menggunakan tema CoursesTheme
    CoursesTheme {
        // Membuat contoh topik untuk preview
        val topic = Topic(R.string.architecture, 58, android.R.drawable.ic_menu_camera)
        // Menampilkan kartu topik dalam preview
        TopicCard(topic = topic)
    }
}
