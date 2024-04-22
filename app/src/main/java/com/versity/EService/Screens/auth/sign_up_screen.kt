import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import kotlinx.coroutines.tasks.await
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
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
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.options
import com.google.firebase.storage.FirebaseStorage
import com.versity.EService.R
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun SignUpScreen(navController: NavHostController) {
    var auth: FirebaseAuth
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    suspend  fun updateProfile(uid: String, name: String, booleanValue: Boolean) {
        auth = Firebase.auth
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()
        user?.updateProfile(profileUpdates)?.await()
        // Store boolean value in Firestore or Realtime Database
        // For example, in Firestore
        val firestore = FirebaseFirestore.getInstance()

        firestore
            .collection("users")
            .document(uid)
            .set(
                mapOf(
                    "name" to name,
                    "booleanValue" to booleanValue
                )
            )
            .await()
    }

// Function to create user with email and password
    auth = Firebase.auth
     suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String,
        booleanValue: Boolean
    ): String {
        val authResult = auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    scope.launch {
                        snackbarHostState.showSnackbar("Sign Up Successfull")
                    }
                    navController.navigate("loginScreen")

                } else {
                    // If sign in fails, display a message to the user.
                    scope.launch {
                        snackbarHostState.showSnackbar("Sign Up Successfull")

                    }
                }
            }.await()
        val user = authResult.user
        // Update user profile with additional information
        if (user != null) {
            updateProfile(user.uid, name, booleanValue)
        }
        if (user != null) {
            return user.uid
        }


        return  ""; // Function to update user profile

    }
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }



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
                text = "Sign Up",
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
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
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
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == 0,
                    onClick = { selectedOption = 0 }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Service Provider")

                Spacer(modifier = Modifier.width(5.dp))

                RadioButton(
                    selected = selectedOption == 1,
                    onClick = { selectedOption = 1 }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Service Taker")
            }



            Button(
                onClick = {
                  scope.launch { 
                        createUserWithEmailAndPassword("hrido@gmail.com", password, username, true)
                    } },
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                contentAlignment = Alignment.BottomStart
            ) {
                ClickableText(
                    onClick = {
                        navController.navigate("loginScreen")

                    },
                    text = AnnotatedString("Log In"),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Blue,
                        textAlign = TextAlign.Left,
                    ),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }}
    }

    // SharedCode module



