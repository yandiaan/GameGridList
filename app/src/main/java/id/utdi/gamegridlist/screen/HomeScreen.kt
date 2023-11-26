package id.utdi.gamegridlist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.utdi.gamegridlist.Destination
import id.utdi.gamegridlist.R
import id.utdi.gamegridlist.ui.theme.GameGridListTheme

/**
 * [HomeScreen] adalah layar utama aplikasi.
 * Ini menampilkan pesan selamat datang dan dua tombol:
 * satu untuk melihat daftar game dan satu untuk melihat profil pengembang.
 *
 * @param navController Navigasi controller untuk mengelola navigasi antar layar.
 */
@Composable
fun HomeScreen(navController: NavController) {
    // Terapkan tema ke UI
    GameGridListTheme {
        // Surface adalah wadah terluar dengan warna latar belakang dari tema
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Column digunakan untuk menyusun komponen secara vertikal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tampilkan pesan selamat datang
                Text(
                    text = "Welcome to GameGridList!",
                    style = MaterialTheme.typography.bodySmall
                )
                // Tambahkan spasi
                Spacer(modifier = Modifier.padding(16.dp))
                // Tombol untuk melihat daftar game
                Button(
                    onClick = {
                        // Navigate to the ListScreen when the button is clicked
                        navController.navigate(Destination.List.route)
                    }
                ) {
                    Text(text = "View Game List")
                }
                // Tombol untuk melihat profil pengembang
                Button(
                    onClick = {
                        // Navigate to the ProfileScreen when the button is clicked
                        navController.navigate(Destination.Profile.route)
                    }
                ) {
                    Text(text = "View Developer Profile")
                }
            }
        }
    }
}
