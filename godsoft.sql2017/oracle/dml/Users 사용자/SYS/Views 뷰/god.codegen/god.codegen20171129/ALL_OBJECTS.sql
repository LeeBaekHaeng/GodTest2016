select
    T1.*
from SYS.ALL_OBJECTS T1
where 1 = 1
--    and T1.OBJECT_TYPE in ('TABLE', 'VIEW', 'SYNONYM')
    and T1.OWNER in ('SYS')
    and T1.OBJECT_NAME in (
        'ALL_COL_COMMENTS'
        , 'ALL_CONSTRAINTS'
        , 'ALL_CONS_COLUMNS'
        , 'ALL_OBJECTS'
        , 'ALL_TABLES'
        , 'ALL_TAB_COLS'
        , 'ALL_TAB_COMMENTS'
    )
order by
    T1.OBJECT_ID
;

select * from DBA_OBJECTS
;

