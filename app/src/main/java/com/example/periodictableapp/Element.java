package com.example.periodictableapp;

import java.io.Serializable;

public class Element implements Serializable
{
    private int _atomicNumber;
    private String _symbol;
    private String _name;
    private String _atomicMass;
    private short _yearDiscovered;
    private String _cpkHexColor;
    private int _period;
    private int _groupfamily;
    private boolean _favorited;
    private StandardState _standardState;
    private Groupblock _groupblock;

    public int getatomicNumber()
    {
        return this._atomicNumber;
    }
    public void setatomicNumber(int value)
    {
        this._atomicNumber = value;
    }


    public String getsymbol()
    {
        return this._symbol;
    }
    public void setsymbol(String value)
    {
        this._symbol = value;
    }


    public String getname()
    {
        return this._name;
    }
    public void setname(String value)
    {
        this._name = value;
    }


    public String getatomicMass()
    {
        return this._atomicMass;
    }
    public void setatomicMass(String value)
    {
        this._atomicMass = value;
    }


    public short getyearDiscovered()
    {
        return this._yearDiscovered;
    }
    public void setyearDiscovered(short value)
    {
        this._yearDiscovered = value;
    }


    public String getcpkHexColor()
    {
        return this._cpkHexColor;
    }
    public void setcpkHexColor(String value)
    {
        this._cpkHexColor = value;
    }


    public int getperiod()
    {
        return this._period;
    }
    public void setperiod(int value)
    {
        this._period = value;
    }


    public int getgroupfamily()
    {
        return this._groupfamily;
    }
    public void setgroupfamily(int value)
    {
        this._groupfamily = value;
    }


    public boolean getfavorited()
    {
        return this._favorited;
    }
    public void setfavorited(boolean value)
    {
        this._favorited = value;
    }


    public StandardState get_standardState() {
        return _standardState;
    }

    public void set_standardState(StandardState _standardState) {
        this._standardState = _standardState;
    }

    public Groupblock get_groupblock() {
        return _groupblock;
    }

    public void set_groupblock(Groupblock _groupblock) {
        this._groupblock = _groupblock;
    }


}