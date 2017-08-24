select
    DBA_USERS.*
from DBA_USERS
where ACCOUNT_STATUS = 'OPEN'
order by
    USERNAME
;
