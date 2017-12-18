/*
oracle 경과시간
http://starplaying.tistory.com/338

경과시간 계산
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date

경과된 시간 계산
https://msdn.microsoft.com/ko-kr/library/ee532932(v=vs.94).aspx
*/

select
    trunc(to_date('2011-10-10 10:02:03', 'yyyy-mm-dd hh24:mi:ss') - to_date('2011-10-09 09:00:00', 'yyyy-mm-dd hh24:mi:ss')) as dd
    , trunc((to_date('2011-10-10 10:02:03', 'yyyy-mm-dd hh24:mi:ss') - to_date('2011-10-09 09:00:00', 'yyyy-mm-dd hh24:mi:ss')) * 24) as hh24
    , trunc(mod((to_date('2011-10-10 10:02:03', 'yyyy-mm-dd hh24:mi:ss') - to_date('2011-10-09 09:00:00', 'yyyy-mm-dd hh24:mi:ss')) * 24, 1) * 60) as mi
    , trunc(round(mod((to_date('2011-10-10 10:02:03', 'yyyy-mm-dd hh24:mi:ss') - to_date('2011-10-09 09:00:00', 'yyyy-mm-dd hh24:mi:ss')) * 24 * 60, 1) * 60)) as ss
from dual
;
