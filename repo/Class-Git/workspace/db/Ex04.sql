-- 작업 데이터베이스 변경
USE market_db;

SELECT * FROM member;

-- 1. 전체 회원수 조회
SELECT COUNT(mem_id) -- COUNT(컬럼이름) : 갯수를 조회하는 집계함수
FROM member;

-- 2. 회원 키의 평균, 최대, 최소 값 조회
SELECT AVG(height) 평균키, MAX(height) 최대키, MIN(height) 최소키
FROM member;

-- 3. 전체 멤버수 조회
SELECT SUM(mem_number) 전체멤버수
FROM member;

SELECT * FROM buy;

-- 4. 회원별 구매액 합계, 구매액 평균, 구매 건수 조회
SELECT mem_id, 
	   SUM(price * amount) 총구매액, 
       AVG(price * amount) 평균구매액, 
       COUNT(price) 구매건수
FROM buy
GROUP BY mem_id;

-- 4. 제품별 판매액 합계, 판매액 평균, 판매 건수 조회
SELECT prod_name, 
	   SUM(price * amount) 총판매액, 
       AVG(price * amount) 평균판매액, 
       COUNT(price) 판매건수
FROM buy
GROUP BY prod_name;

-- 5. 총구매액이 1000이상인 회원 조회
SELECT mem_id, 
	   SUM(price * amount) 총구매액
FROM buy
-- WHERE SUM(price * amount) >= 1000 -- 오류 : group 집계함수 where절 사용 X
GROUP BY mem_id
HAVING SUM(price * amount) >= 1000 -- HAVING : GROUP BY 다음에 조건 검사
ORDER BY SUM(price * amount);


-- ------------------------------------------------------

SELECT * FROM customer;
SELECT * FROM book;
SELECT * FROM orders;

-- 6. 고객이 주문한 도서의 총 판매액을 구하시오
SELECT SUM(saleprice) 총판매액
FROM orders;

-- 7. 2번 김연아 고객이 주문한 도서의 총 판매액을 구하시오.
SELECT custid
FROM customer 
WHERE name = '김연아';

SELECT SUM(saleprice) 총판매액
FROM orders
WHERE custid = 2;

-- 8. 고객이 주문한 도서의 총 판매액, 평균값, 최저가, 최고가를 구하시오
SELECT SUM(saleprice) 총판매액, AVG(saleprice) 평균값, 
	   MAX(saleprice) 최고가, MIN(saleprice) 최저가
FROM orders;

-- 9. 도서 판매 건수를 구하시오.
SELECT COUNT(*) 판매건수
FROM orders;

-- 10. 고객별로 주문한 도서의 총 수량과 총 판매액을 구하시오
SELECT custid, COUNT(saleprice) 총수량, SUM(saleprice) 총판매액
FROM orders
GROUP BY custid;

-- 11. 가격이 8,000원 이상인 도서를 구매한 고객에 대하여 
--     고객별 주문 도서의 총 수량을 구하시오. 단, 두 권 이상 구매한 고객만 구한다.
SELECT custid, COUNT(saleprice) 총구매수량
FROM orders
WHERE saleprice >= 8000
GROUP BY custid
HAVING COUNT(saleprice) >= 2;






