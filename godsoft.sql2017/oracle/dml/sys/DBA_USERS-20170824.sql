/*
�ۼ���: ������Ʈ �̹���<dlqorgod@naver.com>
�ۼ���: 2017--08-24
*/

select
    DBA_USERS.*
from DBA_USERS
where ACCOUNT_STATUS = 'OPEN'
order by
    USERNAME
;
