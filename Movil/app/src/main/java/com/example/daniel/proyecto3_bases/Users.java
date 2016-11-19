package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 16/11/2016.
 */

public class Users {
    public final String table="GENERAL_USER";
    public String _id;
    public String _name;
    public String _lastName1;
    public String _lastName2;
    public String _identityNumber;
    public String _cellPhone;
    public String _password;
    public String _office;
    public String _residenceAddress;
    public String _username;
    public String _birthDate;
    public String penalty;
    public String rol;


    public Users(String _id,String _name, String _lastName1, String _lastName2, String _cellPhone, String _identityNumber,String _username, String _password,String _birthDate,String _office, String _residenceAddress,String penalty){
        this._id=_id;
        this._name=_name;
        this._lastName1=_lastName1;
        this._lastName2=_lastName2;
        this._cellPhone=_cellPhone;
        this._identityNumber=_identityNumber;
        this._password=_password;
        this._office=_office;
        this._residenceAddress=_residenceAddress;
        this._username=_username;
        this._birthDate=_birthDate;
        this.penalty=penalty;
    }





}
