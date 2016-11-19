package com.example.daniel.proyecto3_bases;

public interface RolString {
	public static final String ROL = "ROL";
	public static final String ID = "_id TEXT UNIQUE NOT NULL,";
	public static final String DESCRIPTION = "_description TEXT NOT NULL,";
	public static final String PRIMARYKEY = "CONSTRAINT rpk PRIMARY KEY (_id)";
	public static final String CREATE_ROL = "CREATE TABLE " + ROL + "(" + ID + DESCRIPTION+
								PRIMARYKEY + ")";
}
