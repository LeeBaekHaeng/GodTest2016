select
    'Y' as "생성여부"
    , 'god.codegen.crud.sys.' || replace(lower(T1.TABLE_NAME), '_', '') as "패키지명"
--    , 'god.codegen.crud.sys.' || replace(lower('GOD_CODEGEN_CRUD_SYS_' || T1.TABLE_NAME), '_', '') as "패키지명"
    , 'GOD_CODEGEN_CRUD_SYS_' || T1.TABLE_NAME as "엔티티명"
    , T1.*
from SYS.ALL_TAB_COMMENTS T1
where 1 = 1
--    and ALL_TAB_COMMENTS.OWNER in (
--        'TEST'
--        , 'TEST2'
--    )
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
    T1.OWNER, T1.TABLE_NAME
;
