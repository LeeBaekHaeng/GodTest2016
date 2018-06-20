select
    ALL_OBJECTS.*
from ALL_OBJECTS
where 1 = 1
--    and ALL_OBJECTS.OWNER = 'TEST'
--    and ALL_OBJECTS.OBJECT_TYPE in ('TABLE')
--    and ALL_OBJECTS.OBJECT_TYPE in ('TABLE', 'VIEW')
--    and ALL_OBJECTS.OBJECT_TYPE in ('TABLE', 'VIEW', 'SYNONYM')
--    and OBJECT_NAME = 'SYSDATE'
    and OBJECT_NAME = 'TO_DATE'
--order by
--    ALL_OBJECTS.OBJECT_ID
;

select * from DBA_OBJECTS
;

