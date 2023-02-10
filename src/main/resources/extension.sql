--테이블 보기
SELECT * FROM Extension

--테이블 데이터 삭제
DELETE FROM Extension ;

--테이블 삭제
DROP TABLE Extension

--고정 확장자
INSERT INTO Extension(extension,defaultCheck) VALUES('bat',true);
INSERT INTO Extension(extension,defaultCheck) VALUES('cmd',true);
INSERT INTO Extension(extension,defaultCheck) VALUES('cpl',true);
INSERT INTO Extension(extension,defaultCheck) VALUES('exe',true);
INSERT INTO Extension(extension,defaultCheck) VALUES('scr',true);
INSERT INTO Extension(extension,defaultCheck) VALUES('js',true);

--테이블 생성
CREATE TABLE Extension(
	extension		VARCHAR(20)		NOT NULL,
	defaultCheck	BOOLEAN			NOT NULL DEFAULT FALSE,
	checkBox		BOOLEAN			NULL
)

SELECT COUNT(*) FROM Extension WHERE defaultCheck = false;