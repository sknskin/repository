-- 작업 데이터베이스 변경
USE market_db;

-- 
SELECT * FROM buy;
SELECT * FROM member;
-- member, buy 테이블 각각에서 필요한 데이터를 조회하고 결합하는 방법

-- 1. 고객아이디, 고객이름, 주문데이터 전부를 조회하세요
SELECT buy.mem_id, mem_name, prod_name, price, amount
FROM member
INNER JOIN buy
ON member.mem_id = buy.mem_id;

SELECT b.mem_id, m.mem_name, b.prod_name, b.price, b.amount
FROM member m
INNER JOIN buy b
ON m.mem_id = b.mem_id;

SELECT b.mem_id, m.mem_name, b.prod_name, b.price, b.amount
FROM member m, buy b
WHERE m.mem_id = b.mem_id;

-- 2. 모든 고객의 고객아이디, 고객이름, 주문액 합계
SELECT COUNT(DISTINCT mem_id) FROM member;
SELECT COUNT(DISTINCT mem_id) FROM buy;

SELECT m.mem_id, m.mem_name, SUM(b.price * b.amount) 주문총액
FROM member m
-- INNER JOIN buy b -- 주문한 고객만 조회
LEFT OUTER JOIN buy b -- 주문하지 않은 고객을 포함해서 조회
ON m.mem_id = b.mem_id
GROUP BY m.mem_id
ORDER BY SUM(b.price * b.amount) DESC;

SELECT m.mem_id, m.mem_name, SUM(b.price * b.amount) 주문총액
FROM buy b
RIGHT OUTER JOIN member m -- 주문하지 않은 고객을 포함해서 조회
ON m.mem_id = b.mem_id
GROUP BY m.mem_id
ORDER BY SUM(b.price * b.amount) DESC;

-- 3. customer, book, orders 테이블 활용
--    고객별 (고객이름 같이 조회) 구매액 합계
SELECT c.custid, c.name, SUM(o.saleprice)
FROM customer c, orders o
WHERE c.custid = o.custid
GROUP BY c.custid
ORDER BY SUM(o.saleprice) DESC;

SELECT c.custid, c.name, SUM(o.saleprice)
FROM customer c
INNER JOIN orders o
ON c.custid = o.custid
GROUP BY c.custid
ORDER BY SUM(o.saleprice) DESC;

-- 고객아이디, 고객이름, 도서명, 주문 정보 ( customer, book, orders )
SELECT c.custid, c.name, b.bookname, o.saleprice, o.orderdate
FROM customer c, orders o, book b
WHERE c.custid = o.custid AND o.bookid = b.bookid
ORDER BY c.custid;

SELECT c.custid, c.name, b.bookname, o.saleprice, o.orderdate
FROM customer c
INNER JOIN orders o ON c.custid = o.custid
INNER JOIN book b   ON o.bookid = b.bookid
ORDER BY c.custid;






