# 프로젝트 개요
## 프로젝트 명 : 소프라노몰
## 프로젝트 기간 : 2023.05.09 ~
## 프로젝트 참여  : 김우주
## JAVA ver : 1.8.0_241
## JAVA script ver : 1.0
## Dynamic Web Module : 3.1
## DBMS : Oracle 11g xe
## External Library : json , cos , commons , crypto , mail
## JAVA Script Plugin : JQuery
## JAVA Script API : DAUM 주소 API , json 데이터 처리
## 프로젝트 데이터 처리패턴 : MVC2
- View -> Controller > Model > Controller > View

<br>

## 프로젝트 주요 기능 : 회원 , 공지사항 등

<br>

## 프로젝트 특이사항
### 회원 가입
- 아이디 중복체크 시 json을 이용해 현재 창에서 구현
- 비밀번호는 AES256을 이용하여 암호화 처리하여 저장
- 주소의 입력은 DAUM 주소 API를 활용하여 입력받아 저장

### 공지사항(게시판)
- 관리자계정(admin) 은 모든 글을 수정 및 삭제 가 가능함
- 다른 계정은 자신의 글만 수정 및 삭제가 가능
- 첨부파일 기능을 구현하여 업로드가 가능
- 해당하는 글에 들어가 업로드 되어있는 파일 다운로드 가능

### 결제
- 결제 기능은 결제 API를 활용하여 구현
- 결제 후 구매 완료를 하면 이용후기가 작성가능
- 배송시스템은 결제 후 이루어진다

### 배송
- 배송시스템은 국내택배만을 취급한다
- 송장번호가 생긴후 송장 조회를 하면 해당 택배사의 조회주소로 연결시켜준다

---

# 프로젝트 설계
## 개념적 설계 (유스케이스)
- 유스케이스

![개념적 설계](./readmeimg/usecase.PNG "유즈케이스")

---
## 논리적 설계 (검증 표)
- 논리 검증 체크리스트

![논리적 설계](./readmeimg/logiccheck.PNG "논리검증 체크리스트")

- 논리적 ERD

![논리적 설계](./readmeimg/logicERD.PNG "논리적 ERD")


---
## 물리적 설계
- 데이터베이스 ERD

![물리적 설계](./readmeimg/dataBaseERD.PNG "데이터베이스 ERD")

---
## 클래스 설계
- 클래스 다이어 그램

![물리적 설계](./readmeimg/classDiagram.PNG "데이터베이스 ERD")

---
## 시퀀스 설계
- 시퀀스 설명

![시퀀스 설계](./readmeimg/jungboERD1.PNG "시퀀스 설명")

![시퀀스 설계](./readmeimg/jungboERD2.PNG "시퀀스 설명")

![시퀀스 설계](./readmeimg/jungboERD3.PNG "시퀀스 설명")

![시퀀스 설계](./readmeimg/jungboERD4.PNG "시퀀스 설명")

---
## 프로젝트 구성
- (리소스구성도)

![프로젝트 구성](./readmeimg/resource.PNG "리소스 구성도")