package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp() // TODO 1: Memanggil composable utama LemonApp untuk menampilkan UI aplikasi
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // TODO 2: Menggunakan anotasi untuk API Material3 yang eksperimental
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) } // TODO 3: Menyimpan status langkah saat ini dalam proses pembuatan lemonade

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name)) // TODO 4: Menampilkan nama aplikasi di AppBar
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Gray, // TODO 5: Mengatur warna latar belakang AppBar menjadi abu-abu
                    titleContentColor = Color.Black // TODO 6: Mengatur warna teks judul AppBar menjadi hitam
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // TODO 7: Menambahkan padding agar konten tidak tertutup oleh AppBar
            color = MaterialTheme.colorScheme.background // TODO 8: Mengatur warna latar belakang Surface sesuai tema
        ) {
            when (currentStep) { // TODO 9: Menggunakan kondisi untuk menampilkan langkah yang sesuai berdasarkan currentStep
                1 -> { // TODO 10: Langkah 1 - Memilih lemon
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.lemon_select)) // TODO 11: Menampilkan teks instruksi untuk memilih lemon
                        Spacer(modifier = Modifier.height(32.dp)) // TODO 12: Menambahkan spasi vertikal
                        Image(
                            painter = painterResource(R.drawable.lemon_tree), // TODO 13: Menampilkan gambar pohon lemon
                            contentDescription = stringResource(R.string.lemon_tree_content_description), // TODO 14: Menambahkan deskripsi konten untuk aksesibilitas
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable {
                                    currentStep = 2 // TODO 15: Mengubah langkah saat ini menjadi 2 setelah gambar diklik
                                }
                        )
                    }
                }
                2 -> { // TODO 16: Langkah 2 - Memeras lemon
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.lemon_squeeze)) // TODO 17: Menampilkan teks instruksi untuk memeras lemon
                        Spacer(modifier = Modifier.height(32.dp)) // TODO 18: Menambahkan spasi vertikal
                        Image(
                            painter = painterResource(R.drawable.lemon_squeeze), // TODO 19: Menampilkan gambar proses pemerasan lemon
                            contentDescription = stringResource(R.string.lemon_content_description), // TODO 20: Menambahkan deskripsi konten untuk aksesibilitas
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable {
                                    currentStep = 3 // TODO 21: Mengubah langkah saat ini menjadi 3 setelah gambar diklik
                                }
                        )
                    }
                }
                3 -> { // TODO 22: Langkah 3 - Menambahkan air dan es
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.lemon_drink)) // TODO 23: Menampilkan teks instruksi untuk menambahkan air dan es
                        Spacer(modifier = Modifier.height(32.dp)) // TODO 24: Menambahkan spasi vertikal
                        Image(
                            painter = painterResource(R.drawable.lemon_drink), // TODO 25: Menampilkan gambar proses menambahkan air dan es
                            contentDescription = stringResource(R.string.lemon_drink_content_description), // TODO 26: Menambahkan deskripsi konten untuk aksesibilitas
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable {
                                    currentStep = 4 // TODO 27: Mengubah langkah saat ini menjadi 4 setelah gambar diklik
                                }
                        )
                    }
                }
                4 -> { // TODO 28: Langkah 4 - Memulai ulang proses
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.lemon_restart)) // TODO 29: Menampilkan teks instruksi untuk memulai ulang proses
                        Spacer(modifier = Modifier.height(32.dp)) // TODO 30: Menambahkan spasi vertikal
                        Image(
                            painter = painterResource(R.drawable.lemon_restart), // TODO 31: Menampilkan gambar tombol restart
                            contentDescription = stringResource(R.string.lemon_restart_content_description), // TODO 32: Menambahkan deskripsi konten untuk aksesibilitas
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable {
                                    currentStep = 1 // TODO 33: Mengubah langkah saat ini kembali ke 1 setelah gambar diklik
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp() // TODO 34: Preview composable LemonApp untuk melihat tampilan di editor
    }
}
