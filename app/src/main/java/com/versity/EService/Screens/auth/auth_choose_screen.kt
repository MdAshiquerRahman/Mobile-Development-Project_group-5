
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import com.versity.EService.R
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ChipBorder

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AuthChooseScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image in the center
        Image(
            painter = painterResource(id = R.drawable.onboard4),
            contentDescription = null,
            modifier = Modifier.size(200.dp), // Adjust the size as needed
            contentScale = ContentScale.Fit
        )

        // Text below the image
        Text(
            text = "Get your work done best with us",          color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )
        Button(
            onClick = {
                navController.navigate("signUpScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Blue),
            modifier = Modifier
                .fillMaxWidth()

                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp),

            ) {
            Text(
                text = "Sign Up",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }

          Button(
              onClick = {
                  navController.navigate("loginScreen")
              },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color.Blue,
                    contentColor = androidx.compose.ui.graphics.Color.White
                ),
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()

                    .height(56.dp)
                    .padding(horizontal = 16.dp, vertical = 10.dp),

                ) {
                androidx.compose.material3.Text(
                    text = "Sign In",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = androidx.compose.ui.Modifier.fillMaxSize()
                )

        }
        // Two buttons vertically aligned
     

        
    }
}


@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        )
       ,
        modifier = Modifier
            .fillMaxWidth()

            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp),

        ) {
        Text(
            text = "Sign In",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
}