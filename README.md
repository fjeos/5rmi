# ormi_5jo
오르미 5기 2차 프로젝트 5조

## 영화 블로그 서비스 (Spring Boot + Thymeleaf + AWS)

### 팀 편성
방준현 (팀장) - Frontend/Backend
유나영 - Backend
안유석 - Backend
신재민 - Backend
~~서민우~~ - Frontend

### 1단계
프로젝트 관리: [Notion](https://www.notion.so/oreumi/5-5-f5b9276610cb4268984b88c988c744d5)
- 칸반 보드: [Notion](https://www.notion.so/oreumi/2df5908f6b4c48a9b4d83df40d4face8?v=0083633ff69b49829b96d516f7f6f24c)
- 개인 일지: (프로젝트 관리 페이지 밑에 있음)
- ERD: [ERD 클라우드](https://www.erdcloud.com/d/ni3wAtJFpCCPiQKL6)
- Figma: [5rmi-movie-web](https://www.figma.com/design/uWLSoP4hmhIA64YEcyNFpg/Untitled?node-id=1-457&t=YG88Gta83mmD56EG-1)
- 이슈: [5rmi/issues](https://github.com/fjeos/5rmi/issues)
- Git 개발 브랜치: [/develop](https://github.com/fjeos/5rmi/tree/develop)

### 2단계
기본적인 게시판 기능 및 화면 구현
- 글 목록/상세/수정/삭제/추가
- Thymeleaf UI 구성

### 3단계
기능 심화
- 회원 가입, 탈퇴, 로그인 - *회원 탈퇴 구현 안됨*
- 게시판 수정/삭제 권한 제한 (자기거만)
- DB로 통해 회원 정보 저장

- 관리자 계정 정의 및 관리자 페이지 구현
- 일반 유저 정지 가능
- 관리자는 계정 권한 변경 가능 *구현 안됨*

- 공지글 목록/상세/수정/삭제/추가
- 공지글 최근 5개 까지만 조회
- DB로 통해 공지글 정보 저장
- 정지된 회원 공지글 볼 수 있음

### 서비스 배포
- AWS로 서비스 배포 **현제 develop 브랜치 Git clone 통해서 이루어짐**
