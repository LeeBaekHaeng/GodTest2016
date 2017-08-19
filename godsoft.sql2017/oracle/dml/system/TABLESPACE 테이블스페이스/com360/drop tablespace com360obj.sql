/*
테이블스페이스 삭제
com360obj
*/

/*
데이터 테이블스페이스 삭제
*/
DROP TABLESPACE ts_dcom360obj INCLUDING CONTENTS AND DATAFILES;

/*
인덱스 테이블스페이스 삭제
*/
DROP TABLESPACE ts_icom360obj INCLUDING CONTENTS AND DATAFILES;

/*
임시 테이블스페이스 삭제
*/
DROP TABLESPACE ts_tcom360obj INCLUDING CONTENTS AND DATAFILES;
