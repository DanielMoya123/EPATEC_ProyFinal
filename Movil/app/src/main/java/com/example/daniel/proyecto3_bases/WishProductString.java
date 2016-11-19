package com.example.daniel.proyecto3_bases;

public interface WishProductString {
	public static final String WISH_PRODUCTS = "WISH_PRODUCTS ";
	public static final String WISHID = "_wishId TEXT NOT NULL,";
	public static final String PRODUCTID = "_productId TEXT NOT NULL,";
	public static final String NUMBEROFPRODUCTS = "numberOfProducts INTEGER,";
	public static final String FOREIGNKEY = "CONSTRAINT wishFK FOREIGN KEY (_wishId) REFERENCES WISH (_id), CONSTRAINT productWFK FOREIGN KEY (_productId) REFERENCES PRODUCT (_id),";
	public static final String PRIMARYKEY = "CONSTRAINT wish_productPK PRIMARY KEY (_wishId,_productId)";
	public static final String CREATE_USER_ROL = "CREATE TABLE " + WISH_PRODUCTS + "(" + WISHID + PRODUCTID +
								NUMBEROFPRODUCTS  + FOREIGNKEY +PRIMARYKEY+")";

}
