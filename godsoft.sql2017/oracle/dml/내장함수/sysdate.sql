select
    sysdate
--    , to_char(sysdate, 'yyyy-ww') as YYYY_WW
--    , to_date('2017-01-01', 'yyyy-mm-dd') as YYYY_MM_DD
    , to_char(to_date('2017-01-01', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */
    , to_char(to_date('2017-01-02', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */
    , to_char(to_date('2017-01-07', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */
    , to_char(to_date('2017-01-08', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */
    , to_char(to_date('2017-01-09', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */
    , to_char(to_date('2017-11-23', 'yyyy-mm-dd'), 'yyyy-ww') as YYYY_WW /* 林 */    
--    , to_date('2017-01-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')
--    , to_date('2017-01-01 00:00:01', 'yyyy-mm-dd hh24:mi:ss')
--    , to_date('2017-01-01 23:59:59', 'yyyy-mm-dd hh24:mi:ss')
from dual
;

