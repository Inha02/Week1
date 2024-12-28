package com.example.week1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                containerColor = Color(0xFF7D8F69),
                contentColor = Color.White,
                onClick = {showDialog =true},
                modifier = Modifier.size(60.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.acorn_plus), // 리소스 아이콘 로드
                    contentDescription = null, // 콘텐츠 설명 (null로 설정 가능)
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp).size(30.dp),
                    tint = Color.White,
                )
            }
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text("담다", fontSize = 60.sp, color = Color(0xFF634543) , modifier =  Modifier.padding(16.dp))
                Image(painter = painterResource(R.drawable.icon_damda),
                    contentDescription = "",
                    modifier = Modifier.height(100.dp))
            }


            Spacer(Modifier.padding(16.dp))

            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(viewModel.contactState.value.contactList.size){ index ->
                    val contact = viewModel.contactState.value.contactList[index]
                    Surface (
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { navController.navigate(Screen.Gallery.route +"/${contact.id}") },
                        color = Color(0xFF634543)
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.acorn1), // 리소스 아이콘 로드
                                contentDescription = null, // 콘텐츠 설명 (null로 설정 가능)
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp).size(20.dp),
                                tint = Color.White,
                            )
                            Text(text = contact.name, modifier = Modifier.padding(16.dp), color = Color.White)
                            Text(text = contact.phone, modifier = Modifier.padding(16.dp), color = Color.White)
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                Icons.Default.Delete,
                                "",
                                Modifier.padding(16.dp).clickable { viewModel.deleteContact(contact) },
                                tint = Color(0xFFEAD9D1))
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
            title = { Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.acorn1), // 리소스 아이콘 로드
                    contentDescription = null, // 콘텐츠 설명 (null로 설정 가능)
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp).size(20.dp),
                    tint = Color(0xff634543),
                )
                Text(text = "연락처 추가", fontSize = 20.sp, color = Color(0xff634543))
            }
                    },
            text = {
                // AlertDialog 내에서 필요한 입력 항목
                Column {

                    TextField(
                        value = name,
                        onValueChange = { name = it},
                        placeholder = { Text("이름", color = Color(0x99634543)) }, // Placeholder 텍스트
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xff634543),
                            unfocusedIndicatorColor = Color(0xff634543)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    TextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it},
                        placeholder = { Text("전화번호", color = Color(0x99634543)) }, // Placeholder 텍스트
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xff634543),
                            unfocusedIndicatorColor = Color(0xff634543)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
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
                    Text("추가", color = Color(0xff7d8f69))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("취소",color = Color(0xff7d8f69))
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
            Icon(
                painter = painterResource(id = R.drawable.acorn1), // 리소스 아이콘 로드
                contentDescription = null, // 콘텐츠 설명 (null로 설정 가능)
                modifier = Modifier.padding(32.dp)
            )
            Text(text = "박세준", modifier = Modifier.padding(16.dp))
            Text(text = "010-4905-8098", modifier = Modifier.padding(16.dp))
        }
    }

}