package com.example.daniel.proyecto3_bases;

public interface ProductString {
	public static final String PRODUCT = "PRODUCT";
	public static final String ID = "_id TEXT UNIQUE NOT NULL,";
	public static final String DESCRIPTION = "_description TEXT NOT NULL,";
	public static final String OFFICE = "_office TEXT,";
	public static final String PRICE = "price INTEGER,";
	public static  final  String TAXABLE = "_nonTaxable numeric,";
	public static final String CATEGORYID = "_categoryId TEXT NOT NULL,";
	public static final String AMOUNT = "_amount INTEGER,";
	public static final String PRIMARYKEY = "CONSTRAINT mpk PRIMARY KEY (_id),";
	public static final String FOREIGNKEY = "CONSTRAINT category_fk FOREIGN KEY (_categoryId) REFERENCES CATEGORY (_id)";
	public static final String CREATE_PRODUCT = "CREATE TABLE " + PRODUCT + "(" + ID + DESCRIPTION
								+OFFICE+PRICE + TAXABLE+CATEGORYID+ AMOUNT+ PRIMARYKEY + FOREIGNKEY+")";
}
