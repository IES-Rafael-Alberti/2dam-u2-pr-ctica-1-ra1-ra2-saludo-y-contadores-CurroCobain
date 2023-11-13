package com.fmunmar310.saludos_y_contadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
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
fun PreviewSaludoYcontadores(){
    SaludoYcontadores()
}
@OptIn(ExperimentalMaterial3Api:: class)
@Composable
fun SaludoYcontadores(){
    var name by rememberSaveable { mutableStateOf("") }
    var accept by rememberSaveable { mutableStateOf(0) }
    var cancel by rememberSaveable { mutableStateOf(0) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    if (showDialog){
        MyAlertDialog(onDismissRequest = {showDialog = false} ,
            onConfirmation = {accept++},
            onCancel = {cancel++},
            configuration ={name})

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(onClick = { showDialog = true
         }) {
            Text(text = "Saludar")
        }
        Text(text = "Saludos $name /$accept /$cancel ",
            modifier = Modifier.wrapContentSize())
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onCancel: () -> Unit,
    configuration: () -> Unit,

) {
    var text by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                    onCancel()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}
