
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.versity.EService.R
import org.w3c.dom.Text as Text1


private const val PREFS_NAME = "MyPrefs"
private const val PREF_ONBOARDING_COMPLETED = "onboardingCompleted"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun OnboardingScreen(navController: NavHostController) {
   var clickCount by remember { mutableStateOf(0) }
    val titles = listOf(
        "Please post Your service here and get more clients",
        "Easy Service booking & Scheduling",
        "Explore your Best service"
    )

    val images = listOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3
    )




    var buttonTexts = "NEXT"
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = images[clickCount]),
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = titles[clickCount],
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(25.dp))
            ThreeDotsUI(selectedScreenIndex = clickCount)
            Spacer(modifier = Modifier.width(16.dp))
            ButtonWithGradientBackground(
                onClick = {
                    clickCount++

                    if (clickCount < 2) {
                          buttonTexts="NEXT"

                }else if (clickCount== 2){

//                    selectedTabIndex = (selectedTabIndex + 1)

                        buttonTexts="GET START"

                     }else{clickCount=0
                        navController.navigate("chooseAuth")
                     } },
                text = buttonTexts
            )
        }
    }
}

@Composable
fun ButtonWithGradientBackground(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF62CFF4), Color(0xFF2C67F2)),
        startX = 0f,
        endX = 100f
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(brush = gradientBrush, shape = shape)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ThreeDotsUI(selectedScreenIndex: Int) {
    Row(modifier = Modifier.padding(8.dp)) {
        for (i in 0 until 3) {
            Dot(selected = i == selectedScreenIndex)
        }
    }
}

@Composable
fun Dot(selected: Boolean) {
    val dotColor = if (selected) Color.Blue else Color.Gray
    val dotSize = 20.dp

    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(color = dotColor, shape = CircleShape)
            .size(dotSize)
    )

}
