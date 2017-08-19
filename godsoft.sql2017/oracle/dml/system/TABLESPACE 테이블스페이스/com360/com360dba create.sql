/*
com360
C:\app\LeeBaekHaeng\oradata\orcl2
*/

/*
데이터 테이블스페이스 생성
*/

CREATE TABLESPACE ts_dcom360dba DATAFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_dcom360dba01.dbf' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_dcom360dba02.dbf' SIZE 1G AUTOEXTEND OFF
;

/*
인덱스 테이블스페이스 생성
*/

CREATE TABLESPACE ts_icom360dba DATAFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_icom360dba01.DBF' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_icom360dba02.DBF' SIZE 1G AUTOEXTEND OFF
;

/*
임시 테이블스페이스 생성
*/

CREATE TEMPORARY TABLESPACE ts_tcom360dba TEMPFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_tcom360dba01.DBF' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_tcom360dba02.DBF' SIZE 1G AUTOEXTEND OFF
;
