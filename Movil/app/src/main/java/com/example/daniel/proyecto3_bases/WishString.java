package com.example.daniel.proyecto3_bases;

public interface WishString {
	public static final String WISH = "WISH ";
	public static final String ID = "_id TEXT UNIQUE NOT NULL,";
	public static final String CLIENTID = "_clientId TEXT  NOT NULL,";
	public static final String SELLERID = "_sellerId TEXT  NOT NULL,";
	public static final String PENALTY = "penalty numeric,";
	public static final String CREATIONTIME = "_creationTime TEXT,";
	public static final String FOREIGNKEY = "CONSTRAINT clientFK FOREIGN KEY (_clientId) REFERENCES GENERAL_USER (_id),";
	public static final String PRIMARYKEY = "CONSTRAINT sellerFK FOREIGN KEY (_sellerId) REFERENCES GENERAL_USER (_id)";
	public static final String CREATE_WISH = "CREATE TABLE " + WISH + "(" + ID + CLIENTID + SELLERID+
								PENALTY+ CREATIONTIME  + FOREIGNKEY+PRIMARYKEY +")";
}
