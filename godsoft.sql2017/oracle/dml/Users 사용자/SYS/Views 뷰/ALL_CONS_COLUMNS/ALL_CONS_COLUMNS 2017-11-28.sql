select
    T1.*
from SYS.ALL_CONS_COLUMNS T1
where 1 = 1
    and T1.OWNER in ('TEST')
order by
    T1.OWNER
    , T1.CONSTRAINT_NAME
    , T1.POSITION
;
