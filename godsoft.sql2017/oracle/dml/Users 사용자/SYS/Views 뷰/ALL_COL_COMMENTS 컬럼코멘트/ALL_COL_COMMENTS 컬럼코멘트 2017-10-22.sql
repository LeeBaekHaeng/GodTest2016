select
    ALL_COL_COMMENTS.*
from SYS.ALL_COL_COMMENTS /* 컬럼코멘트 */
where 1 = 1
    and ALL_COL_COMMENTS.OWNER in (
        'TEST'
        , 'TEST2'
    )
order by
    ALL_COL_COMMENTS.OWNER, ALL_COL_COMMENTS.TABLE_NAME
;
