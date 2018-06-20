select
    COLUMN_NAME
    , '    , ' || COLUMN_NAME as select1
    , '    , T1.' || COLUMN_NAME as select2
from (
select
    ALL_TAB_COLS.*
from SYS.ALL_TAB_COLS /* 테이블컬럼 */
where 1 = 1
    and ALL_TAB_COLS.OWNER = 'SYS'
--    and ALL_TAB_COLS.TABLE_NAME = 'ALL_TABLES'
--    and ALL_TAB_COLS.TABLE_NAME = 'ALL_TAB_COMMENTS'
--    and ALL_TAB_COLS.TABLE_NAME = 'ALL_TAB_COLS'
    and ALL_TAB_COLS.TABLE_NAME = 'ALL_COL_COMMENTS'
order by
    ALL_TAB_COLS.OWNER, ALL_TAB_COLS.TABLE_NAME, ALL_TAB_COLS.COLUMN_ID
)
;
