

0열 선택0열 다음에 열 추가
1열 선택1열 다음에 열 추가
2열 선택2열 다음에 열 추가
3열 선택3열 다음에 열 추가
4열 선택4열 다음에 열 추가
0행 선택0행 다음에 행 추가
1행 선택1행 다음에 행 추가
2행 선택2행 다음에 행 추가
3행 선택3행 다음에 행 추가
4행 선택4행 다음에 행 추가
5행 선택5행 다음에 행 추가
6행 선택6행 다음에 행 추가
7행 선택7행 다음에 행 추가
8행 선택8행 다음에 행 추가
9행 선택9행 다음에 행 추가
셀 전체 선택
열 너비 조절
행 높이 조절
기능

Method

URL

Request

Response

메인페이지

GET

/


index.html

게시글 올리기

POST

/api/posts

{

"username" : String

"title" : String,

"contents" : ,

Header

Authorization : Bearer

<JWT>

}

success

(Status.OK)

전체글 조회하기

GET

/api/posts


[{

"title" : String,

"username" : String

"contents" : String

"creatdDate" : LocalDateTime

}]

게시글 수정하기

PUT

/api/products/{id}

{

"username" : String

"title" : String,

"contents" : ,

Header

Authorization : Bearer

<JWT>

}

success

(Status.OK)

게시글 삭제하기

DELETE

/api/products/{id}

{

"username" : String

Header

Authorization : Bearer

<JWT>

}

success

(Status.OK)

회원가입 페이지

GET

/api/user/signup


signup.html

회원가입

POST

/api/user/signup

{

"username" : String,

"password" : String,

"email" : String,

"admin" : boolean,

"adminToken" : String

}

success

(Staus.OK)

로그인 페이지

GET

/api/user/login


login.html

로그인

POST

api/user/login

{

"username" : String,

"password" : String

}

Header

Authorization : Bearer

<JWT>

success

(Status.OK)

셀 병합
행 분할
열 분할
너비 맞춤
삭제
﻿
