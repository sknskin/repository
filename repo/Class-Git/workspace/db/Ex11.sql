USE market_db;

-- 1. tbl_member
SELECT NOW(); -- 현재 시간을 반환하는 함수
DROP TABLE IF EXISTS tbl_member;
CREATE TABLE tbl_member
(
   memberid VARCHAR(20) PRIMARY KEY,
   passwd VARCHAR(100) NOT NULL,
   email VARCHAR(50) NOT NULL UNIQUE,
   usertype VARCHAR(10) DEFAULT ('user'), -- ( 'user' or 'amdin' )
   regdate DATETIME DEFAULT ( NOW() ),
   active BOOLEAN DEFAULT (TRUE)
);

-- 2. tbl_board
DROP TABLE IF EXISTS tbl_board;
CREATE TABLE tbl_board
(
   boardno INT PRIMARY KEY AUTO_INCREMENT,
   writer VARCHAR(20) NOT NULL,
   title VARCHAR(100) NOT NULL,
   content VARCHAR(10000) NOT NULL,
   writedate DATETIME DEFAULT ( NOW() ),
   modifydate DATETIME DEFAULT ( NOW() ),
   readcount INT DEFAULT (0), 
   CONSTRAINT fk_member_board FOREIGN KEY (writer) REFERENCES tbl_member(memberid) 
);

-- 3. tbl_comment
DROP TABLE IF EXISTS tbl_comment;
CREATE TABLE tbl_comment
(
   commentno INT PRIMARY KEY AUTO_INCREMENT,
   writer VARCHAR(20) NOT NULL,
   boardno INT NOT NULL,
   content VARCHAR(500) NOT NULL,
   writedate DATETIME DEFAULT ( NOW() ),
   modifydate DATETIME DEFAULT ( NOW() ),
   CONSTRAINT fk_board_comment FOREIGN KEY (boardno) REFERENCES tbl_board(boardno),
   CONSTRAINT fk_member_comment FOREIGN KEY (writer) REFERENCES tbl_member(memberid)
);

-- 4. tbl_member 수정
ALTER TABLE tbl_member
ADD COLUMN usergrade INT NULL,
CHANGE COLUMN regdate joindate DATETIME DEFAULT ( NOW() );

-- 5. tbl_board 수정
ALTER TABLE tbl_board
ADD COLUMN category VARCHAR (50) NOT NULL;

-- 6. tbl_member 테이블에 데이터 삽입

-- INSERT INTO tbl_member
-- VALUES ('iamuserone', 'iamuserone', 'iamuserone@example.com', 'user', now(), true);
INSERT INTO tbl_member (memberid, passwd, email) -- 나머지 컬럼은 기본값 사용
VALUES ('iamuserone', 'iamuserone', 'iamuserone@example.com');

INSERT INTO tbl_member (memberid, passwd, email) -- 나머지 컬럼은 기본값 사용
VALUES ('iamusertwo', 'iamusertwo', 'iamusertwo@example.com');

INSERT INTO tbl_member (memberid, passwd, email) -- 나머지 컬럼은 기본값 사용
VALUES ('iamuserthree', 'iamuserthree', 'iamuserthree@example.com');

INSERT INTO tbl_member (memberid, passwd, email, usertype) -- 나머지 컬럼은 기본값 사용
VALUES ('iamadminone', 'iamadminone', 'iamadminone@example.com', 'admin');

SELECT * FROM tbl_member;

-- 7. tbl_board 테이블에 데이터 삽입

INSERT INTO tbl_board (writer, title, content, category) -- 나머지 컬럼은 기본값 사용
VALUES ('iamuserone', '게시글 연습 1', '게시글 작성 연습입니다.', 'free');

INSERT INTO tbl_board (writer, title, content, category)
VALUES ('iamuserone', '게시글 연습 2', '게시글 작성 연습입니다.', 'notice');

INSERT INTO tbl_board (writer, title, content, category)
VALUES ('iamuserone', '게시글 연습 3', '게시글 작성 연습입니다.', 'free');

SELECT * FROM tbl_board; 

-- 8. tbl_comment 테이블에 데이터 삽입
INSERT INTO tbl_comment (writer, boardno, content)
VALUES ('iamusertwo', 1, '게시글 1에 대한 댓글');

INSERT INTO tbl_comment (writer, boardno, content)
VALUES ('iamusertwo', 2, '게시글 2에 대한 댓글');

INSERT INTO tbl_comment (writer, boardno, content)
VALUES ('iamusertwo', 3, '게시글 3에 대한 댓글');

SELECT * FROM tbl_comment;

-- 9. 데이터 수정

UPDATE tbl_member
SET passwd = 'Pa$$word',  email = 'iamuserone@naver.com'
WHERE memberid = 'iamuserone';

SELECT * FROM tbl_member;

UPDATE tbl_board
SET title = '수정된 게시글 1',  modifydate = now()
WHERE boardno = 1;

SELECT * FROM tbl_board;

-- 10. 데이터 삭제

DELETE FROM tbl_member 
WHERE memberid = 'iamuserthree'; -- 이 데이터를 참조하고 있는 데이터가 있다면 삭제 불가능

SELECT * FROM tbl_member; 



   

