package com.mobinautsoftware.epicwowquiz;

/**
 * Created by plgrizw on 28.08.2014.
 */
public class PlayerInfo
{
    private String faction;

    public int getTier5()
    {
        return tier5;
    }

    public void setTier5(int tier5)
    {
        this.tier5 = tier5;
    }

    public int getTier1()
    {
        return tier1;
    }

    public void setTier1(int tier1)
    {
        this.tier1 = tier1;
    }

    public int getTier2()
    {
        return tier2;
    }

    public void setTier2(int tier2)
    {
        this.tier2 = tier2;
    }

    public int getTier3()
    {
        return tier3;
    }

    public void setTier3(int tier3)
    {
        this.tier3 = tier3;
    }

    public int getTier4()
    {
        return tier4;
    }

    public void setTier4(int tier4)
    {
        this.tier4 = tier4;
    }

    private int tier1 = 0;
    private int tier2 = 0;
    private int tier3 = 0;
    private int tier4 = 0;
    private int tier5 = 0;

    public PlayerInfo(String faction)
    {
        this.faction = faction;
    }

    public PlayerInfo(String faction, int tier1, int tier2, int tier3, int tier4, int tier5)
    {
        this.faction = faction;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
        this.tier5 = tier5;
    }
}
