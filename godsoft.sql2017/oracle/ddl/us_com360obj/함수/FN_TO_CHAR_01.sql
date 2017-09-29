CREATE OR REPLACE FUNCTION FN_TO_CHAR_01 (
    IN_CHAR in varchar2
    , IN_TO_DATE_FMT in varchar2
    , IN_TO_CHAR_FMT in varchar2
) RETURN varchar2 IS
tmpVar varchar2(100);
/******************************************************************************
   NAME:       FN_TO_CHAR_01
   PURPOSE:    

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        2017-09-29   LeeBaekHaeng       1. Created this function.

   NOTES:

   Automatically available Auto Replace Keywords:
      Object Name:     FN_TO_CHAR_01
      Sysdate:         2017-09-29
      Date and Time:   2017-09-29, 오후 6:49:58, and 2017-09-29 오후 6:49:58
      Username:        LeeBaekHaeng
      Table Name:       (set in the "New PL/SQL Object" dialog)

******************************************************************************/
BEGIN
   tmpVar := IN_CHAR;

select
    to_char(to_date(IN_CHAR, IN_TO_DATE_FMT), IN_TO_CHAR_FMT)
    into tmpVar
from dual
;

   RETURN tmpVar;
   EXCEPTION
--     WHEN NO_DATA_FOUND THEN
--       NULL;
     WHEN OTHERS THEN
       -- Consider logging the error and then re-raise
--       RAISE;
   RETURN tmpVar;
END FN_TO_CHAR_01;



/
