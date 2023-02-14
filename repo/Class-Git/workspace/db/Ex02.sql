-- 1. 작업 데이터베이스 선택

USE market_db; -- 이 명령 실행이후 모든 명령은 market_db를 대상으로 합니다.

-- 2. member 테이블의 mem_id, mem_name, member_number 데이터 조회
SELECT mem_id, mem_name, mem_number
FROM member;

-- 3. member 테이블의 모든 컬럼 데이터 조회
SELECT 
	mem_id, mem_name, mem_number, addr, phone1, phone2, height, debut_date
FROM member;

SELECT * -- * : 모든 컬럼
FROM member;

-- 4. 조회 컬럼을 별칭으로 조회
SELECT 
	mem_id AS '아이디', mem_name AS '이름', addr AS '주소', 
    phone1 AS '지역번호', phone2 AS '전화번호'
FROM member;

SELECT 
	mem_id '아이디', mem_name '이름', addr '주소', 
    phone1 '지역번호', phone2 '전화번호'
FROM member;

-- 4. member 테이블에서 addr 컬럼 조회 ( 회원 지역 분포 )
SELECT addr 
FROM member;

SELECT DISTINCT addr -- DISTINCT : 중복 제거
FROM member; 

-- 5. member 테이블에서 서울에 사는 회원 조회
SELECT * 
FROM member
WHERE addr = '서울'; -- SQL에서 작은따옴표는 문자열 표시 (큰따옴표는 다른 용도)

-- 6. member 테이블에서 키가 165 이상인 회원 조회
SELECT *
FROM member
WHERE height >= 165;

-- 7. 서울에 살거나 키가 165 이상의 회원 조회
SELECT *
FROM member
WHERE addr = '서울' OR height >= 165; -- AND : &&, OR : ||

-- 8. 키 165 이상 167 이하인 회원 조회
SELECT *
FROM member
WHERE height >= 165 AND height <= 167; -- AND : &&, OR : ||

SELECT *
FROM member
WHERE height BETWEEN 165 AND 167; -- BETWEEN ~ AND ~ 

-- 9. 주소가 '경기' 또는 '전남'인 member 조회
SELECT *
FROM member
WHERE addr = '경기' OR addr = '전남';

SELECT *
FROM member
WHERE addr IN ('경기', '전남');

-- 10. 이름에 '핑크'가 포함된 회원 조회
SELECT *
FROM member
WHERE mem_name LIKE '%핑크%'; -- %는 0개 이상의 모든 문자 의미 '핫핑크', '핑크래빗', ...

-- 11. 2014년 ~ 2015년 사이에 데뷔한 멤버
SELECT *
FROM member
WHERE debut_date >= '2014-01-01' AND debut_date <= '2015-12-31';

SELECT *
FROM member
WHERE debut_date BETWEEN '2014-01-01' AND '2015-12-31';

-- 12. 데뷔 년도 순으로 회원 조회
SELECT *
FROM member
ORDER BY debut_date; -- 오름차순 정렬 기본 적용

SELECT *
FROM member
ORDER BY debut_date DESC; -- DESC : 내림차순, ASC : 오름차순

-- 13. 지역순으로 조회하되 같은 지역인 경우 데뷔일자 순으로 조회
SELECT *
FROM member
ORDER BY addr DESC, debut_date ASC;

-- 14. 데뷔일자가 빠른 순으로 5명의 회원 조회
SELECT *
FROM member
ORDER BY debut_date ASC
-- LIMIT 3; -- 처음부터 3건의 결과 조회
LIMIT 3, 3; -- 3번째부터 3건의 결과 조회 (순서는 0부터)
