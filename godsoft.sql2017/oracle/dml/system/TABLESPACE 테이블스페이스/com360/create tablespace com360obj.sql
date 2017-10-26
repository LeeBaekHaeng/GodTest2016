/*
테이블스페이스 생성
com360obj
C:\app\LeeBaekHaeng\oradata\orcl2
*/

/*
데이터 테이블스페이스 생성
*/

CREATE TABLESPACE ts_dcom360obj DATAFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_dcom360obj01.dbf' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_dcom360obj02.dbf' SIZE 1G AUTOEXTEND OFF
;

/*
인덱스 테이블스페이스 생성
*/

CREATE TABLESPACE ts_icom360obj DATAFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_icom360obj01.DBF' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_icom360obj02.DBF' SIZE 1G AUTOEXTEND OFF
;

/*
임시 테이블스페이스 생성
*/

CREATE TEMPORARY TABLESPACE ts_tcom360obj TEMPFILE 
	'C:\app\LeeBaekHaeng\oradata\orcl2\ts_tcom360obj01.DBF' SIZE 1G AUTOEXTEND OFF
	, 'C:\app\LeeBaekHaeng\oradata\orcl2\ts_tcom360obj02.DBF' SIZE 1G AUTOEXTEND OFF
;
