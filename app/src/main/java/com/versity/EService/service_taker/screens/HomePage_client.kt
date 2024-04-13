import android.annotation.SuppressLint
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.versity.EService.R
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePageClient(navController: NavHostController){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
Box(modifier = Modifier.fillMaxWidth()){

    ModalNavigationDrawer(
        modifier = Modifier
            .requiredWidth(400.dp).padding(horizontal = 10.dp)
            .background(Color.Transparent),
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                // ...other drawer items
            }
        }
    ) {
        // Screen content
     Scaffold(
        topBar = {


            TopAppBar(navigationIcon = {

                Icon(modifier = Modifier.clickable(onClick = {
                    scope.launch{if(drawerState.isClosed){drawerState.open()}else{drawerState.close() }}

                    }),imageVector = Icons.Default.Menu, contentDescription = "Hello") },
                title = { Text(text = "HomePage")} )}
   , content =  {

Column(  modifier = Modifier
    .fillMaxWidth().padding(16.dp)) {


        Card(    modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 30.dp)
            .height(height = 180.dp)
            .background(Color.White),
       elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )          ) {
          Row(modifier = Modifier
              .fillMaxWidth()
              .padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically) {
              Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                  Text(text = "Get 30% on repairing", fontSize = 25.sp)
                  Box(modifier = Modifier.height(10.dp))
                  Text(text = "Code: AFR34")
                  Box(modifier = Modifier.height(10.dp))


                  Button(onClick = { /*TODO*/ }, content = { Text(text = "Book Now")})


              }

              Image(painterResource(R.drawable.frame),"content description")

          }

        }
    Text(text = "Categories", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Box(modifier = Modifier.height(10.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(10) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(R.drawable.cleaning),"content description")

                Text(text = "Cleaning",)
            }
        }
    }
   }})}
}
}