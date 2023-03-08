# bebeShare 육아용품 무료 나눔 웹사이트   


### :baby_bottle: 사용하지 않는 육아용품을 나눔하고 나눔받을 수 있는 서비스

> - 나눔을 희망하는 유저(쉐어)는 육아용품을 게시하고 나눔할 수 있습니다
> - 나눔받기를 희망하는 유저(베베)는 댓글을 통해 나눔받기 신청을 할 수 있습니다
> - 댓글을 통해 해당 물품을 나눔 받을 수 있습니다

### :three: 나눔받기 횟수의 제한

> - 유저는 일정기간 제한된 나눔받기 횟수만큼 나눔을 받을 수 있습니다
> - 제공된 나눔받기 횟수를 모두 소진하더라도 나눔을 할 경우 나눔횟수가 추가됩니다
> - 나눔하기 횟수는 제한이 없습니다

### :heavy_check_mark: 관심목록 찜 기능

> - 베베는 상품 상세 페이지에서 상품을 찜할 수 있습니다

### :family: 마이페이지

> - 마이페이지에서 나눔내역 횟수, 나눔목록, 찜목록, 댓글 목록을 볼 수 있습니다



## 팀원 소개

|             [k](https://github.com/superkkj)              |              [t](https://github.com/TevLee)               |            [y](https://github.com/omnireader0)            |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://res.cloudinary.com/divncmfka/image/upload/v1645906082/me_bvddac.jpg" width="150" height="150"> | <img src="https://user-images.githubusercontent.com/69510442/156530657-0bf8a79c-232b-4fd4-b10b-1f2c080d2aa3.png" width="150" height="150"> | <img src="https://res.cloudinary.com/divncmfka/image/upload/v1645950212/%E1%84%89%E1%85%A5%E1%86%AB%E1%84%92%E1%85%B4%E1%84%82%E1%85%B5%E1%86%B7_yr5abm.jpg" width="150" height="150"> |
|                           BackEnd                            |                           BackEnd                            |                           BackEnd                            |

<br>

## 개발 후기

[유진후기](https://sebiblog.tistory.com/36#%F-%-F%--%--%--%EB%A-%--%ED%--%AC)


## 개발 기간

- 2022.03.15 ~ 2022.04.26



## 개발 환경

<p>
<img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/Spring Boot -6DB33F?style=flat-square&logo=Spring Boot&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/JPA-6DB33F?style=flat-square&logo=&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/Query DSL-0769AD?style=flat-square&logo=&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/MariaDB -4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
<img src="https://img.shields.io/badge/Oauth-4285F4?style=flat-square&logo=Google&logoColor=white"/>
</p>

<p>
<img src="https://img.shields.io/badge/Heroku -232F3E?style=flat-square&logo=Heroku&logoColor=white" alt=""/>
<img src="https://img.shields.io/badge/GitHub Actions -2088FF?style=flat-square&logo=GitHub Actions&logoColor=white" alt=""/>
</p>



## 배포

[배포 LINK](https://bebeshare-deploy.herokuapp.com/)



## 기획 및 설계

[위키 바로 가기](https://lean-owner-437.notion.site/19dcfe16062f4078acf0220b710057e4)

[UI 디자인](https://www.figma.com/file/of7JanRjo7pGiLBRmXsoXS/%EC%B9%B4%EC%9A%B0%EC%B9%98%EC%BD%94%EB%94%A9-figma%ED%8C%8C%EC%9D%BC?node-id=18%3A4)

[DB 명세서](https://lean-owner-437.notion.site/DB-dc8f9dfa82504658b3324594b1e9f214)  

[API 명세서](https://lean-owner-437.notion.site/API-90cc212689ad4070b10fa83f822301bf)

![db800x715](https://user-images.githubusercontent.com/48271665/162228879-9fa529cc-c351-4397-92c7-673392d27cbf.png)

## 개발 시 기술적 고민했던 부분 

정말 사소한 2가지입니다.

#### 1. 스프링 공부를 하고 있었기 때문에, 스프링 관련 고민들이 있었습니다.

컨트롤러에서 리턴하는 방식에 대해 고민이 있었습니다. 공부도 하고 구글링해보니, 1) repository에서 엔티티를 조회한 뒤 dto로 변경하는 방식(서비스에서 엔티티 리턴하고, 컨트롤러에서 응답할 때 dto로 감싸서 리턴하는 방식)이 있고, 2) 다른 하나는 dto를 직접 조회하는 방식(서비스에서 dto를 만들어서 컨트롤러에게 리턴하는 방식)이 있었습니다.


제 생각에는 dto를 만들어서 컨트롤러에서 리턴하는 방식이 단계가 하나 더 추가되기 때문에 복잡해 보이지만, 컨트롤러에서 리턴할 때 비지니스 로직을 감추고 의존성을 낮출 수 있다고 생각했습니다.
 

예를 들어, 엔티티와 dto의 디자인이 같으면 엔티티를 반환해도 되지만, 많은 경우에 디자인이 다르고, 외부 노출하면 안되는 경우가 있기 때문에 dto를 만들어서 컨트롤러에서 리턴하는 방식이 훨씬 이점이 있다고 보아 코드를 작성했습니다.

```java
 // 상품 게시글 조회
    @GetMapping("/products")
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }


    // 상품 게시글 상세 조회
    @ResponseBody
    @GetMapping("/products/{productId}")
    public ProductResponseDto findById(@PathVariable Long productId) {
        return productService.findById(productId);
    }
```

 

#### 2. 배포시 세션 기반의 로그인의 세션 유지 문제

헤로쿠를 이용해 배포 계획을 세웠는데, 헤로쿠는 계속 떠 있는 것이 아니라 호출해야 동작합니다. 그런데 로그인을 OAuth2.0을 세션 방식으로 구현했기 때문에 배포시 세션 쪽에 문제가 발생합니다. 세션 방식은 서버에서 세션을 저장하고 유지하는 방식인데, 여러 서버가 접근하는 경우, 중간에 다른 서버에 로그인되면, 기존 서버의 세션 유지에 실패합니다.

 
따라서 세션의 정보를 서버가 아닌 다른 메모리에 저장할 필요가 있다고 생각했고, Redis라는 것을 알게 되었습니다. Redis는 일종의 보조? 캐시 디비로, 다중 서버가 접근하면, 각 세션 정보를 redis에 저장하여 사용할 수 있는 형태가 됩니다. 

 
이때, Spring session과 redis 관련된 의존성을 추가하고, 설정 파일에 redis 주소를 설정해주고 코드를 작성하면 간단하게 redis를 통한 세션 관리가 가능해집니다.
(Spring session을 이용하면 톰캣에 연결하지 않고도 클라우드 서비스 간에 세션 정보를 공유할 수 있음)

