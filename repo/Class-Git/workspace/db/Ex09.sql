USE market_db;

-- 1. member 테이블에 데이터 삽입
SELECT * FROM member; -- 테이블 구조 및 데이터 확인
DESC member; -- 테이블 구조 확인

INSERT INTO member 
(mem_id,mem_name,mem_number,addr,phone1,phone2,height,debut_date) -- 생략가능
VALUES ('ASP', '에스파', 4, '서울', '010', '25439963', 170, '2020-11-17');
SELECT * FROM member;

INSERT INTO member
VALUES ('ASP2', '에스파2', 4, '서울', '010', '25439963', 170, '2020-11-17');
SELECT * FROM member;

INSERT INTO member
(mem_id,mem_name,mem_number,addr) -- 선택적 컬럼인 경우 생략 불가능
VALUES ('ASP3', '에스파3', 4, '서울');
SELECT * FROM member;

-- 2. buy 테이블에 데이터 삽입 ( auto increment test )
select * from buy;
desc buy;

INSERT INTO buy (mem_id, prod_name, price, amount) -- 자동 증가 컬럼은 생략 
VALUES ('ASP', '혼공자바', 25000, 4);
SELECT * FROM buy;

-- 3. buy 테이블에 데이터 삽입 ( insert multi rows )
INSERT INTO buy (mem_id, prod_name, price, amount)
VALUES ('ASP', '자바의정석', 25000, 4),
       ('ASP', '리액트', 20000, 4),
       ('ASP', '반응형웹', 15000, 4);
SELECT * FROM buy;

-- 4. member 테이블 데이터 수정
select * from member;

UPDATE member
SET addr = '경기', height = 172
WHERE mem_id = 'ASP';
SELECT * FROM member;

-- 5. member 테이블의 데이터 삭제
DELETE FROM member 
WHERE mem_id = 'ASP3';
SELECT * FROM member;

DELETE FROM member 
WHERE mem_id = 'ASP'; -- 다른 테이블에서 참조하는 데이터는 삭제 불가능 ( 다른 테이블의 데이터를 삭제한 후에 삭제 가능 )

SELECT * FROM buy;
DELETE FROM buy
WHERE mem_id = 'ASP'; -- 참조하는 테이블의 데이터를 먼저 지우고
DELETE FROM member 
WHERE mem_id = 'ASP'; -- 데이터 삭제 가능










