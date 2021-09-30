## 🐹🐹  My Goal !!!


### 1. CRUD api 기능 구현 🙆
### 2. 타임리프 적용 html 생성 🙆
### 3. 로그인 기능 구현 (등급 별로 사용자 권한) 🙆
### 4. 게시판 기능 추가 (연결해야해요!!!) 🙆
### 5. 검색기능 수정 / user 접속기능 개선
### 5. html을 꾸며야 해요 (Admin / 게임신청 / 게임리스트) 개별 🙆
### 6. 게임 상세 페이지 완성 시켜야 해요 🙆
### 7. 게임 다운 링크 안정성 검사 구성할것

```text
관리자만 들어갈 수 있도록 .antMatchers("Admin")
admin.html -> 모든 유저의 정보와 겔러리의 데이터를 관리해야함

가입자만 들어갈 수 있도록 .antMatchers("Manager" , "Admin")
게임신청! -> gallarypost.html 을 이쪽으로 연결할 것

누구나 들어갈수 있도록 .permitAll()
게임리스트 -> gallary.html 을 이쪽으로 연결할 것
```

## TODO
관리자는 로그인하면 관리자 페이지로 바로 이동시키기 **(html에서는 관리자 페이지 이동 버튼 지우기)**

Admin 유저는 모든 기능을 사용할 수 있어야함 (Admin / User / Guest) 접속가능 (test 단계)


1. Gallary 테이블에 username 인덱스에 번호 말고 이름 저장하고 싶다.

2. Account 테이블 username (user / admin / guest) 말고 닉네임을 쓰는것이 좋을듯 하다.


새로이 알게된 메소드
```bash
1. // springb security에서 사용자의 현재 정보를 가져오기
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        
2. //ㄴ 
    Account
```
