select
    ALL_TAB_COMMENTS.*
from SYS.ALL_TAB_COMMENTS /* ���̺��ڸ�Ʈ */
where 1 = 1
    and ALL_TAB_COMMENTS.OWNER = 'TEST'
order by
    ALL_TAB_COMMENTS.OWNER, ALL_TAB_COMMENTS.TABLE_NAME
;
