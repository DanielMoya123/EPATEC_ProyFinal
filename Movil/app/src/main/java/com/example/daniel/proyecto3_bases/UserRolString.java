package com.example.daniel.proyecto3_bases;

public interface UserRolString {
	public static final String USER_ROL = "USER_ROL ";
	public static final String ROLID = "rol_id TEXT  NOT NULL,";
	public static final String USERID = "_user_id TEXT  NOT NULL,";
	public static final String FOREIGNKEY = "CONSTRAINT user_rol_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES ROL (_id),CONSTRAINT user_rol_user_id_fkey FOREIGN KEY (_user_id)REFERENCES GENERAL_USER (_id),";
	public static final String PRIMARYKEY = "CONSTRAINT user_rol_pkey PRIMARY KEY (_user_id,rol_id)";
	public static final String CREATE_USER_ROL = "CREATE TABLE " + USER_ROL + "(" + ROLID + USERID +
								FOREIGNKEY +PRIMARYKEY+")";
}
