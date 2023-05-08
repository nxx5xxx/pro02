CREATE TABLE USER1(ID VARCHAR2(20) PRIMARY KEY,PW VARCHAR2(100) NOT NULL,NAME VARCHAR2(20) NOT NULL,
TEL VARCHAR2(13) NOT NULL,ADDR VARCHAR2(100) ,EMAIL VARCHAR2(100) ,REGDATE DATE DEFAULT SYSDATE,POINT NUMBER);

insert into user1 values('admin','1234','관리자','01011111111','경기도 고양시 일산동구','admin@naver.com','20230311',0	);
insert into user1 values('kim','2345','김김김','01022222222','경기도 고양시 일산서구','kim@naver.com','20230312',0	);
insert into user1 values('lee','3456','이이이','01033333333','경기도 김포시 운양동','lee@naver.com','20230313',0	);
insert into user1 values('park','4567','박박박','01044444444','경기도 김포시 장기동','park@naver.com','20230314',0	);
insert into user1 values('choi','5678','최최최','01055555555','경기도 의정부시 민락동','choi@naver.com','20230315',0	);
insert into user1 values('son','6789','손손손','01066666666','경기도 의왕시 내손동','son@naver.com','20230316',0	);


CREATE TABLE PRODUCT(
    PCODE CHAR(5) PRIMARY KEY,PNAME VARCHAR2(100) NOT NULL,PRICE NUMBER NOT NULL,
    PDESC VARCHAR2(300),PAMOUNT NUMBER NOT NULL);
    
insert into product values('30001','티어스 오브더 킹덤',74800,'젤다의 전설',25	);
insert into product values('30002','포트리스',39800,'닌텐도스위치',8	);
insert into product values('30003','원더풀라이프',44100,'목장이야기',3	);
insert into product values('30004','스플래툰3',25000,'익스팬션 패스',13	);
insert into product values('30005','포켓몬스터',25900	,'스칼랫',22	);
insert into product values('30006','마계전기 다스가이아',54800,'5/24출고예정',17	);
    
select * from product;

CREATE TABLE BUY(
    ONUM CHAR(5) PRIMARY KEY,ID VARCHAR2(20) ,PCODE CHAR(5) ,TEL VARCHAR2(13) NOT NULL,
    ADDR VARCHAR2(100) NOT NULL,AMOUNT NUMBER NOT NULL,PRICE NUMBER NOT NULL,ENAME VARCHAR2(20),
    ECODE VARCHAR2(30),STATUS VARCHAR2(15) DEFAULT '배송전',ODATE DATE DEFAULT SYSDATE,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(PCODE) REFERENCES PRODUCT(PCODE));
    
insert into buy values('10001','kim','30004','01022222222','경기도 고양시 일산서구',1,25000,' ',' ',	DEFAULT,'20230412');
insert into buy values('10002','park','30001','01044444444','경기도 김포시 장기동',2,149600,'우체국택배','B2030','배송완료','20230410');
insert into buy values('10003','kim','30006','01022222222','경기도 고양시 일산서구',1,54800,' ',' ',	DEFAULT,'20230412');
insert into buy values('10004','son','30002','01066666666','경기도 의왕시 내손동',1,39800,'로젠택배','C1020','배송완료', '20230411');

select * from buy;
    
CREATE TABLE BASKET(
    BNUM CHAR(5) PRIMARY KEY,ID VARCHAR2(20) ,PCODE CHAR(5),
    BAMOUNT NUMBER(5) NOT NULL,PRICE NUMBER(10) NOT NULL,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(PCODE) REFERENCES PRODUCT(PCODE)); 
    
insert into basket values('50001','lee','30004',1,25000	);
insert into basket values('50002','kim','30006',1,54800	);
insert into basket values('50003','park','30002',1,39800	);
insert into basket values('50004','son','30004',1,25000	);
insert into basket values('50005','choi','30004',1,25000	);
    
select * from basket;
    
CREATE TABLE PAYMENT(
    PNUM CHAR(5) PRIMARY KEY, ID VARCHAR2(20), ONUM CHAR(5), PAYMTD VARCHAR2(20) NOT NULL, 
    CREDIT VARCHAR2(50), PRICE NUMBER(10) NOT NULL, PAYDATE DATE DEFAULT SYSDATE,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(ONUM) REFERENCES BUY(ONUM)); 
    
insert into payment values('20001','kim','10001','카드','1111-2222-3333-4444',	25000,	'20230412');
insert into payment values('20002','park','10002','계좌이체','110-323-805402',	149600,	'20230410');
insert into payment values('20003','kim','10003','카드','1111-2222-3333-4444',	54800,	'20230412');
insert into payment values('20004','son','10004','카드','1212-2323-1212-2323',	39800,	'20230411');

select * from payment;

CREATE TABLE REVIEW(
    BNO CHAR(5) PRIMARY KEY, ID VARCHAR2(20) , ONUM CHAR(5) , B_DATE DATE DEFAULT SYSDATE, 
    B_REVIEW VARCHAR2(500) NOT NULL, B_SCORE NUMBER(1) DEFAULT 5,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(ONUM) REFERENCES BUY(ONUM)); 
    
insert into review values('1','park','10002','20230412','게임이 재밋어요',5	);
insert into review values('2','son','10004','20230412','배송이 정말빨라요',5	);
 
 
select * from review;


    
--5. 회원 포인트 갱신 프로시저 
select * from user1;

SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE update_point_pro
    (vid IN user1.id%TYPE, vpoint IN user1.point%TYPE)
    IS
    BEGIN
    UPDATE user1 SET point=vpoint WHERE id=vid;
    COMMIT;
    END;
/

EXECUTE update_point_pro('kim',150);
EXECUTE update_point_pro('lee',200);
EXECUTE update_point_pro('park',60);
EXECUTE update_point_pro('choi',19);
select * from user1;
--6. 상품추가 프로시저 
select * from product;
CREATE OR REPLACE PROCEDURE prd_ins_pro(vpcode IN product.pcode%TYPE,vpname IN product.pname%TYPE,
    vprice IN product.price%TYPE,vpdesc IN product.pdesc%TYPE,vpamount IN product.pamount%TYPE)
    IS
    BEGIN
        INSERT INTO product values(vpcode,vpname,vprice,vpdesc,vpamount);
        DBMS_OUTPUT.put_line(vpname||'상품이 등록되었습니다');
    COMMIT;
    END;
/
EXECUTE prd_ins_pro('30007','짱구는못말려',45000,'여름방학',1);
select * from product;
--7. 특정 회원 검색 함수


CREATE OR REPLACE TYPE UT AS OBJECT(ID VARCHAR2(20) ,PW VARCHAR2(100) ,NAME VARCHAR2(20) ,
TEL VARCHAR2(13) ,ADDR VARCHAR2(100) ,EMAIL VARCHAR2(100) ,REGDATE DATE);

CREATE OR REPLACE TYPE UC AS TABLE OF UT;

CREATE OR REPLACE FUNCTION get_mem_info(vid IN user1.id%TYPE)
    RETURN UC
    IS gmi UC;
    BEGIN
        SELECT UT(id,pw,name,tel,addr,email,regdate)
        BULK COLLECT INTO gmi
        FROM user1 WHERE id=vid;
        RETURN gmi;
    END;
/
SELECT id as 아이디,pw as 비밀번호,name as 이름, tel as 연락처, addr as 주소, email as 이메일,
    regdate as 가입일 FROM TABLE(get_mem_info('lee'));
--또는 
select * from user1;

CREATE OR REPLACE FUNCTION get_mem_info(vid IN user1.id%TYPE)
    RETURN user1.id%TYPE
    IS
    x number := 0;
    BEGIN
    DBMS_OUTPUT.put_line('아이디'||' | '||'비밀번호'||' | '||'이름'||'     | '||'주소'||'                         | '||'이메일'||'             | '||'가입일');
     FOR i IN (SELECT * from user1 where id=vid) LOOP
     x := x+1;
     DBMS_OUTPUT.put_line(i.id||'      | '||i.pw||'        | '||i.name||' | '||i.addr||' | '||i.email||' | '||i.regdate);
     END LOOP;
     RETURN x;
    END;
/
VAR gmi number;
EXECUTE :gmi :=get_mem_info('lee');
--8. 특정 회원의 장바구니 정보 연관쿼리 함수
-- 고객명, 연락처, 상품명, 수량, 결제할금액
--	데이터 조작 프로시저 최적화하기
-- 최적화라는게 글자제한이라면 FOR문전에 DBMS_OUTPUT.ENABLE. 로 100000글자 제한하기
SELECT * FROM BASKET;
SELECT a.name as name, a.tel as tel, c.pname as pname, b.bamount as bamount, (b.bamount*c.price) as price from user1 a , basket b , product c where a.id=b.id and c.pcode=b.pcode;

CREATE OR REPLACE FUNCTION user_basket_fnc(vid IN basket.id%TYPE)
    RETURN basket.id%TYPE
    IS
    x number := 0;
    BEGIN
    DBMS_OUTPUT.put_line('고객명'||' | '||'연락처'||'         | '||'상품명'||'                     | '||'수량'||' | '||'결제할 금액');
     FOR i IN (SELECT a.name as name, a.tel as tel, c.pname as pname, b.bamount as bamount,
     (b.bamount*c.price) as price from user1 a , basket b , product c where a.id=b.id and c.pcode=b.pcode and a.id=vid) LOOP
     x := x+1;
     DBMS_OUTPUT.put_line(i.name||' | '||i.tel||' | '||i.pname||' |   '||i.bamount||'    | '||i.price);
     END LOOP;
     RETURN x;
    END;
/
VAR ubf number;
EXECUTE :ubf := user_basket_fnc('kim');


create table pro(tno number, pname varchar2(40), dan number); -- 상품 테이블
INSERT INTO pro VALUES(100,'카스맥주',1500);
INSERT INTO pro VALUES(200,'테라맥주',1000);
INSERT INTO pro VALUES(300,'필라이트맥주',2000);
INSERT INTO pro VALUES(400,'하이트맥주',1800);
INSERT INTO pro VALUES(500,'클라우드맥주',1600);
create table inventory(ino number, quant number, price number); -- 재고 테이블
create table wearing(ino number, quant number, price number); -- 입고 테이블
create table sales(ino number, quant number, price number); -- 판매 테이블
INSERT INTO SALES VALUES(200,3,7500);
INSERT INTO SALES VALUES(300,9,2500);
INSERT INTO inventory VALUES(300,15,2500);


--9. 반품쿼리 트리거 return_trigger
CREATE OR REPLACE TRIGGER return_trigger
    AFTER DELETE ON sales
    FOR EACH ROW
    DECLARE
        x number;
    BEGIN
        SELECT count(*) INTO x FROM inventory WHERE ino=:OLD.ino;
            IF(x=0) THEN INSERT INTO inventory values(:OLD.ino, :OLD.quant, :OLD.price);
            ELSE UPDATE inventory SET quant=quant+:OLD.quant, price= price+:OLD.price
            WHERE ino=:OLD.ino;
            END IF;
    END;
/

SELECT * FROM sales;
SELECT * FROM inventory;
DELETE FROM sales WHERE ino=200;
SELECT * FROM inventory;
DELETE FROM sales WHERE ino=300;
SELECT * FROM inventory;

DROP TRIGGER return_trigger;




--10. 상품 판매 쿼리 트리거  sales_trigger
CREATE OR REPLACE TRIGGER sales_trigger
    AFTER INSERT ON sales
    FOR EACH ROW
    DECLARE
     x number;
    BEGIN
        SELECT quant-:NEW.quant INTO x FROM inventory WHERE ino=:NEW.ino;
        IF(x<=0) THEN DELETE FROM inventory where ino=:NEW.ino;
        ELSE update inventory SET quant=quant-:NEW.quant, price=price-:NEW.price;
        END IF;
    END;
/
insert into inventory values(300,24,5000);
insert into inventory values(200,3,7500);

SELECT * FROM sales;
SELECT * FROM inventory;

INSERT INTO sales values(200,3,7500);
SELECT * FROM sales;
SELECT * FROM inventory;

INSERT INTO sales values(300,10,2500);
SELECT * FROM sales;
SELECT * FROM inventory;

