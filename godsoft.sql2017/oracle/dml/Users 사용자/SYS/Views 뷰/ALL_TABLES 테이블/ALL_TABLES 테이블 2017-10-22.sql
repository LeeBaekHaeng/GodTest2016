select
    ALL_TABLES.*
from SYS.ALL_TABLES /* Å×ÀÌ÷ם */
where 1 = 1
    and ALL_TABLES.OWNER in (
        'TEST'
        , 'TEST2'
    )
order by
    ALL_TABLES.OWNER, ALL_TABLES.TABLE_NAME
;
