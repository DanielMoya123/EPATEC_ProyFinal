package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 16/11/2016.
 */

public class Products {
    public final String table ="PRODUCT";
    public String _id;
    public boolean _nontaxable;
    public String _office;
    public String _description;
    public String _categoryId;
    public int _amount;
    public int price;


    public Products(String _id, boolean _nontaxable, String _office, String _description, String _categoryId, int _amount,int price){
        this._id=_id;
        this._nontaxable=_nontaxable;
        this._office=_office;
        this._description=_description;
        this._categoryId=_categoryId;
        this._amount=_amount;
        this.price=price;
    }

}
