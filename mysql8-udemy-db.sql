-- 회원 테이블 생성
CREATE TABLE TBL_MEMBER
(
    ID          VARCHAR(20) NOT NULL COMMENT '아이디',
    PASSWORD    VARCHAR(50) NOT NULL COMMENT '비밀번호',
    USERNAME    VARCHAR(10) NOT NULL COMMENT '이름',
    EMAIL       VARCHAR(100) NULL COMMENT '이메일',
    REG_DATE    TIMESTAMP DEFAULT NOW() COMMENT '등록일',
    UPDATE_DATE TIMESTAMP DEFAULT NOW() COMMENT '수정일',
    PRIMARY KEY (ID)
) COMMENT '회원 테이블';

COMMIT;

-- 게시글 테이블 생성
CREATE TABLE TBL_BOARD_POST
(
    POST_ID  INT          NOT NULL AUTO_INCREMENT COMMENT '게시글 고유 아이디',
    SUBJECT  VARCHAR(200) NOT NULL COMMENT '제목',
    CONTENT  TEXT NULL COMMENT '내용',
    WRITER   VARCHAR(30)  NOT NULL COMMENT '작성자',
    REG_DATE TIMESTAMP    NOT NULL DEFAULT NOW() COMMENT '작성일',
    HIT      INT                   DEFAULT 0 COMMENT '조회수',
    PRIMARY KEY (POST_ID)
) COMMENT '게시글 테이블';

-- 테이블 ENGINE TYPE 확인
SELECT TABLE_SCHEMA,
       TABLE_NAME,
       ENGINE
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'udemy';

-- 게시글 Dump 데이터 생성
INSERT INTO TBL_BOARD_POST (SUBJECT, CONTENT, WRITER)
        (SELECT SUBJECT, CONTENT, WRITER FROM TBL_BOARD_POST);

-- 게시글 수 조회
SELECT COUNT(*) AS CNT
FROM TBL_BOARD_POST
WHERE 1 = 1;

-- 게시글 목록 조회
SELECT *
FROM TBL_BOARD_POST
WHERE 1 = 1
ORDER BY REG_DATE DESC LIMIT 0, 10;

SELECT POST_ID, SUBJECT, CONTENT, WRITER, REG_DATE, HIT
FROM TBL_BOARD_POST
WHERE 1 = 1
ORDER BY POST_ID DESC, REG_DATE DESC LIMIT 0, 10;

-- 게시글 댓글 테이블 생성
CREATE TABLE TBL_BOARD_COMMENT
(
    CMT_ID      INT           NOT NULL AUTO_INCREMENT COMMENT '댓글 고유 아이디',
    POST_ID     INT           NOT NULL DEFAULT 0 COMMENT '게시글 고유 아이디',
    CMT_CONTENT VARCHAR(1000) NOT NULL COMMENT '내용',
    WRITER      VARCHAR(30)   NOT NULL COMMENT '작성자',
    REG_DATE    TIMESTAMP     NOT NULL DEFAULT NOW() COMMENT '작성일',
    UPDATE_DATE TIMESTAMP     NOT NULL DEFAULT NOW() COMMENT '수정일',
    PRIMARY KEY (CMT_ID)
) COMMENT '게시글 댓글 테이블';

COMMIT;

-- 게시글 댓글 테이블 외래키 추가
-- ALTER TABLE [테이블명] CONSTRAINT [제약 조건 명칭] FOREIGN KEY (해당 테이블에서 외랜키로 지정할 컬럼) REFERENCES [참조할 테이블명](참조할 테이블에서 외래키로 참조할 컬럼명)
ALTER TABLE TBL_BOARD_COMMENT
    ADD CONSTRAINT FK_POST_ID FOREIGN KEY (POST_ID) REFERENCES TBL_BOARD_POST (POST_ID);
COMMIT;