# ToDoong

Jica Android 백앤드 개발자 과정의 Todoong팀 프로젝트 입니다.<br><br>
리더: Lee Jinyoung<br>
팀원: Kang mingu, Juong ahyun<br>

<실행화면><br>
<img src="https://i.imgur.com/NX1SBoc.png">
<img src="https://i.imgur.com/eXF0NTP.png"><br>

<프로젝트 내용>

- 목표: 사용자가 즐겁게 일정을 소화할 수 있도록 캐릭터와 연동 된 todoList를 만든다.

<br><기술>
- DataBase는 SQLite를 사용하여 데이터의 추가/삭제/수정 기능을 이용할 수 있도록 짠다.
  <br>SQLite를 이용한 이유: 가볍게 사용하는 체크리스트 어플인 만큼 서버에 저장을 하여 영구적으로 남기는 방법보단
  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;핸드폰 내에서 저장되게 하는게 효율적이라 생각되어 채택하게 되었다.
                       
- RecyclerView를 이용하여 효과적으로 투두리스트 항목을 관리하도록 한다.
- BottomSheet를 이용하여 +버튼을 클릭시 할일 목록 작성 페이지를 띄운다.
- CalenderView와 항목 데이터를 연동하여 일자별로 사용자가 작성 데이터를 볼 수 있도록 한다.<br>

<추후 추가 될 내용>
- checkBox 체크시 캐릭터 행동 변화

<br><업데이트 상황>
- 어플 첫 실행시 사용안내페이지 --> intro slider로 구현중
- checkBox값이 true일 경우 캐릭터 행동 변화(gif이미지 제작중) 
- writeDate로 항목을 수정/삭제하는 방식때문에 전체 삭제가 되는 오류를 id로 교체하여 해결
- checkBox 변경값이 저장되지 않는 오류 해결
- calenderView xml레이아웃 --> activity_calender2.xml 작성중
<br>
------------------------------------------------------------------------------------------------------------------------------------<br>
※ 본 코드를 팀원들 허락없이 수정/개인적인 사용/상업적 사용을 금지합니다. <br>
※ 디자인 저작권은 팀원 Juong ahyun님께 있습니다.
