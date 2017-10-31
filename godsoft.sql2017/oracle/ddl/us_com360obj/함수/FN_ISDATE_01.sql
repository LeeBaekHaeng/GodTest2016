CREATE OR REPLACE FUNCTION FN_ISDATE_01 (
    IN_DATE in varchar2 /* 날짜 */
    , IN_TO_DATE_FMT in varchar2 /* to_date 포맷 */
) RETURN varchar2 IS
tmpVar varchar2(100);
/******************************************************************************
   NAME:       FN_ISDATE_01
   PURPOSE:    

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        2017-10-31   LeeBaekHaeng       1. Created this function.

   NOTES:

   Automatically available Auto Replace Keywords:
      Object Name:     FN_ISDATE_01
      Sysdate:         2017-10-31
      Date and Time:   2017-10-31, 오전 10:59:03, and 2017-10-31 오전 11:53:00
      Username:        LeeBaekHaeng (set in TOAD Options, Procedure Editor)
      Table Name:       (set in the "New PL/SQL Object" dialog)

******************************************************************************/
BEGIN
   tmpVar := IN_DATE;

select
    to_date(IN_DATE, IN_TO_DATE_FMT)
    into tmpVar
from dual
;

   tmpVar := 'Y';

   RETURN tmpVar;
   EXCEPTION
--     WHEN NO_DATA_FOUND THEN
--       NULL;
     WHEN OTHERS THEN
   tmpVar := 'N';
   RETURN tmpVar;
--       -- Consider logging the error and then re-raise
--       RAISE;
END FN_ISDATE_01;



/
