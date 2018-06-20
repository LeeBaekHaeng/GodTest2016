/*
오라클(ORACLE) 날짜 레코드 만들기 CONNECT BY

출처: http://javaengine.tistory.com/44 [자바엔진]
*/

select
    lpad(level, 2, '0')
from dual
connect by level <= 23
;

/*
시
*/
select
    STD_DT || ' ' || STD_HH24 as STD_DT
from (
select
    to_char(to_date('2016-01-01', 'yyyy-mm-dd') + level - 1, 'yyyy-mm-dd') as STD_DT /* 표준일시 */
from dual
--connect by level <= to_date('2015-12-31', 'yyyy-mm-dd') - to_date('2015-01-01', 'yyyy-mm-dd') + 1
--connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2016-01-01', 'yyyy-mm-dd') + 1
connect by level <= to_date('2017-12-31', 'yyyy-mm-dd') - to_date('2016-01-01', 'yyyy-mm-dd') + 1
), (
select
--    lpad(level, 2, '0') as std_hh24 /* 표준시 */
    lpad(level - 1, 2, '0') as STD_HH24 /* 표준시 */
from dual
--connect by level <= 23
connect by level <= 24
)
;

/*
일
*/
select
    to_char(to_date('2015-01-01', 'yyyy-mm-dd') + level - 1, 'yyyy-mm-dd') as STD_DT /* 표준일시 */
from dual
--connect by level <= to_date('2015-12-31', 'yyyy-mm-dd') - to_date('2015-01-01', 'yyyy-mm-dd') + 1
--connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2016-01-01', 'yyyy-mm-dd') + 1
connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2015-01-01', 'yyyy-mm-dd') + 1
;

select
    to_char(sysdate, 'yyyy-ww')
    , to_char(to_date('2017-01-01', 'yyyy-mm-dd'), 'yyyy-ww')
    , to_char(to_date('2017-12-31', 'yyyy-mm-dd'), 'yyyy-ww')
from dual
;

/*
주
*/
select
    distinct to_char(to_date('2010-01-01', 'yyyy-mm-dd') + level - 1, 'yyyy-ww') as STD_DT /* 표준일시 */
from dual
--connect by level <= to_date('2015-12-31', 'yyyy-mm-dd') - to_date('2015-01-01', 'yyyy-mm-dd') + 1
--connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2016-01-01', 'yyyy-mm-dd') + 1
connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2010-01-01', 'yyyy-mm-dd') + 1
order by
    STD_DT /* 표준일시 */
;

/*
년
*/
select
    distinct to_char(to_date('2010-01-01', 'yyyy-mm-dd') + level - 1, 'yyyy') as STD_DT /* 표준일시 */
from dual
--connect by level <= to_date('2015-12-31', 'yyyy-mm-dd') - to_date('2015-01-01', 'yyyy-mm-dd') + 1
--connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2016-01-01', 'yyyy-mm-dd') + 1
connect by level <= to_date('2016-12-31', 'yyyy-mm-dd') - to_date('2010-01-01', 'yyyy-mm-dd') + 1
order by
    STD_DT /* 표준일시 */
;
