# ToDoong

Jica Android 백앤드 개발자 과정의 Todoong팀 프로젝트 입니다.
리더: Lee Jinyoung
팀원: Kang mingu, Juong ahyun

<프로젝트 내용>

- 목표: 사용자가 즐겁게 일정을 소화할 수 있도록 캐릭터와 연동 된 todoList를 만든다.

<기술>
- DataBase는 SQLite를 사용하여 데이터의 추가/삭제/수정 기능을 이용할 수 있도록 짠다.
  SQLite를 이용한 이유: 가볍게 사용하는 체크리스트 어플인 만큼 서버에 저장을 하여 영구적으로 남기는 방법보단
                       핸드폰 내에서 저장되게 하는게 효과적이라 생각되어 채택하게 되었다.
                       
- RecyclerView를 이용하여 효과적으로 투두리스트 항목을 관리하도록 한다.
- BottomSheet를 이용하여 +버튼을 클릭시 할일 목록 작성 페이지를 띄운다.
- CalenderView와 항목 데이터를 연동하여 일자별로 사용자가 작성 데이터를 볼 수 있도록 한다.

<업데이트 상황>
- whritDate로 항목을 구분하는 방식을 id로 교체