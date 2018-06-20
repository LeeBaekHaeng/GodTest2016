select
    *
from DBA_PROCEDURES
where 1 = 1
--    and OBJECT_NAME = 'SYSDATE'
    and OBJECT_NAME = 'TO_DATE'
;

select
    *
from DBA_SOURCE
where 1 = 1
--    and OBJECT_NAME = 'SYSDATE'
    and NAME = 'TO_DATE'
;

select
    *
from DICTIONARY 
;

select
    *
from DBA_OBJECTS
where 1 = 1
--    and OBJECT_NAME = 'SYSDATE'
    and OBJECT_NAME = 'TO_DATE'
;

