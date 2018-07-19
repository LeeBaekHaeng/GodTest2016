god180719 공통컴포넌트 3.7.0 tiles-3.0.8

eGovCI-3.5.0_64bit.exe
eGovFrameDev-3.7.0-64bit.exe
jdk-8u162-windows-x64.exe

C:\god\eGovCI-3.5.0_64bit\bin\apache-maven-3.3.3\conf
settings.xml
<localRepository>C:\god\eGovCI-3.5.0_64bit/bin/apache-maven-3.3.3/repository</localRepository>

C:\god\eGovCI-3.5.0_64bit\bin\apache-maven-3.3.3\repository

C:\Program Files\Java\jdk1.8.0_162
C:\god\eGovCI-3.5.0_64bit\bin

C:\god\eGovFrameDev-3.7.0-64bit\eclipse
eclipse.ini
-vm
#C:\god\eGovCI-3.5.0_64bit\bin\jdk1.8.0_162\bin\javaw.exe
#C:\god\eGovCI-3.5.0_64bit\bin\jdk1.8.0_162\jre\bin\javaw.exe
C:\god\eGovCI-3.5.0_64bit\bin\jdk1.8.0_162\jre\bin\server\jvm.dll

C:\god\eGovFrameDev-3.7.0-64bit\workspace

god.test180719-com370-tiles308

https://downloads.mariadb.org/interstitial/mariadb-10.3.8/winx64-packages/mariadb-10.3.8-winx64.msi/from/http%3A//ftp.kaist.ac.kr/mariadb/?serve

mariadb-10.3.8-winx64.msi

https://tiles.apache.org/download.html#Tiles_3_as_a_Maven_dependency

/god.test180719-com370-tiles308/pom.xml
<dependency>
<groupId>org.apache.tiles</groupId>
<artifactId>tiles-extras</artifactId>
<version>3.0.8</version>
</dependency>

/god.test180719-com370-tiles308/src/main/resources/egovframework/egovProps/globals.properties
Globals.DbType = mysql
Globals.Url=jdbc:log4jdbc:mysql://localhost:3306/database
Globals.UserName = ID
Globals.Password = PW

/god.test180719-com370-tiles308/src/script/mysql/ddl/cmm_create_mysql.sql
/god.test180719-com370-tiles308/src/script/mysql/dml/cmm_insert_mysql.sql

http://localhost:8080/test180719-tiles308

https://github.com/LeeBaekHaeng/GodTest2016/blob/master/godsoft.test20170716-tiles3/src/main/webapp/WEB-INF/config/godsoft/springmvc/egov-com-tiles3.xml

src/main/webapp/WEB-INF/config/god/springmvc
egov-com-tiles308.xml

/god.test180719-com370-tiles308/src/main/webapp/WEB-INF/web.xml
<param-value>/WEB-INF/config/egovframework/springmvc/egov-com-*.xml</param-value>

<param-value>
/WEB-INF/config/egovframework/springmvc/egov-com-*.xml
/WEB-INF/config/god/springmvc/egov-com-*.xml
</param-value>

https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/view.html#view-tiles

<bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles2.TilesConfigurer">
  <property name="definitions">
    <list>
      <value>/WEB-INF/defs/general.xml</value>
      <value>/WEB-INF/defs/widgets.xml</value>
      <value>/WEB-INF/defs/administrator.xml</value>
      <value>/WEB-INF/defs/customer.xml</value>
      <value>/WEB-INF/defs/templates.xml</value>
    </list>
  </property>
</bean>

<bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
  <property name="viewClass" value="org.springframework.web.servlet.view.tiles2.TilesView"/>
</bean>

https://github.com/LeeBaekHaeng/GodTest2016/blob/master/godsoft.test20170716-tiles3/src/main/webapp/WEB-INF/config/godsoft/defs/tiles-defs.xml

src/main/webapp/WEB-INF/config/god/defs
general.xml

http://localhost:8080/test180719-tiles308/EgovContent.do

/EgovContent.do

/god.test180719-com370-tiles308/src/main/webapp/WEB-INF/jsp/egovframework/com/cmm/EgovUnitContent.jsp

/god.test180719-com370-tiles308/src/main/webapp/WEB-INF/jsp/god/com/cmm/EgovUnitContent.jsp

