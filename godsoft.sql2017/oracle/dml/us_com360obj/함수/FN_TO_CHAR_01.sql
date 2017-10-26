select
    to_date('20170929', 'yyyymmdd') as DATE_01
    , to_char(to_date('20170929', 'yyyymmdd'), 'yyyy-mm-dd') as DATE_02
    , FN_TO_CHAR_01('20170929', 'yyyymmdd', 'yyyy-mm-dd') as DATE_03
    , FN_TO_CHAR_01('20170929a', 'yyyymmdd', 'yyyy-mm-dd') as DATE_04
    , FN_TO_CHAR_01('20170929000000', 'yyyymmddhh24miss', 'yyyy-mm-dd hh24:mi:ss') as DATE_05
    , FN_TO_CHAR_01('20170929000001', 'yyyymmddhh24miss', 'yyyy-mm-dd hh24:mi:ss') as DATE_06
    , FN_TO_CHAR_01('20170929235959', 'yyyymmddhh24miss', 'yyyy-mm-dd hh24:mi:ss') as DATE_07
    , FN_TO_CHAR_01('20170929240000', 'yyyymmddhh24miss', 'yyyy-mm-dd hh24:mi:ss') as DATE_08
from DUAL
;
