-- 작업 데이터베이스 변경
USE market_db;

-- (1) 박지성이 구매한 도서의 출판사 수 ( customer, orders, book )
SELECT b.*-- count(DISTINCT b.publisher) 출판사수
FROM book b
INNER JOIN orders o ON b.bookid = o.bookid 
INNER JOIN customer c ON o.custid = c.custid 
WHERE c.name = '박지성';

SELECT count(DISTINCT b.publisher) "박지성 고객의 구매 도서 출판사 수"
FROM book b, orders o, customer c
WHERE b.bookid = o.bookid AND 
	  o.custid = c.custid AND 
      c.name = '박지성';

-- (2) 박지성이 구매한 도서의 이름, 가격, 정가와 판매가격의 차이 ( customer, orders, book )
SELECT b.bookname, b.price, (b.price - o.saleprice) discount
FROM book b
INNER JOIN orders o ON b.bookid = o.bookid 
INNER JOIN customer c ON o.custid = c.custid 
WHERE c.name = '박지성';

-- (3) 박지성이 구매하지 않은 도서의 이름 ( customer, orders, book )
select count(*) from book;	-- 10 ( 총도서개수 )
select count(distinct bookid) from orders; -- 8 (판매도서개수)
-- 위 두 조회 결과로 볼 때 판매되지 않은 도서가 2권입니다.
-- 2권의 도서는 박지성 고객이 주문하지 않은 도서에 포함되어야 합니다.

SELECT DISTINCT b.*
FROM book b
LEFT OUTER JOIN orders o ON b.bookid = o.bookid
WHERE o.custid <> ( SELECT custid 
					FROM customer
                    WHERE name = '박지성') -- 박지성 고객이 주문하지 않은 도서 조건
	  OR
      o.custid IS NULL; -- 주문 실적이 없는 도서 조건 (NULL은 직접 비교 불가)

-- (4) 주문하지 않은 고객의 이름 ( customer, orders )
SELECT *
FROM customer c
WHERE c.custid NOT IN ( SELECT DISTINCT custid 
						FROM orders );

-- (5) 고객의 이름과 고객별 구매액 ( customer, orders )
SELECT c.name 고객이름, 
	   SUM(o.saleprice) 구매액1, IFNULL(SUM(o.saleprice), 0) 구매액2
FROM customer c
-- INNER JOIN orders o ON c.custid = o.custid
LEFT OUTER JOIN orders o ON c.custid = o.custid
GROUP BY c.name;

-- (6) 고객의 이름과 고객이 구매한 도서 목록 ( customer, orders, book )
SELECT c.name, b.bookname, b.publisher, b.price
FROM customer c
INNER JOIN orders o ON c.custid = o.custid
INNER JOIN book b ON o.bookid = b.bookid
ORDER BY c.name;

-- (7) 도서의 가격(Book 테이블)과 판매가격(Orders 테이블)의 차이가 가장 많은 주문 ( book, orders )
SELECT MAX(b.price - o.saleprice) 최대할인액
FROM book b
INNER JOIN orders o ON b.bookid = o.bookid;

SELECT o.*, b.price
FROM orders o
INNER JOIN book b ON o.bookid = b.bookid
WHERE b.price - o.saleprice = ( SELECT MAX(b.price - o.saleprice)
								FROM book b
								INNER JOIN orders o 
                                ON b.bookid = o.bookid );

-- (8) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름 ( orders, customer )
SELECT AVG(o.saleprice) FROM orders o; -- 도서의 판매액 평균

SELECT c.*
FROM customer c
INNER JOIN orders o ON c.custid = o.custid
-- WHERE AVG(o.saleprice) > ( SELECT AVG(o.saleprice) FROM orders o )
GROUP BY c.name
HAVING AVG(o.saleprice) > ( SELECT AVG(o.saleprice) FROM orders o );

