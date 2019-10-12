package com.example.demo;

import org.apache.ibatis.annotations.Select;

@DB1
public interface Db1Mapper {

	@Select("SELECT CL_CODE FROM comtccmmnclcode")
	String getClCode();

	String getDb1Dual();

}
