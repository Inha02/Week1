package com.example.week1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ContactView(navController: NavHostController) {

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.Cyan,
                contentColor = Color.White,
                onClick = {showDialog =true}
            ) {

                Icon(Icons.Default.Add, "")
            }
        }
    ) { innerpadding ->
        Column (modifier = Modifier.padding(innerpadding)){

            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(10){
                    Surface (
                        modifier = Modifier.padding(bottom = 8.dp),
                        shadowElevation = 4.dp
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        ) {

                        }
                    }
                }
            }
        }
    }

    AddContactAlertDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onAddContact = { name, phoneNumber ->
            // 연락처를 저장하는 로직 or ViewModel 호출 등 처리
            // 예: viewModel.addContact(name, phoneNumber)

            // 처리 후 다이얼로그 닫기
            showDialog = false
        }
    )

}


@Composable
fun AddContactAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddContact: (String, String) -> Unit
) {
    if (showDialog) {
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        AlertDialog(

            onDismissRequest = { onDismiss() },
            title = { Text(text = "연락처 추가") },
            text = {
                // AlertDialog 내에서 필요한 입력 항목


                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("이름") }
                    )
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("전화번호") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // 여기서 입력된 값 전달
                        onAddContact(name, phoneNumber)
                    }
                ) {
                    Text("추가")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("취소")
                }
            }
        )
    }
}
