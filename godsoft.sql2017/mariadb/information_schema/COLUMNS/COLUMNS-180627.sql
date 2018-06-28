select
	COLUMNS.*
from information_schema.COLUMNS
;

select
	COLUMNS.*
from information_schema.COLUMNS
where 1 = 1
	and COLUMNS.TABLE_SCHEMA = 'us_test180625_com370_obj'
	and COLUMNS.TABLE_NAME = 'comtccmmnclcode'
;

select
	COLUMNS.COLUMN_NAME
	, lower(COLUMNS.COLUMN_NAME) as COLUMN_NAME_LOWER
	, upper(COLUMNS.COLUMN_NAME) as COLUMN_NAME_UPPER
	, test.camel_case(COLUMNS.COLUMN_NAME) as COLUMN_NAME_CAMEL_CASE
	, test.pascal_case(COLUMNS.COLUMN_NAME) as COLUMN_NAME_PASCAL_CASE
	, concat('private String ', test.camel_case(COLUMNS.COLUMN_NAME), ';') as COLUMN_NAME_VO
	, concat('	, ', lower(COLUMNS.COLUMN_NAME)) as COLUMN_NAME_SQL_SELECT
	, concat('and ', lower(COLUMNS.COLUMN_NAME), ' = #', test.camel_case(COLUMNS.COLUMN_NAME), '#') as COLUMN_NAME_IBATIS_WHERE
	, concat(', #', test.camel_case(COLUMNS.COLUMN_NAME), '#') as COLUMN_NAME_IBATIS_INSERT
	, concat(', ', lower(COLUMNS.COLUMN_NAME), ' = #', test.camel_case(COLUMNS.COLUMN_NAME), '#') as COLUMN_NAME_IBATIS_UPDATE
from information_schema.COLUMNS
where 1 = 1
	and COLUMNS.TABLE_SCHEMA = 'us_test180625_com370_obj'
	and COLUMNS.TABLE_NAME = 'comtccmmnclcode'
;
