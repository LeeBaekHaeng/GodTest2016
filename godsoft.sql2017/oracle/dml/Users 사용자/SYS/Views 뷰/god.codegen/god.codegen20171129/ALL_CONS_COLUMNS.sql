select
    T1.*
from SYS.ALL_CONS_COLUMNS T1
where 1 = 1
--    and T1.OWNER in ('TEST')
    and T1.OWNER in ('SYS')
    and T1.TABLE_NAME in (
        'ALL_COL_COMMENTS'
        , 'ALL_CONSTRAINTS'
        , 'ALL_CONS_COLUMNS'
        , 'ALL_OBJECTS'
        , 'ALL_TABLES'
        , 'ALL_TAB_COLS'
        , 'ALL_TAB_COMMENTS'
    )
order by
    T1.OWNER
    , T1.CONSTRAINT_NAME
    , T1.POSITION
;

