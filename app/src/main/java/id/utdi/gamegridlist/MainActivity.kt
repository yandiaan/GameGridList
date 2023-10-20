// Import paket dan kelas yang diperlukan
package id.utdi.gamegridlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.utdi.gamegridlist.data.DataSource
import id.utdi.gamegridlist.model.Game
import id.utdi.gamegridlist.ui.theme.GameGridListTheme

// Kelas MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Terapkan tema ke UI secara keseluruhan
            GameGridListTheme {
                // Surface adalah wadah terluar dengan warna latar belakang dari tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Column digunakan untuk menyusun komponen secara vertikal
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Tambahkan padding ke GameGrid
                        GameGrid(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                    }
                }
            }
        }
    }
}

// Fungsi komposisi untuk menampilkan grid game yang dapat di-scroll
@Composable
fun GameGrid(modifier: Modifier = Modifier) {
    // LazyVerticalGrid digunakan untuk membuat grid vertikal dengan elemen-elemen yang dapat di-scroll
    LazyVerticalGrid(
        // Grid dengan 2 kolom tetap
        columns = GridCells.Fixed(2),
        // Jarak antar item dalam grid
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        // Tampilkan setiap game dalam grid menggunakan komposisi GameCard
        items(DataSource.games) { game ->
            GameCard(game)
        }
    }
}

// Fungsi komposisi untuk menampilkan detail game dalam kartu
@Composable
fun GameCard(game: Game) {
    // State untuk melacak apakah dialog detail harus ditampilkan
    var isDetailDialogVisible by remember { mutableStateOf(false) }

    // Komposisi Card digunakan untuk menampilkan elemen dalam bentuk kartu
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

// Fungsi pratinjau untuk UI yang dapat dikomposisikan
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Pratinjau dengan tema yang diterapkan
    GameGridListTheme {
        // Column digunakan untuk menyusun komponen secara vertikal dalam pratinjau
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tambahkan padding ke GameGrid dalam pratinjau
            GameGrid(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        }
    }
}
