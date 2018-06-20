select
    T1.*
from SYS.ALL_CONSTRAINTS T1
where 1 = 1
    and T1.OWNER in ('TEST')
--    and T1.CONSTRAINT_TYPE in ('P')
order by
    T1.OWNER
    , T1.CONSTRAINT_NAME
;
