package com.example.daniel.proyecto3_bases;

/**
 * Created by ferllini13 on 16/11/2016.
 */

public class Orders {
    public final String table="WISH";
    public String _id;
    public String _office;
    public String _clientId;
    public String _sellerId;
    public boolean penalty;
    public String _creationTime;

    public Orders(String _id, String _office, String _clientId, String _sellerId, boolean penalty, String _creationTime){
        this._id=_id;
        this._office=_office;
        this._clientId=_clientId;
        this.penalty= penalty;
        this._creationTime=_creationTime;


    }

}