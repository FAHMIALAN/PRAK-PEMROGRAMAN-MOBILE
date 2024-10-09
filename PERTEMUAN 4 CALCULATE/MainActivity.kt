package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // TODO 1: Mengaktifkan mode edge-to-edge pada aplikasi.
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout() // TODO 2: Memanggil fungsi `TipTimeLayout` sebagai konten utama.
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") } // TODO 3: State untuk menyimpan input jumlah tagihan.
    var tipInput by remember { mutableStateOf("") } // TODO 4: State untuk menyimpan input persentase tip.
    var roundUp by remember { mutableStateOf(false) } // TODO 5: State untuk menyimpan apakah tip dibulatkan atau tidak.

    val amount = amountInput.toDoubleOrNull() ?: 0.0 // TODO 6: Mengonversi input jumlah ke Double, default 0 jika null.
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0 // TODO 7: Mengonversi input tip ke Double, default 0 jika null.
    val tip = calculateTip(amount, tipPercent, roundUp) // TODO 8: Menghitung tip berdasarkan input jumlah, tip, dan pembulatan.

    Column(
        modifier = Modifier
            .statusBarsPadding() // TODO 9: Menambahkan padding untuk status bar.
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()) // TODO 10: Menambahkan fitur scroll vertikal jika konten terlalu panjang.
            .safeDrawingPadding(), // TODO 11: Menambahkan padding untuk area aman perangkat.
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip), // TODO 12: Menampilkan teks "Calculate Tip" dari resource.
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            label = R.string.bill_amount, // TODO 13: Label untuk input jumlah tagihan.
            leadingIcon = R.drawable.money, // TODO 14: Ikon uang untuk bidang input tagihan.
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // TODO 15: Mengatur jenis keyboard ke numerik.
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChanged = { amountInput = it }, // TODO 16: Mengubah state `amountInput` sesuai input pengguna.
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
        )
        EditNumberField(
            label = R.string.how_was_the_service, // TODO 17: Label untuk input persentase tip.
            leadingIcon = R.drawable.percent, // TODO 18: Ikon persentase untuk bidang input persentase tip.
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // TODO 19: Mengatur keyboard ke numerik untuk input persentase.
                imeAction = ImeAction.Done
            ),
            value = tipInput,
            onValueChanged = { tipInput = it }, // TODO 20: Mengubah state `tipInput` sesuai input pengguna.
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
            iconSize = 32 // TODO 21: Mengatur ukuran ikon khusus untuk bidang input ini.
        )
        RoundTheTipRow(
            roundUp = roundUp, // TODO 22: Menyimpan status pembulatan tip.
            onRoundUpChanged = { roundUp = it }, // TODO 23: Mengubah status pembulatan saat pengguna mengaktifkan/menonaktifkan sakelar.
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = stringResource(R.string.tip_amount, tip), // TODO 24: Menampilkan jumlah tip yang dihitung dalam format mata uang.
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp)) // TODO 25: Spacer untuk memberikan jarak tambahan di bagian bawah layar.
    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int, // TODO 26: Parameter untuk label dari resource string.
    @DrawableRes leadingIcon: Int, // TODO 27: Parameter untuk ikon dari resource drawable.
    keyboardOptions: KeyboardOptions, // TODO 28: Mengatur opsi keyboard seperti tipe dan tindakan IME.
    value: String, // TODO 29: Menyimpan nilai input dari pengguna.
    onValueChanged: (String) -> Unit, // TODO 30: Callback untuk memperbarui nilai input saat pengguna mengetik.
    modifier: Modifier = Modifier,
    iconSize: Int = 24 // TODO 31: Ukuran default ikon, dapat diubah jika diperlukan.
) {
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null, 
                modifier = Modifier.size(25.dp) // TODO 32: Menggunakan ukuran ikon sesuai parameter `iconSize`.
            )
        },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) }, // TODO 33: Menampilkan label dari resource string.
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean, // TODO 34: Status apakah tip harus dibulatkan atau tidak.
    onRoundUpChanged: (Boolean) -> Unit, // TODO 35: Callback saat sakelar diubah.
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip)) // TODO 36: Menampilkan teks "Round up tip" dari resource.
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End), // TODO 37: Mengatur sakelar berada di ujung kanan.
            checked = roundUp, // TODO 38: Menyimpan status sakelar (aktif/nonaktif).
            onCheckedChange = onRoundUpChanged // TODO 39: Memperbarui status sakelar saat pengguna mengubahnya.
        )
    }
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount // TODO 40: Menghitung jumlah tip berdasarkan persentase.
    if (roundUp) {
        tip = kotlin.math.ceil(tip) // TODO 41: Membulatkan tip jika opsi pembulatan diaktifkan.
    }
    return NumberFormat.getCurrencyInstance().format(tip) // TODO 42: Mengembalikan hasil tip dalam format mata uang.
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout() // TODO 43: Pratinjau tampilan layout aplikasi dalam mode preview.
    }
}
