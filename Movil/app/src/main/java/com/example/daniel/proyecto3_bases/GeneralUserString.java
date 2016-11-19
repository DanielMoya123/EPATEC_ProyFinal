package com.example.daniel.proyecto3_bases;

public interface GeneralUserString {
	public static final String GENERAL_USER = "GENERAL_USER ";
	public static final String ID = "_id TEXT UNIQUE NOT NULL,";
	public static final String NAME = "_name TEXT NOT NULL,";
	public static final String LASTNAME1 = "_lastName1 TEXT,";
	public static final String LASTNAME2 = "_lastName2 TEXT,";
	public static final String IDENTITYNUMBER = "_identityNumber TEXT,";
	public static final String PASSWORD = "_password TEXT,";
	public static final String USERNAME = "_username TEXT NOT NULL,";
	public static final String CELLPHONE = "_cellPhone TEXT,";
	public static final String BIRTHDATE = "_birthDate TEXT,";
	public static final String OFFICE = "_office TEXT,";
	public static final String RESIDENCE = "_residenceAddress TEXT,";
	public static final String PENALTY = "penalty numeric,";
	public static final String PRIMARYKEY = "CONSTRAINT gupk PRIMARY KEY (_id),";
	public static final String CELLPHONEUNIQUE = "CONSTRAINT general_user_cell_phone_number_key UNIQUE (_cellPhone),";
	public static final String IDENTITYUNIQUE = "CONSTRAINT general_user_identity_number_key UNIQUE (_identityNumber),";
	public static final String USERNAMEUNIQUE = "CONSTRAINT general_user_username_key UNIQUE (_username)";
	
	public static final String CREATE_GENERAL_USER = "CREATE TABLE "+ GENERAL_USER + "("+
								ID + NAME +LASTNAME1 + LASTNAME2 + IDENTITYNUMBER + PASSWORD+RESIDENCE +
								USERNAME + CELLPHONE + BIRTHDATE + OFFICE + PENALTY +
								CELLPHONEUNIQUE + IDENTITYUNIQUE + USERNAMEUNIQUE + ")";
}
