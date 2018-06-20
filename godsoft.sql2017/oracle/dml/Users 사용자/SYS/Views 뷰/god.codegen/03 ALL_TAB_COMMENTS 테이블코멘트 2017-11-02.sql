select
    ALL_TAB_COMMENTS.*
from SYS.ALL_TAB_COMMENTS /* 테이블코멘트 */
where 1 = 1
--    and ALL_TAB_COMMENTS.OWNER in (
--        'TEST'
--        , 'TEST2'
--    )
    and OWNER in ('SYS')
    and TABLE_NAME in (
        'ALL_COL_COMMENTS'
        , 'ALL_CONSTRAINTS'
        , 'ALL_CONS_COLUMNS'
        , 'ALL_OBJECTS'
        , 'ALL_TABLES'
        , 'ALL_TAB_COLS'
        , 'ALL_TAB_COMMENTS'
    )
order by
    ALL_TAB_COMMENTS.OWNER, ALL_TAB_COMMENTS.TABLE_NAME
;

