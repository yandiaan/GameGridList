package id.utdi.gamegridlist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.utdi.gamegridlist.Destination
import id.utdi.gamegridlist.R
import id.utdi.gamegridlist.ui.theme.GameGridListTheme

/**
 * [ProfileScreen] adalah layar yang menampilkan profil pengembang.
 *
 * @param navController Navigasi controller untuk mengelola navigasi antar layar.
 */
@Composable
fun ProfileScreen(navController: NavController) {
    // Terapkan tema ke UI
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // LazyColumn untuk menampilkan konten secara berurutan
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            item {
                // Tombol kembali ke layar utama
                Button(
                    onClick = { navController.navigate(Destination.Home.route) }
                ) {
                    Text(text = "Back to Home")
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }

            item {
                // Tampilkan foto profil
                Image(
                    painter = painterResource(id = R.drawable.dian),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }

            item {
                // Tampilkan nama pengembang
                Text(
                    text = "Dian Setiawan",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            // Tampilkan informasi lainnya tentang pengembang
            item {
                ProfileItem(title = "Role", value = "Developer")
                ProfileItem(title = "Location", value = "Yogyakarta, Indonesia")
                ProfileItem(title = "Email", value = "diansetiawan2121@gmail.com")
                ProfileItem(title = "GitHub", value = "github.com/yandiaan")
            }

            // Tambahkan lebih banyak item sesuai kebutuhan

        }
    }
}

/**
 * [ProfileItem] adalah komponen untuk menampilkan item profil dengan judul dan nilai.
 *
 * @param title Judul item profil.
 * @param value Nilai item profil.
 */
@Composable
fun ProfileItem(title: String, value: String) {
    // Column untuk menyusun komponen secara vertikal
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Tampilkan judul item dengan gaya teks tertentu
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp) // Terapkan padding di sini
        )
        // Tampilkan nilai item dengan gaya teks tertentu
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
