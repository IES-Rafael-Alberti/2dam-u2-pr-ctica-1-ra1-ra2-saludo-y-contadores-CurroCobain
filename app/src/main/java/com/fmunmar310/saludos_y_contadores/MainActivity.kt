package com.fmunmar310.saludos_y_contadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fmunmar310.saludos_y_contadores.ui.theme.Saludos_y_ContadoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Saludos_y_ContadoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SaludoYcontadores()
                }
            }
        }
    }
}

@Preview
@Composable
fun SaludoYcontadores(){
    var name by rememberSaveable { mutableStateOf("") }
    var accept by rememberSaveable { mutableStateOf(0) }
    var cancel by rememberSaveable { mutableStateOf(0) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    if (showDialog){
        MyAlertDialog(
            onDismissRequest = {showDialog = false} ,
            onConfirmation = {accept++}, accValue = {accept.toString()},
            onCancel = {cancel++}, canValue = {cancel.toString()},
            onTextChange = {it : String -> name = it}, nameValue = {name}
            )

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(onClick = { showDialog = true
         }) {
            Text(text = "Saludar",
                fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Text(text = """
            Saludos $name
        """.trimIndent(),
            modifier = Modifier.wrapContentSize(),
            fontSize = 20.sp)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    accValue: () -> String,
    onCancel: () -> Unit,
    canValue: () -> String,
    onTextChange: (String) -> Unit,
    nameValue : () ->String

) {

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(dismissOnBackPress = true), {
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .background(color = Color.White)
                .border(width = 1.dp, color = Color.Gray)
                ){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(){
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Configuración",
                            modifier = Modifier.padding(bottom = 15.dp),
                            fontStyle = FontStyle.Italic,
                            fontSize = 25.sp
                        )
                    }
                    Row(){
                        OutlinedTextField(
                            value = nameValue(),
                            onValueChange = onTextChange,
                            label = {Text("Ingresa tu nombre")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp)
                        )
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(onClick = {
                            onConfirmation()
                            onDismissRequest()})
                        {
                            Text("Conf ${accValue()}")}

                        Button(onClick = {
                            onCancel()
                            onDismissRequest()
                            onTextChange("")})
                        {
                            Text("Canc ${canValue()}")}

                        Button(onClick = {
                            onTextChange("")
                        })
                        {
                            Text("Limp")}
                    }
                }
            }
        }
    )
}
