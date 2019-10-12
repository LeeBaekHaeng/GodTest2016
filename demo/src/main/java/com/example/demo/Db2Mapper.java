package com.example.demo;

import org.apache.ibatis.annotations.Select;

@DB2
public interface Db2Mapper {

	@Select("select column_name from COLUMNS where table_schema = 'com' and table_name = 'comtcadministcode' and column_name = 'ADMINIST_ZONE_SE';")
	String getClCode();

	String getDb1Dual();

}
