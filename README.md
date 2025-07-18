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
- HTML (Thymeleaf 최소 사용)

## 실행 방법
1. `database.sql`로 MySQL DB 생성 및 테이블 세팅
2. `application.yml`(또는 `application.properties`)에 DB 정보 입력 

3. Gradle 빌드 및 실행

```bash
./gradlew bootRun
```

## 주요 기능 요약
- 로그인한 사용자만 스터디 개설/신청/마이페이지 접근 가능
- 비밀번호는 BCrypt로 암호화
- 동일 스터디 중복 신청 불가, 정원 초과 시 신청 불가 