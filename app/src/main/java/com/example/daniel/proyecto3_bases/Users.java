package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 16/11/2016.
 */

public class Users {
    public final String table="Users";
    public String _id;
    public String _name;
    public String _lastName1;
    public String _lastName2;
    public String _cellphone;
    public String _identityNumber;
    public int _type;
    public String _password;
    public String _office;
    public String _residenceAddress;


    public Users(String _id,String _name, String _lastName1, String _lastName2, String _cellphone, String _identityNumber, int _type, String _password, String _office, String _residenceAddress){
        this._id=_id;
        this._name=_name;
        this._lastName1=_lastName1;
        this._lastName2=_lastName2;
        this._cellphone=_cellphone;
        this._identityNumber=_identityNumber;
        this._type=_type;
        this._password=_password;
        this._office=_office;
        this._residenceAddress=_residenceAddress;




    }





}
