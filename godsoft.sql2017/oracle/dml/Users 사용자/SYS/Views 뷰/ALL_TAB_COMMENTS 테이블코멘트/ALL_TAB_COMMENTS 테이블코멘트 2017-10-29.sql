select
    'Y' as "��������"
    , 'god.codegen.crud.sys.' || replace(lower(T1.TABLE_NAME), '_', '') as "��Ű����"
    , 'GOD_CODEGEN_CRUD_SYS_' || T1.TABLE_NAME as "��ƼƼ��"
    , T1.*
from SYS.ALL_TAB_COMMENTS T1
where 1 = 1
    and T1.OWNER in (
        ''
        , 'SYS'
    )
    and T1.TABLE_NAME in (
        'ALL_TAB_COMMENTS'
        , 'ALL_TABLES'
        , 'ALL_TAB_COLS'
        , 'ALL_COL_COMMENTS'
        , 'ALL_CONSTRAINTS'
        , 'ALL_CONS_COLUMNS'
        , 'ALL_OBJECTS'
    )
order by
    T1.OWNER, T1.TABLE_NAME
;

