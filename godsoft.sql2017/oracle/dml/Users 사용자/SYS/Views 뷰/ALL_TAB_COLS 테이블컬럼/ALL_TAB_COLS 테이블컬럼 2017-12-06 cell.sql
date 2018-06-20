select
    'cell = row.createCell(column++);\r\ncell.setCellValue("' || COLUMN_NAME || '");\r\n\r\n'
from SYS.ALL_TAB_COLS /* 테이블컬럼 */
where 1 = 1
--    and ALL_TAB_COLS.OWNER in (
--        'TEST'
--        , 'TEST2'
--    )
    and ALL_TAB_COLS.OWNER = 'SYS'
    and ALL_TAB_COLS.TABLE_NAME = 'ALL_TAB_COLS'
order by
--    ALL_TAB_COLS.OWNER, ALL_TAB_COLS.TABLE_NAME, ALL_TAB_COLS.COLUMN_ID
    ALL_TAB_COLS.COLUMN_ID
;

