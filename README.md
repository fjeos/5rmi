# ormi_5jo: 영화 블로그 서비스
오르미 백엔드 양성 과정 / 5기 / Java/SpringBoot 프로젝트 / 5조

![image](https://github.com/user-attachments/assets/90380d34-9ad0-4dea-aa79-0155d708057e)

## 프로젝트 소개
~~[현제 AWS 배표 링크](http://3.38.190.229:8080/)~~ / ~~관리자 계정 (example): hello1 (비밀번호도 동일)~~

### 팀 편성
| 방준현 | 유나영 | 안유석 | 신재민 | ~~서민우~~ |
|:-----------------:|:-----------------:|:-----------------:|:-----------------:|:-----------------:|
| 팀장 (Frontend/Backend) | Frontend/Backend | Backend | Backend | Frontend |
| <img src="https://github.com/bindingflare.png" width="60px;"/> | <img src="https://github.com/fjeos.png" width="60px;"/> | <img src="https://github.com/duys2.png" width="60px;"/> | <img src="https://github.com/Freddieshin.png" width="60px;"/> | |
| Thymeleaf, Figma | Thymeleaf |  |  | Figma |
| WebSecurity, 게시판, 관리자 | 게시글, 커멘트, 관리자 | 게시글, 커멘트 | 유저, 공지글 | |

## 1단계
### 프로젝트 관리: [Notion 종합 페이지](https://www.notion.so/oreumi/5-5-f5b9276610cb4268984b88c988c744d5)
- 칸반 보드: [Notion](https://www.notion.so/oreumi/2df5908f6b4c48a9b4d83df40d4face8?v=0083633ff69b49829b96d516f7f6f24c)
- 개인 일지: (프로젝트 관리 페이지 밑에 있음)
- ERD: [ERD 클라우드](https://www.erdcloud.com/d/ni3wAtJFpCCPiQKL6)

![image](https://github.com/user-attachments/assets/fbcaff8f-09f6-4bc1-b2e4-44b2210a70bd)
- Figma: [5rmi-movie-web](https://www.figma.com/design/uWLSoP4hmhIA64YEcyNFpg/Untitled?node-id=1-457&t=YG88Gta83mmD56EG-1)

![image](https://github.com/user-attachments/assets/ecba1ad2-d0aa-4b51-9ff8-1c89cbd68fcc)
- 이슈: [5rmi/issues](https://github.com/fjeos/5rmi/issues)
- Git 개발 브랜치: [/develop](https://github.com/fjeos/5rmi/tree/develop)

## 2단계
### 기본적인 게시판 기능 및 화면 구현
- 글 목록/상세/수정/삭제/추가
- (추가) 게시판 댓글 목록/상세/수정/삭제/추가
- Thymeleaf UI 구성
<details>
  <summary>이미지 참조</summary>
  <img src="https://github.com/user-attachments/assets/ddfb2143-ec7b-4dd2-bfa8-08ca3eb58b0b">
  <img src="https://github.com/user-attachments/assets/21c9ce25-f3f6-4942-bd0c-3833ab746290">
</details>

Milestones:
1. [Post featureset](https://github.com/fjeos/5rmi/milestone/1?closed=1)
1. [Comment featureset](https://github.com/fjeos/5rmi/milestone/2?closed=1)
2. [Board featureset](https://github.com/fjeos/5rmi/milestone/4?closed=1)

## 3단계
### 회원 기능 심화
- 회원 가입, 탈퇴, 로그인
- 게시판 수정/삭제 권한 제한 (자기거만)
- DB로 통해 회원 정보 저장

<details>
  <summary>이미지 참조</summary>
  <img src="https://github.com/user-attachments/assets/0da8d88c-75f8-4fbe-92d6-bf324a063796">
  <img src="https://github.com/user-attachments/assets/292c0737-42a7-4148-af9c-eb7ddc28f5a6">
</details>

Milestones:
1. [WebSecurity](https://github.com/fjeos/5rmi/milestone/6?closed=1)
2. [Profile Feature](https://github.com/fjeos/5rmi/milestone/5?closed=1)

### 관리자 기능 심화
- 관리자 계정 정의 및 관리자 페이지 구현
- 일반 유저 정지 가능
- 관리자는 계정 권한 변경 가능

<details>
  <summary>이미지 참조</summary>
  <img src="https://github.com/user-attachments/assets/f83bbcb2-321f-4cfb-8569-b8f1d8844abe">
</details>

Milestones:
1. [Banhammer](https://github.com/fjeos/5rmi/milestone/7?closed=1)
2. [WebSecurity](https://github.com/fjeos/5rmi/milestone/6?closed=1)

### 공지글 기능 심화
- 공지글 목록/상세/수정/삭제/추가
- 공지글 최근 5개 까지만 조회
- DB로 통해 공지글 정보 저장
- 정지된 회원 공지글 볼 수 있음

<details>
  <summary>이미지 참조</summary>
  <img src="https://github.com/user-attachments/assets/78f2b115-f52d-4dea-a105-5eaa7a12ed1d">
  <img src="https://github.com/user-attachments/assets/c1fd9c21-9eb4-4b0d-b93c-820a64ddb5b0">
</details>

Milestones:
1. [Board featureset](https://github.com/fjeos/5rmi/milestone/4?closed=1)
2. [Banhammer](https://github.com/fjeos/5rmi/milestone/7?closed=1)
3. [WebSecurity](https://github.com/fjeos/5rmi/milestone/6?closed=1)

## 4 단계
### 서비스 배포
- AWS로 **현제 develop 브랜치 Git clone 통해서 이루어짐**

## Miscellaneous
### Movie API 연동 시도
- TODO
