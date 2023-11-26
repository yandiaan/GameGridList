package id.utdi.gamegridlist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.utdi.gamegridlist.Destination
import id.utdi.gamegridlist.R
import id.utdi.gamegridlist.data.DataSource
import id.utdi.gamegridlist.model.Game
import id.utdi.gamegridlist.ui.theme.GameGridListTheme

/**
 * [ListScreen] adalah layar yang menampilkan daftar game dalam bentuk grid.
 *
 * @param navController Navigasi controller untuk mengelola navigasi antar layar.
 */
@Composable
fun ListScreen(navController: NavController) {
    // Terapkan tema ke UI
    GameGridListTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tombol kembali ke layar utama
            Button(
                onClick = { navController.navigate(Destination.Home.route) }
            ) {
                Text(text = "Back to Home")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            // LazyVerticalGrid untuk menampilkan grid vertikal game
            LazyVerticalGrid(
                // Grid dengan 2 kolom tetap
                columns = GridCells.Fixed(2),
                // Jarak antar item dalam grid
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            ) {
                // Tampilkan setiap game dalam grid menggunakan komposisi GameCard
                items(DataSource.games) { game ->
                    GameCard(game)
                }
            }
        }
    }
}

/**
 * [GameCard] adalah komponen yang menampilkan detail game dalam bentuk kartu.
 *
 * @param game Objek Game yang berisi informasi game.
 */
@Composable
fun GameCard(game: Game) {
    // State untuk melacak apakah dialog detail harus ditampilkan
    var isDetailDialogVisible by remember { mutableStateOf(false) }

    // Komposisi Card untuk menampilkan elemen dalam bentuk kartu
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Column digunakan untuk menyusun komponen secara vertikal dalam kartu
        Column {
            // Tampilkan gambar game dengan efek corner radius
            Image(
                painter = painterResource(id = game.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            // Tambahkan spasi antara gambar dan detail game
            Spacer(modifier = Modifier.height(8.dp))
            // Column untuk detail game
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                // Tampilkan judul game dengan gaya teks tertentu
                Text(
                    text = stringResource(id = game.title),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        // Tambahkan bayangan ke teks
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
                // Tambahkan spasi antara judul dan genre
                Spacer(modifier = Modifier.height(8.dp))
                // Tampilkan genre game dengan gaya teks tertentu
                Text(
                    text = stringResource(id = game.genre),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        // Tambahkan bayangan ke teks
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )

                // Tambahkan tombol detail untuk menampilkan dialog
                Button(
                    onClick = { isDetailDialogVisible = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Details")
                }

                // Tampilkan dialog detail jika tombol detail ditekan
                if (isDetailDialogVisible) {
                    AlertDialog(
                        onDismissRequest = { isDetailDialogVisible = false },
                        title = { Text(text = "Game Details") },
                        text = {
                            Column {
                                Image(
                                    painter = painterResource(id = game.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clip(shape = RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Title: ${stringResource(id = game.title)}")
                                Text(text = "Genre: ${stringResource(id = game.genre)}")
                            }
                        },
                        confirmButton = {
                            Button(onClick = { isDetailDialogVisible = false }) {
                                Text(text = "OK")
                            }
                        }
                    )
                }
            }
        }
    }
}
