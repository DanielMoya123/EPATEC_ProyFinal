package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 16/11/2016.
 */

public class Products {
    public final String table ="Product";
    public String _id;
    public boolean _nontaxable;
    public String _office;
    public String _description;
    public String _categoryId;
    public String _amount;

    public Products(String _id, boolean _nontaxable, String _office, String _description, String _categoryId, String _amount){
        this._id=_id;
        this._nontaxable=_nontaxable;
        this._office=_office;
        this._description=_description;
        this._categoryId=_categoryId;
        this._amount=_amount;
    }

}
