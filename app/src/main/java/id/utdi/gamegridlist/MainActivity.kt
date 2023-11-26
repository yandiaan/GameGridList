// Import paket dan kelas yang diperlukan
package id.utdi.gamegridlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.utdi.gamegridlist.screen.HomeScreen
import id.utdi.gamegridlist.screen.ListScreen
import id.utdi.gamegridlist.screen.ProfileScreen
import id.utdi.gamegridlist.ui.theme.GameGridListTheme

// class sealed untuk mendefinisi masing-masing route yang ada di aplikasi ini
sealed class Destination(val route: String) {
    object Home: Destination("home")
    object List: Destination("list")
    object Profile: Destination("profile")
}

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
                    // Inisialisasi NavHostController untuk mengelola navigasi
                    val navController = rememberNavController()
                    // Panggil fungsi untuk menampilkan aplikasi berbasis navigasi
                    NavigationAppHost(navController = navController)
                }
            }
        }
    }
}

// Fungsi komposisi untuk menangani navigasi berbasis NavHost
@Composable
fun NavigationAppHost(navController: NavHostController){
    // NavHost adalah tempat menentukan routing antar layar dalam aplikasi
    NavHost(navController = navController, startDestination = Destination.Home.route){
        // Setiap composable berisi tampilan untuk satu destinasi atau layar
        composable(Destination.Home.route){ HomeScreen(navController) }
        composable(Destination.List.route){ ListScreen(navController) }
        composable(Destination.Profile.route){ ProfileScreen(navController) }
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
            // Inisialisasi NavHostController untuk pratinjau navigasi
            val navController = rememberNavController()
            // Panggil fungsi untuk menampilkan aplikasi berbasis navigasi dalam pratinjau
            NavigationAppHost(navController = navController)
        }
    }
}
