CREATE TABLE COMTNROUGHMAP (
    ROUGHMAP_ID       VARCHAR(75) NOT NULL,
	ROUGHMAPSJ        VARCHAR(75) NOT NULL,
	ROUGHMAPADDRESS   VARCHAR(200)NULL,
	LA                VARCHAR(48) NULL,
	LO                VARCHAR(48) NULL,
	MARKERLA          VARCHAR(48) NULL,
	MARKERLO          VARCHAR(48) NULL,
	INFOWINDOW        VARCHAR(20) NULL,
	ZOOMLEVEL         VARCHAR(10) NULL,
	FRST_REGIST_PNTTM DATETIME NULL,
	FRST_REGISTER_ID  VARCHAR(20) NULL,
	LAST_UPDT_PNTTM   DATETIME NULL,
	LAST_UPDUSR_ID    VARCHAR(20) NULL
 );