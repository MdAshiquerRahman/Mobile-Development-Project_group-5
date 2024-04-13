import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.versity.EService.R

@Composable
fun LoginScreen(navController:NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    Surface(color = Color.LightGray) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.blue_logo),
                contentDescription = "logo"
            )
            Text(
                text = "Log in your account",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                visualTransformation = PasswordVisualTransformation()
            )

          Row (Modifier.fillMaxWidth()){

              Box(
                  modifier = Modifier
                      .weight(1f)
                      .height(55.dp),

                  contentAlignment = Alignment.BottomStart
              ) {


                  ClickableText(
                      onClick = {
                          showDialog.value = !showDialog.value;
                      },
                      text = AnnotatedString("Forgot password"),
                      style = TextStyle(
                          fontSize = 15.sp,
                          color = Color.Blue,
                          textAlign = TextAlign.Left,
                      ),
                      modifier = Modifier.padding(bottom = 32.dp)
                  )
              }

              Box(modifier = Modifier
                  .weight(1f)
                  .height(55.dp),

                  contentAlignment = Alignment.CenterEnd
              ) {


                  ClickableText(onClick = {
                      navController.navigate("signUpScreen")

                  },
                      text = AnnotatedString("Sign Up"),
                      style = TextStyle(fontSize =  15.sp, color = Color.Blue, textAlign = TextAlign.Left,),
                      modifier = Modifier.padding(bottom = 32.dp)
                  )}
          }
            if(showDialog.value) AlertDialogExample(onDissmiss = {showDialog.value=false;},dialogText = "send otp to email", oncOk = {navController.navigate("otpInputScreen")}, dialogTitle = "reset password")
            Button(
                onClick = {
                    navController.navigate("homePageScreenClient")
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
        }
    }
}

@Composable
fun AlertDialogExample(

onDissmiss:()->Unit,oncOk:()->Unit,
    dialogTitle: String,
    dialogText: String,

) {
    var text by remember { mutableStateOf("") }

    AlertDialog(

        title = {
           Column {

            Text(text = dialogTitle)
               TextField(
                   value = text,
                   onValueChange = { text = it },
                   label = {
                       Text(text = "Email here")
                   },
                   modifier = Modifier
                       .padding(top = 16.dp)
                       .sizeIn(minHeight = 120.dp)
                       .background(Color.Transparent),
                   keyboardOptions = KeyboardOptions.Default.copy(
                       imeAction = ImeAction.Done
                   ),
                   keyboardActions = KeyboardActions(
                       onDone = {
                           //Do something here
                       },
                   ),
               )
        }},
        text = {
            Text(text = dialogText)
        },
        properties = DialogProperties(),
        onDismissRequest = onDissmiss,
        confirmButton = {
            TextButton(
                onClick = oncOk
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDissmiss
            ) {
                Text("Dismiss")
            }
        }
    )
}

