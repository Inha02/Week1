 # 담다(Damda)
담다는 누군가와의 추억을 연락처와 연동해 담을 수 있는 앱입니다.

## 팀원 
박세준 최인하

## 테크 스택
언어: Kotlin(JetpackCompose) / 개발환경: Android Studio

 ## 스플래쉬 화면 및 아이콘
 스플래쉬 앱을 실행하면 뜨는 스플래쉬 화면에는 담다의 아이콘인 다람쥐가 나타납니다
<p align = "center">
    <img width = 300 src="Screens/splashScreen.jpg">
</p>

 ## 첫 번째 화면
<p align="center">
  <img width = 300 src="Screens/firstScreen.jpg">
  <img width = 300 src="Screens/firstScreenAddContact.jpg">
</p>

첫번째 탭에는 앱의 이름 '담다'와 아이콘인 다람쥐가 상단에 표시됩니다.   
연락처와 이름, 연락처 삭제 버튼을 `lazycolumn`을 통해 구현했습니다.  
우하단의 도토리와 플러스 아이콘으로 표현한 `floatingActionButton`을 통해 연락처를 추가할 수 있습니다.  
`allertDialog` 를 통해 연락처를 추가할 수 있도록 했습니다.  
연락처는 안드로이드의 `room DB` 에 저장됩니다.

 ## 두 번째 화면
 <p align="center">
  <img width = 300 src="Screens/secondScreen.jpg">
  <img width = 300 src="Screens/AddDiaryScreen.jpg">
</p>

`navHostController` 를 통해 탭 간의 전환이 가능하도록 했습니다.  
두번째 탭에서는 연락처에 해당하는 갤러리가 연결됩니다. 
우하단의 도토리 아이콘을 통해 갤러리에 사진과 함께 글을 통해 추억을 기록할 수 있습니다. 
사진을 직접 촬영하거나 휴대폰 갤러리에서 불러와 저장할 수 있습니다. 
글과 사진은 `room DB`에 저장됩니다.

## 세 번째 화면
 <p align="center">
  <img width = 300 src="Screens/thirdScreen.jpg">
  <img width = 300 src="Screens/EditDiaryScreen.jpg">
</p>


갤러리의 사진과 함께 기록한 글을 확인할 수 있습니다. 
쓰레기통 버튼을 통해 갤러리의 사진을 삭제하거나, 수정 버튼을 통해 글과 사진을 수정할 수 있습니다.

