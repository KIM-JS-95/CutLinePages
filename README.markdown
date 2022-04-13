

![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=WELCOM%20TO%20CUTLINEPAGE&fontSize=50&animation=fadeIn&fontAlignY=38&desc=Made%20by%20KIM%20-JS&descAlignY=51&descAlign=70)
# <center> VER.2 </center>

<div align="center" style='letter-spacing:10px'>

---

## QR Code

<img src="https://user-images.githubusercontent.com/65659478/143237888-f14d9579-439a-4b84-bf0f-f68223552e45.png" width="200" height="200"/>

## www.cutlinepage.ml

---

## SKILL

<br>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/>
<img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=for-the-badge&logo=JAVASCRIPT&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/JENKINS-D24939?style=for-the-badge&logo=JENKINS&logoColor=white"/>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=DOCKER&logoColor=white"/>
<img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=AMAZONAWS&logoColor=white"/>
<img src="https://img.shields.io/badge/GRADLE-02303A?style=for-the-badge&logo=GRADLE&logoColor=white"/>
<img src="https://img.shields.io/badge/SPRINGBOOT-6DB33F?style=for-the-badge&logo=SPRINGBOOT&logoColor=white"/>
<img src="https://img.shields.io/badge/YOUTUBE_API-FF0000?style=for-the-badge&logo=YOUTUBE&logoColor=white"/>

</div>


## INTRO
```text
유튜브 채널을 운영하고 여러 게임을 촬영하면서 플레이 영상은 남길 수 있지만 나의 생각을 남기고 싶었다.

물론, 이미 해외의 큰 규모의 구독자를 가진 유튜버는 개인 사이트를 운영하여 후기를 남기곤 하지만 
한국의 많은 유튜버는 영상으로서 마무리하거나 Naver Cafe 혹은 Twitch 게시판에 남기는 정도이다.

이번 프로젝트의 목적은 
포털 사이트에서 제공되고 있는 Cafe, Blog 를 벤치마킹하여 개발 구성 부터 인프라까지 구축하고 관리하는 단계까지 
개발의 모든 단계를 이해하는데 의의가 있다.  
```

<br>

 ------------------

## 구성

![제목을-입력해주세요_-001 (1)](https://user-images.githubusercontent.com/65659478/143247907-2e7992d2-62db-4935-a7d0-87b87b9a27b5.png)

## 변경사항

### 🥳 댓글기능 구현 🥳

게시판 기능의 **완전한 기능을 구현**하고자 추가적으로 Reply(댓글)기능을 추가 한다.

#### Table 추가
댓글 기능의 경우 게시판 하나당 댓글정보는 여러개가 연결되기 때문에 `@OnetoMany` 양방향 매핑츨 시켜주어 사용하기로 한다.

추가적으로 게시판이 삭제될 경우 매핑되어 있는 댓글 또한 같이 지워질 수 있도록 `cascade`를 All로 지정하여 별도 삭제 기능 추가 없이
작업이 이루어 질 수 있도록 한다.

단, 추후 Account와 매핑할 경우 `cascade` 기능을 사용하지 않고 **고아 객체**로 만들어 두어야한다.


```java
@OneToMany(mappedBy = "gallary",cascade = CascadeType.ALL)
    private List<GallaryReply> gallaryReply = new ArrayList<>();
```

당연히데이터 입력 후 추가적으로 PK를 입력하여 JOIN 처리를 해주어야 한다.

```java
  public void setGallary(Gallary gallary){
        this.gallary=gallary;

        if(!gallary.getGallaryReply().contains(this)){
            gallary.getGallaryReply().add(this);
        }
    }
```

![3](https://user-images.githubusercontent.com/65659478/149054013-8021c9a2-61eb-4e1d-979c-14d60b84c660.png)

#### 댓글 닉네임과 Comment 호출
댓글이라면 익명 댓글 보다는 닉네임 댓글을 사용하는 것이 모두를 위해 좋다고 생각했다.

이전에 매핑시켜놓은 테이블중 `Reply테이블`에서 `Account` 컬럼에 pk를 입력시켜주고 타입리프를 통해 호출해 주면 될것이다.

#### * 추가
각 테이블에 속해 있는 댓글을 출력하기 위해 아래와 같이 `gallary.getGallaryReply()`로 수정한다.
```java

    // 게시판 상세 내용 출력
    @GetMapping("/gallary/view/{id}")
    private String find(@PathVariable("id") Long index, Model model) {
        Gallary gallary = gallaryService.findIndex(index).orElseThrow(()
                -> new IllegalArgumentException("error"));
        

        model.addAttribute("details", gallary);
        
        
        * model.addAttribute("replys", gallary.getGallaryReply());

        return "home/gallary/gallarydetail";
    }
    
```


#### FE 페이지 구성

`Contents`와 `댓글`을 사용자가 쉽게 볼 수 있도록 오른편에 구성하여 페이지의 허전함을 개선하였다.
그러나 댓글의 `닉네임`을 노출시기는 것이 좋다 생각했다.
![제목 없음](https://user-images.githubusercontent.com/65659478/148678245-dbdb6802-ae00-40e5-b74e-927949005e08.png)

#### -> 댓글 닉네임 개선 후
![2](https://user-images.githubusercontent.com/65659478/149053650-de567e42-ec7d-4f1c-bad0-f0c27c7dbdf5.png)



### 🥳 MAIN 페이지 구현 🥳

#### VER.1 메인 페이지
![main](https://user-images.githubusercontent.com/65659478/149650552-d1e7f3e1-8ddf-4c00-b0c5-7f03e6b4ed0d.png)

몇개의 아이콘으로만 구현했으며 기능은 따로 없으며 디자인적으로 해당 페이지의 역할이나 테마를 알수가 없다.


#### VER.2 메인 페이지

페이지의 테마 그리고 현재 유튜브 영상 업로드 상황을 알리기 위해 `동적 베너`를 제작하여 구성한다

공식 `YouTube API`를 사용하여 나의 채널에서 영상의 정보를 크롤링하여 DB에 저장 후 사용하였다.
이미지 또한 별도의 저장없이 유튜브에서 저장되어있는 `데이터 링크`를 주소로 사용하여 비용절감까지 가능하였다.
(아래 코드의 `*`를 확인하면 이해가 쉽다.)

![ezgif com-gif-maker](https://user-images.githubusercontent.com/65659478/150127762-368d5b2e-b905-4ce6-9d35-808e3ddb2b15.gif)


```html
   <div id="wrapper">
        <div id="slider-wrap">
            <ul id="slider">
                <li th:each="youtube: ${youtubes}">
                    <div>
                        <h5 th:text="${youtube.Title}">Slide #1</h5>
                    </div>
                   * <a th:href="@{https://youtu.be/{link} (link=${youtube.VideoId}) }">
                   * <img th:src="@{https://i.ytimg.com/vi/{Id}/hqdefault.jpg (Id=${youtube.VideoId}) }">
                    </a>
                </li>


            </ul>

            <!--controls-->
            <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
            <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
            <div id="counter"></div>

            <div id="pagination-wrap">
                <ul>
                </ul>
            </div>
            <!--controls-->
        </div>
    </div>
```


---
### 🐹 TODO LIST !!! 🐹

#### 1. 댓글기능 구현 🙆
#### 2. YOUTUBE API 섬네일 및 영상 링크 배너 구현 🙆


### DeBug list


---

기본 브렌치  =  master

FE 브렌치 = main
BE 브렌치 = dev
Local_address : http://localhost:8081

Deploy_address : www.cutlinepage.ml:8081

---

### 🛠 update Log 🛠 
[README_ver.1.markdown](./DevLog/README_ver.1.markdown)

### 🛠 Git Blog 🛠
https://kim-js-95.github.io/2021/11/24/portfolio-3.html

