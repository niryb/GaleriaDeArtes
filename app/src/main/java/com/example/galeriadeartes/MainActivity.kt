package com.example.galeriadeartes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriadeartes.ui.theme.GaleriaDeArtesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaDeArtesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen()
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen() {
    val artworksList = listOf(
        ArtPiece(
            title = stringResource(R.string.titulo_quadro),
            description = stringResource(R.string.titulo_descricao),
            imageRes = R.drawable.arte
        ),
        ArtPiece(
            title = stringResource(R.string.titulo_quadro2),
            description = stringResource(R.string.titulo_descricao2),
            imageRes = R.drawable.galeria
        )
    )

    val selectedArtworkIndex = remember { mutableStateOf(0) }
    val selectedArtwork = artworksList[selectedArtworkIndex.value]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color(0xFFEFB8C8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = selectedArtwork.imageRes),
            contentDescription = selectedArtwork.title,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .padding(32.dp)
                .border(
                    width = 4.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color.White)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = selectedArtwork.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9A6FF0),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = selectedArtwork.description,
                fontSize = 18.sp,
                color = Color(0xFF625b71),
                modifier = Modifier.padding(4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    selectedArtworkIndex.value =
                        if (selectedArtworkIndex.value == 0) artworksList.size - 1
                        else selectedArtworkIndex.value - 1
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD0BCFF),
                    contentColor = Color.White
                )
            ) {
                Text("Anterior")
            }
            Button(
                onClick = {
                    selectedArtworkIndex.value =
                        if (selectedArtworkIndex.value == artworksList.size - 1) 0
                        else selectedArtworkIndex.value + 1
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD0BCFF),
                    contentColor = Color.White
                )
            ) {
                Text("Próximo")
            }
        }
    }
}

data class ArtPiece(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    GaleriaDeArtesTheme {
        ArtGalleryScreen()
    }
}
