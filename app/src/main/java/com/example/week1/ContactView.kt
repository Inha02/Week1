package com.example.week1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.week1.data.Contact

@Composable
fun ContactView(navController: NavHostController, viewModel: ContactViewModel) {

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
                items(viewModel.contactState.value.contactList.size){ index ->
                    val contact = viewModel.contactState.value.contactList[index]
                    Surface (
                        modifier = Modifier.padding(bottom = 8.dp),
                        shadowElevation = 4.dp
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(Icons.Default.Phone, "", Modifier.padding(32.dp))
                            Text(text = contact.name, modifier = Modifier.padding(16.dp))
                            Text(text = contact.phone, modifier = Modifier.padding(16.dp))
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
            // Room 데이터베이스에 연락처 저장
            viewModel.addContact(Contact(name = name, phone = phoneNumber, images = emptyList()))

            // 다이얼로그 닫기
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

@Preview
@Composable
fun ContactPreview() {

    Surface (
        modifier = Modifier.padding(bottom = 8.dp),
        shadowElevation = 4.dp
    ){
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Default.Phone, "", Modifier.padding(32.dp))
            Text(text = "박세준", modifier = Modifier.padding(16.dp))
            Text(text = "010-4905-8098", modifier = Modifier.padding(16.dp))
        }
    }

}