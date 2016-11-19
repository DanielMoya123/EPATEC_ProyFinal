package com.example.daniel.proyecto3_bases;

public interface ProviderProductString {
	public static final String PROVIDER_PRODUCTS = "PROVIDER_PRODUCTS ";
	public static final String PROVIDERID = "_providerId TEXT NOT NULL,";
	public static final String PRODUCTID = "_productId TEXT NOT NULL,";
	public static final String FOREIGNKEY = "CONSTRAINT providerFK FOREIGN KEY (_providerId) REFERENCES GENERAL_USER (_id), CONSTRAINT productFK FOREIGN KEY (_productId) REFERENCES PRODUCT (_id),";
	public static final String PRIMARYKEY = "CONSTRAINT provider_productsPK PRIMARY KEY (_providerId,_productId)";
	public static final String CREATE_USER_ROL = "CREATE TABLE " + PROVIDER_PRODUCTS + "(" + PROVIDERID + PRODUCTID +
								  FOREIGNKEY +PRIMARYKEY+")";
}
