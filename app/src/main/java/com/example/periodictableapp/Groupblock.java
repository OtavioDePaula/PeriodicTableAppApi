package com.example.periodictableapp;

public class Groupblock
{
    private int groupblockID;
    private String groupblock;

    public int getgroupblockID()
    {
        return this.groupblockID;
    }
    public void setgroupblockID(int value)
    {
        this.groupblockID = value;
    }

    public String getgroupblock()
    {
        return this.groupblock;
    }
    public void setgroupblock(String value)
    {
        this.groupblock = value;
    }

    public Groupblock(){

    }

    public Groupblock(int groupblockID_, String groupblock_)
    {
        this.groupblockID = groupblockID_;
        this.groupblock = groupblock_;
    }
}