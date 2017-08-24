/*
작성자: 갓소프트 이백행<dlqorgod@naver.com>
작성일: 2017--08-24
*/

select
    DBA_USERS.*
from DBA_USERS
where ACCOUNT_STATUS = 'OPEN'
order by
    USERNAME
;
