USE market_db;

-- 1. '박지성' 고객의 구매 총액
SELECT * FROM orders;
SELECT custid FROM customer WHERE name = '박지성'; -- 1번 확인
SELECT custid, SUM(saleprice) 구매총액 FROM orders WHERE custid = 1;

SELECT c.custid, SUM(o.saleprice)
FROM orders o
INNER JOIN customer c
ON o.custid = c.custid
WHERE c.name = '박지성';

SELECT custid, SUM(saleprice) 구매총액 
FROM orders 
WHERE custid = ( SELECT custid 
				 FROM customer 
                 WHERE name = '박지성' );
                 
-- 가장 비싼 도서의 이름을 조회 ( book 테이블 사용 )
SELECT MAX(price) FROM book; -- 35000 확인
SELECT * FROM book WHERE price = 35000;

SELECT * 
FROM book 
WHERE price = ( SELECT MAX(price) 
				FROM book );
                
-- 구매실적이 있는 고객 조회
SELECT DISTINCT custid FROM orders; -- 1, 2, 3, 4 확인
SELECT * FROM customer WHERE custid IN (1, 2, 3, 4);

SELECT * 
FROM customer 
WHERE custid IN ( SELECT DISTINCT custid 
				  FROM orders );
                  
-- 대한미디어 출판사에서 출간한 도서를 구매한 고객 조회 ( book, orders, customer )
SELECT bookid FROM book WHERE publisher = '대한미디어'; -- 3, 4 확인
SELECT custid FROM orders WHERE bookid IN (3, 4); -- 1 확인
SELECT * FROM customer WHERE custid IN (1);

SELECT *
FROM customer 
WHERE custid IN ( SELECT custid 
				  FROM orders 
                  WHERE bookid IN ( SELECT bookid 
					  			    FROM book 
                                    WHERE publisher = '대한미디어') );



