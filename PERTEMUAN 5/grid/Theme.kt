package com.example.grid.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Skema warna untuk mode gelap (dark theme)
private val DarkColorScheme = darkColorScheme(
    primary = primary_dark, // Warna utama dalam mode gelap
    secondary = secondary_dark, // Warna sekunder dalam mode gelap
    tertiary = tertiary_dark // Warna tersier dalam mode gelap
)

// Skema warna untuk mode terang (light theme)
private val LightColorScheme = lightColorScheme(
    primary = primary_light, // Warna utama dalam mode terang
    secondary = secondary_light, // Warna sekunder dalam mode terang
    tertiary = tertiary_light // Warna tersier dalam mode terang
)

// Fungsi composable untuk menerapkan tema dengan warna dinamis atau berdasarkan mode gelap/terang
@Composable
fun CoursesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Defaultnya mengikuti pengaturan sistem apakah mode gelap aktif
    dynamicColor: Boolean = false, // Menentukan apakah akan menggunakan warna dinamis atau tidak
    content: @Composable () -> Unit // Konten yang akan dirender dalam tema ini
) {
    // Menentukan skema warna berdasarkan pengaturan dinamis dan mode gelap/terang
    val colorScheme = when {
        // Jika dynamicColor aktif dan versi Android mendukung (Android 12 atau lebih baru)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) // Warna dinamis untuk mode gelap
            else dynamicLightColorScheme(context) // Warna dinamis untuk mode terang
        }
        // Jika mode gelap diaktifkan
        darkTheme -> DarkColorScheme
        // Jika mode terang digunakan
        else -> LightColorScheme
    }

    // Mengambil view saat ini untuk pengaturan warna status bar
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            // Mengatur warna status bar agar mengikuti warna utama skema warna
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            // Mengatur apakah ikon status bar akan mengikuti mode terang atau gelap
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // Menerapkan MaterialTheme dengan skema warna dan tipografi yang telah ditentukan
    MaterialTheme(
        colorScheme = colorScheme, // Menggunakan skema warna yang telah ditentukan di atas
        typography = Typography, // Menggunakan tipografi yang sudah disediakan (pastikan ini merujuk ke definisi yang benar)
        content = content // Konten yang akan ditampilkan dalam tema ini
    )
}
