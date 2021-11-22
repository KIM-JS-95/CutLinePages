### 기본 브렌치  =  master
### www.cutlinepage.ml

## 🐹🐹  My Goal !!!

### 1. CRUD api 기능 구현 🙆
### 2. 타임리프 적용 html 생성 🙆
### 3. 로그인 기능 구현 (등급 별로 사용자 권한) 🙆
### 4. 게시판 기능 추가 (연결해야해요!!!) 🙆
### 5. 검색기능 수정 / user 접속기능 개선
### 5. html을 꾸며야 해요 (Admin / 게임신청 / 게임리스트) 개별 🙆
### 6. 게임 상세 페이지 완성 시켜야 해요 🙆
### 7. 이미지 관리를 위한 s3 구성
### 8. 관리자 페이지 구성만 하면 끝


## TODO

무중단 배포방식 선정 (Docker / Jenkins / AWS) 

관리자는 로그인하면 관리자 페이지로 바로 이동시키기 **(html에서는 관리자 페이지 이동 버튼 지우기)**

이미지 게시를 위한 s3 이미지 api 구성

배포 문제 발생 뷰 에러

## 새로이 알게된메소드
```bash
1. // springb security에서 사용자의 현재 정보를 가져오기
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
```

자동 배포 설정



 ------------------

## 구성

### 1. 로그인 페이지
![](./src/main/resources/image/html.png)

### 2. 메인 메뉴
![](./src/main/resources/image/main.png)

### 3. 메인 메뉴
![](./src/main/resources/image/ragi.png)

### 4. 요청 게임 목록 페이지
![](./src/main/resources/image/gallary.png)


---

# port 📱
### http://localhost:8081

## stack 🔨
1. springboot 2.2.1.RELEASE
2. Gradle 7.1 ver
3. aws (S3 / EC2 / RDS)
4. Jenkins
5. Docker
6. Ajax


