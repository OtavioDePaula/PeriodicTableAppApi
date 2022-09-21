package com.example.periodictableapp;

public class StandardState
{
    private int _standardStateID;
    private String _standardState;

    public int getstandardStateID()
    {
        return this._standardStateID;
    }
    public void setstandardStateID(int value)
    {
        this._standardStateID = value;
    }


    public String getstandardState()
    {
        return this._standardState;
    }
    public void setstandardState(String value)
    {
        this._standardState = value;
    }

    public StandardState()
    {

    }

    public StandardState(int standardStateID_, String standardState_)
    {
        this.standardStateID = standardStateID_;
        this.standardState = standardState_;
    }
}