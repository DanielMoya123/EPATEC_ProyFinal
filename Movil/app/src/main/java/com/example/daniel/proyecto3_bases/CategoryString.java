package com.example.daniel.proyecto3_bases;

public interface CategoryString {
	public static final String CATEGORY = "CATEGORY";
	public static final String ID = "_id TEXT UNIQUE NOT NULL,";
	public static final String DESCRIPTION = "_description TEXT NOT NULL,";
	public static final String PRIMARYKEY = "CONSTRAINT Cpk PRIMARY KEY (_id)";
	public static final String CREATE_CATEGORY = "CREATE TABLE " + CATEGORY + "(" + ID + DESCRIPTION+
								PRIMARYKEY + ")";
}
