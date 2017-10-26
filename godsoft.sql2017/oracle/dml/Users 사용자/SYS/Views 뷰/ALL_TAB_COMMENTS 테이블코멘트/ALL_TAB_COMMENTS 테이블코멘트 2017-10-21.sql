select
    ALL_TAB_COMMENTS.*
from SYS.ALL_TAB_COMMENTS /* 테이블코멘트 */
where 1 = 1
    and ALL_TAB_COMMENTS.OWNER = 'TEST'
order by
    ALL_TAB_COMMENTS.OWNER, ALL_TAB_COMMENTS.TABLE_NAME
;
