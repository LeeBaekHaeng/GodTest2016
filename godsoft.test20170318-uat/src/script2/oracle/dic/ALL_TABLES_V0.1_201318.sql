select
  *
from ALL_TABLES
where 1 = 1
  and OWNER = 'US_GODCOM351OBJ'
;


select
  'drop table "' || OWNER || '"."' || TABLE_NAME || '";' as sql_drop_01
  , 'drop table "' || OWNER || '"."' || TABLE_NAME || '" cascade constraints;' as sql_drop_02
from ALL_TABLES
where 1 = 1
  and OWNER = 'US_GODCOM351OBJ'
;
