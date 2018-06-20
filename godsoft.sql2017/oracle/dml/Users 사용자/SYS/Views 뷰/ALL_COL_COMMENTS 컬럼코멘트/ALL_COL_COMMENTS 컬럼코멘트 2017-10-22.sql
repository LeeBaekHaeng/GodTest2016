select
    T1.OWNER
    , T1.TABLE_NAME
    , T1.COLUMN_NAME
    , T1.COMMENTS
from SYS.ALL_COL_COMMENTS T1
left outer join SYS.ALL_TAB_COLS T2 on T2.OWNER = T1.OWNER and T2.TABLE_NAME = T1.TABLE_NAME and T2.COLUMN_NAME = T1.COLUMN_NAME
where 1 = 1
    and T1.OWNER in (
        'TEST'
        , 'TEST2'
    )
order by
    T1.OWNER
    , T1.TABLE_NAME
    , T2.COLUMN_ID
;

