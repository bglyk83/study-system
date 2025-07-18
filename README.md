# Study System

스터디 모집 및 신청 시스템 (Spring Boot, MyBatis, Spring Security, MySQL)

## 주요 기능
- 회원가입, 로그인/로그아웃, 마이페이지
- 스터디 개설/목록/검색/상세/신청
- Spring Security 기반 세션 인증, BCrypt 암호화

## 기술 스택
- Spring Boot
- Spring Security (세션 기반)
- MyBatis
- MySQL
- Gradle
- HTML

## 폴더 구조
```
study-system/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/koreait/studysystem/
│       │       ├── config/                # Spring 설정, 시큐리티, MyBatis 등
│       │       ├── controller/            # 웹 컨트롤러
│       │       ├── entity/                # 엔티티(도메인)
│       │       ├── repository/            # MyBatis 매퍼(Repository)
│       │       └── service/               # 서비스 계층 (인터페이스/구현체)
│       ├── resources/
│       │   ├── mapper/                    # MyBatis XML 매퍼
│       │   ├── templates/                 # HTML
│       │   └── application.yml            # 환경설정 (gitignore)
├── build.gradle
├── README.md
├── .gitignore
├── database.sql
```

## 실행 방법
1. `database.sql`로 MySQL DB 생성 및 테이블 세팅
2. `application.yml`(또는 `application.properties`)에 DB 정보 입력 

3. Gradle 빌드 및 실행

```bash
./gradlew bootRun
```

## 제한 사항 요약
- 로그인한 사용자만 스터디 개설/신청/마이페이지 접근 가능
- 비밀번호는 BCrypt로 암호화
- 동일 스터디 중복 신청 불가, 정원 초과 시 신청 불가 