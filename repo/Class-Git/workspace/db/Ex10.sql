use market_db;

desc member;

-- 1. MySQL Workbench 도구를 사용해서 테이블 만들기

show tables; -- 테이블 목록 보기
desc member2;

-- 2. SQL로 테이블 만들기
DROP TABLE IF EXISTS member3; -- 테이블 삭제

CREATE TABLE member3 (
  mem_id VARCHAR(8) NOT NULL,  -- PRIMARY KEY, -- PK 지정 방법 1
  mem_name VARCHAR(10) NOT NULL,
  mem_number INT NOT NULL,
  addr VARCHAR(10) NOT NULL,
  phone1 VARCHAR(3) NULL,
  phone2 VARCHAR(8) NULL,
  height INT NULL,
  debut_date DATE NULL,
  -- PRIMARY KEY (mem_id) -- 기본키 지정 -- PK 지정 방법 2
  CONSTRAINT pk_member3_memid PRIMARY KEY (mem_id)
);

-- 4. 다른 테이블의 데이터를 가져와서 삽입
INSERT INTO member2
SELECT mem_id, mem_name, mem_number, addr, 
	   phone1, phone2, height, debut_date
FROM member;
SELECT * FROM member2;

-- 5. 테이블 만들기
CREATE TABLE buy3 (
  num INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  mem_id VARCHAR(20) NOT NULL,
  prod_name VARCHAR(20) NOT NULL,
  group_name VARCHAR(45) NULL,
  price INT NOT NULL,
  buycol VARCHAR(45) NOT NULL,
  -- FOREIGN KEY (mem_id) REFERENCES member3(mem_id));
  CONSTRAINT fk_member3_buy3 FOREIGN KEY (mem_id) REFERENCES member3(mem_id));
  
  
  
  -- 6. member3 테이블 수정
ALTER TABLE member3 
ADD COLUMN team_leader VARCHAR(45) NULL AFTER debut_date,
CHANGE COLUMN mem_name mem_name VARCHAR(20) NOT NULL ,
CHANGE COLUMN phone1 phone_area VARCHAR(3) NULL DEFAULT NULL ,
CHANGE COLUMN phone2 phone_unique VARCHAR(8) NULL DEFAULT NULL,
DROP COLUMN height;

select * from member3;
select * from buy3;

INSERT INTO member3 (mem_id, mem_name, mem_number, addr)
SELECT mem_id, mem_name, mem_number, addr 
FROM member;

select * from member3;

-- 7. 테이블 삭제

DROP TABLE buy3;


TRUNCATE TABLE member3; -- 테이블은 유지 + 데이터만 삭제
select * from member3;

DROP TABLE member3; -- 테이블 제거



