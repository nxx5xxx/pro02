/*
create table notice(
idx int primary key,
title varchar2(100) not null,
content varchar2(1000),
author varchar2(20),
file1 varchar2(200),
resdate date default sysdate,
cnt NUMBER default 0
);

insert into notice values(noti_seq.nextval,'제목1','내용입니다1','저자입니다','파일1',default,default);
insert into notice values(noti_seq.nextval,'제목2','내용입니다2','저자입니다','파일1',default,default);
insert into notice values(noti_seq.nextval,'제목3','내용입니다3','저자입니다','파일1',default,default);

create sequence noti_seq;


CREATE TABLE USER1(ID VARCHAR2(20) PRIMARY KEY,PW VARCHAR2(100) NOT NULL,NAME VARCHAR2(20) NOT NULL,
TEL VARCHAR2(13) NOT NULL,ADDR VARCHAR2(100) ,EMAIL VARCHAR2(100) ,REGDATE DATE DEFAULT SYSDATE,POINT NUMBER default 0);

insert into user1 values('admin','1234','관리자','01011111111','경기도 고양시 일산동구','admin@naver.com','20230311',0	);
insert into user1 values('kim','2345','김김김','01022222222','경기도 고양시 일산서구','kim@naver.com','20230312',0	);
insert into user1 values('lee','3456','이이이','01033333333','경기도 김포시 운양동','lee@naver.com','20230313',0	);
insert into user1 values('park','4567','박박박','01044444444','경기도 김포시 장기동','park@naver.com','20230314',0	);
insert into user1 values('choi','5678','최최최','01055555555','경기도 의정부시 민락동','choi@naver.com','20230315',0	);
insert into user1 values('son','6789','손손손','01066666666','경기도 의왕시 내손동','son@naver.com','20230316',0	);


CREATE TABLE PRODUCT(
    PCODE CHAR(5) PRIMARY KEY,PNAME VARCHAR2(100) NOT NULL,PRICE NUMBER NOT NULL,
    PDESC VARCHAR2(300),PAMOUNT NUMBER NOT NULL);

alter table product add ccode varchar2(8) default 0100 NOT NULL;
alter table porduct modify ccode varchar2(8) NOT NULL;
commit;
select * from product;

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
    FOREIGN KEY(PCODE) REFERENCES PRODUCT(PCODE));
    
insert into basket values('50001','lee','30004',1,25000	);
insert into basket values('50002','kim','30006',1,54800	);
insert into basket values('50003','park','30002',1,39800	);
insert into basket values('50004','son','30004',1,25000	);
insert into basket values('50005','choi','30004',1,25000	);
    
select * from basket;
    
CREATE TABLE PAYMENT(
    PNUM CHAR(5) PRIMARY KEY, ID VARCHAR2(20), ONUM CHAR(5), PAYMTD VARCHAR2(20) NOT NULL, 
    CREDIT VARCHAR2(50), PRICE NUMBER(10) NOT NULL, PAYDATE DATE DEFAULT SYSDATE,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(ONUM) REFERENCES BUY(ONUM));

insert into payment values('20001','kim','10001','카드','1111-2222-3333-4444',	25000,	'20230412');
insert into payment values('20002','park','10002','계좌이체','110-323-805402',	149600,	'20230410');
insert into payment values('20003','kim','10003','카드','1111-2222-3333-4444',	54800,	'20230412');
insert into payment values('20004','son','10004','카드','1212-2323-1212-2323',	39800,	'20230411');

select * from payment;

CREATE TABLE REVIEW(
    BNO CHAR(5) PRIMARY KEY, ID VARCHAR2(20) , ONUM CHAR(5) , B_DATE DATE DEFAULT SYSDATE, 
    B_REVIEW VARCHAR2(500) NOT NULL, B_SCORE NUMBER(1) DEFAULT 5,FOREIGN KEY(ID) REFERENCES USER1(ID),
    FOREIGN KEY(ONUM) REFERENCES BUY(ONUM));
    
insert into review values('1','park','10002','20230412','게임이 재밋어요',5	);
insert into review values('2','son','10004','20230412','배송이 정말빨라요',5	);
 
 
select * from review;
alter table notice add cnt NUMBER;
update notice set cnt=0;
alter table review drop column cnt;
select * from notice;
desc review;
commit;

select * from notice where idx=1;
int idx
String title
String content
String author
String file1;

update notice set file1='data/null';
commit;
select * from user1;
select * from user1 where id='kim' and pw='2345';



ID VARCHAR2(20) PRIMARY KEY,
PW VARCHAR2(100) NOT NULL,
NAME VARCHAR2(20) NOT NULL,
TEL VARCHAR2(13) NOT NULL,
ADDR VARCHAR2(100) ,
EMAIL VARCHAR2(100) ,
REGDATE DATE DEFAULT SYSDATE,POINT NUMBER);

alter table user1 modify point number default 0;
update user1 set point=0;
commit;

--날짜를 이용해 하려면
--DATE자료형을 문자열로 바꿔줘야한다 그명령어가 TO_CHAR
select * from user1 WHERE TO_CHAR(regdate,'YYYYMMDD')>'20230506';
delete user1 where  TO_CHAR(regdate,'YYYYMMDD')>'20230506';
commit;

select * from user1;

update user1 set pw='9dpm/dDKFdb07DlP17Bk0PuLo0XaMxBkcIiUibgAq+8MNG3AC/F5YifpC9y5sApk4M2/yQ==' where id='admin';
update user1 set pw='vqwqWc9ZUK6VryTTlDP/HPcjzkEY+y8T/q+yP0Tl9niFzrs52o3ylHlVBq69GPQyvO3LAA==' where id='kim';
update user1 set pw='eGoBYZL/TPn9T6s3elp24jnxG/T4aJzC8kgnLp05iNR6aMS84hTpSrW2919qBn/Zaeev8w==' where id='lee';
update user1 set pw='U98egqJowA8rBU3kP2VeuL8hJpV3GB1d3sNtiivBmX3cOFtZFzppdw5c+jL2TtUYDAaPiw==' where id='park';
update user1 set pw='I4hooDC/P1Ov1w0t5M5/4RF/t0BbceTH8v/KlrynXaIUicNqgLlwKP/D7oaPSYFPguTtKQ==' where id='choi';
update user1 set pw='FKfyUAYyOKorl5cAHbGMav/7gsNqnoVmirFsqSwNb7aNVpCFv3CCYRvdRIl0ngbQEcF0Uw==' where id='son';

select * from notice;
select * from user1;


commit;

update notice set file1='data/null' where TO_CHAR(resdate,'YYYYMMDD')<'20230506';


create sequence noti_seq;
create table notice(
IDX NUMBER(38) primary key,
TITLE VARCHAR2(100) NOT NULL,
CONTENT VARCHAR2(1000),
AUTHOR VARCHAR2(20),
FILE1 VARCHAR2(200),
RESDATE DATE default sysdate,
CNT NUMBER default 0,foreign key(author) references user1(id));

commit;


create table category(ccode VARCHAR2(8) primary key, cgroup VARCHAR2(50) not null, cname VARCHAR2(50) not null );

insert into category values('0100','닌텐도스위치','ETC');
insert into category values('0101','닌텐도스위치','ACTION');
insert into category values('0102','닌텐도스위치','FPS');
insert into category values('0103','닌텐도스위치','RPG');

insert into category values('0200','플레이스테이션','ETC');
insert into category values('0201','플레이스테이션','ACTION');
insert into category values('0202','플레이스테이션','FPS');
insert into category values('0203','플레이스테이션','RPG');

insert into category values('0300','엑스박스','ETC');
insert into category values('0301','엑스박스','ACTION');
insert into category values('0302','엑스박스','FPS');
insert into category values('0303','엑스박스','RPG');

insert into category values('0401','기타','조이스틱');
insert into category values('0402','기타','주변기기');
insert into category values('0403','기타','완구');

insert into category values('0000','모든제품','-');

select * from category;
commit;

select * from notice;
select * from product;
alter table product add ccode varchar2(8) default 0100 NOT NULL;
alter table product add img varchar(1000);
alter table product add img2 varchar(1000);
update product set ccode='0101';
commit;
desc product;
update product set IMG='img/proimg/30001.jpg' where pcode='30001';
update product set IMG='img/proimg/30002.jpg' where pcode='30002';
update product set IMG='img/proimg/30003.jpg' where pcode='30003';
update product set IMG='img/proimg/30004.jpg' where pcode='30004';
update product set IMG='img/proimg/30005.jpg' where pcode='30005';
update product set IMG='img/proimg/30006.jpg' where pcode='30006';
select * from product where ccode like '01'||'%';
-- '*' MSSQL에서 사용됨

--CNAME이 키 안에들어있는것이 값 
-- 차, 바차,넘버,바차,넘버,바차,바차
create sequence pcode start with 30015;
select * from product;

insert into product values(pcode.nextval,'MLB 더 쇼 23',67800,'SIE 샌디에이고 스튜디오',50,'0200','','');
insert into product values(pcode.nextval,'원피스 오디세이',69800,'반다이남코 엔터테인먼트',21,'0201','','');

insert into product values(pcode.nextval,'권바(QANBA) Q1 조이스틱 블랙',79400,'SWITCH/PS3/PC',100,'0401','','');
insert into product values(pcode.nextval,'포켓몬 피카츄 마우스 패드',4990,'Dongguan Lingjie Electronics Technology',0,'0402','','');

select * from category;
select distinct cgroup from category;
commit;


--pname=?,price=?,pdesc=?,pamount=?,ccode=? where pcode=? ";
update product set pname=?,price=?,pdesc=?,pamount=?,ccode=?,img=? where pcode=?
*/
--substr(변수명,어디부터,몇글자)
--ex)
--select 

select * from product;
--update product set IMG2='img/proimg/null';
commit;

select *  from category;
select distinct substr(ccode,1,2) as ct, cgroup from category group by substr(ccode,1,2), cgroup order by ct;
select ccode as frcate, cname from category where ccode like '01'||'%' order by frcate;
select distinct substr(ccode,1,2) as ct, cgroup from category group by substr(ccode,1,2), cgroup order by ct;
select pcode from  (select * from product order by pcode desc) where rownum=1;
select pcode from (select * from product where cate='0101' order by pcode desc) where rownum = 1;

select * from basket;

select * from product;
update product set ccode='0101';
desc product;
commit;
--alter table product drop column ccode;
--alter table product drop column img;
--alter table product drop column img2;
--alter table product add ccode varchar2(8) default '0100' NOT NULL;
--alter table product add ccode varchar2(8);
--alter table product add img varchar2(1000);
--alter table product add img2 varchar2(1000);

/*
select * from basket;
desc basket;
select a.bnum as bnum, a.pcode as pcode , a.bamount as  bamount, a.price as price,  from basket a , user1 b where a.id=b.id;

select a.bnum as bnum, a.pcode as pcode ,c.pname as pname , 
        a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img
        from basket a , product c where a.pcode=c.pcode and id='kim' ;
select a.bnum as bnum, a.pcode as pcode ,a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a , product c where a.pcode=c.pcode and id='kim';
select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id and a.id='kim';

select * from (select * from basket order by bnum desc) where rownum=1;

select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,c.pamount as pamount, a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id;

select * from basket where id='kim' and pcode='30006';
update basket set bamount=bamount+3,price=price*(bamount+3) where bnum='50007'; --74800
commit;

select a.bnum as bnum, a.id as id ,
    a.pcode as pcode ,c.pname as pname ,c.pamount as pamount , a.bamount as  bamount, (a.bamount*c.price) as price, 
    c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id and a.id='kim';
    
    update basket set bamount=2 where bnum='50010';
    
alter table basket drop column price;

alter table basket add price NUMBER default 0 NOT NULL;
*/

select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,c.pamount as pamount, a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id;

select sum(a.bamount*c.price)as sumprice from  basket a,user1 b ,product c where a.id=b.id and a.pcode=c.pcode and a.id='kim';
select * from user1;
select * from user1 where id='kim';
select * from buy order by onum;
select * from product;
desc buy;
select * from payment;

insert into buy values(?,?,?,?,?,?,?,'','',default,default);
select * from  (select * from buy order by onum desc) where rownum=1;
insert into buy values('10006','kim','30004','01022222222','경기도 고양시 장기동',1,50000,' ',' ',default,default);
commit;

--delete from buy where onum='10007';
--delete from buy where onum='10008';
--delete from buy where onum='10009';
--delete from buy where onum='10010';
--delete from buy where onum='10011';
--delete from buy where onum='10012';
--delete from buy where onum='10013';
--delete from buy where onum='10014';
--delete from buy where onum='10015';
--delete from buy where onum='10016';
--delete from payment where pnum='20006';
--delete from buy where  TO_CHAR(odate,'YYYYMMDD')>'20230514';
select * from  (select * from payment order by pnum desc) where rownum=1;
insert into payment values(?,?,?,?,?,?,default);

select * from product;
--update product set pamount= pamount-? where pcode=?

select * from buy a , product b where a.pcode=b.pcode and id='kim';

select * from buy a , product b where a.pcode=b.pcode and id='kim';