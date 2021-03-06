package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

import com.mobinautsoftware.epicwowquiz.App;
import com.mobinautsoftware.epicwowquiz.R;

/**
 * Created by plgrizw on 28.08.2014.
 */
public class PlayerInfo
{
    public String getFaction()
    {
        return faction;
    }

    private String faction;

    public int getTier1()
    {
        return tier1;
    }

    public int getTier2()
    {
        return tier2;
    }

    public int getTier3()
    {
        return tier3;
    }

    public int getTier4()
    {
        return tier4;
    }

    private int tier1 = 0;
    private int tier2 = 0;
    private int tier3 = 0;
    private int tier4 = 0;

    public PlayerInfo(String faction)
    {
        this.faction = faction;
    }

    public PlayerInfo(String faction, int tier1, int tier2, int tier3, int tier4)
    {
        this.faction = faction;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
    }

    public int getTier(String difficulty)
    {
        if (difficulty.equals(Question.DIFFICULTY_EASY)) return tier1;
        else if (difficulty.equals(Question.DIFFICULTY_MEDIUM)) return tier2;
        else if (difficulty.equals(Question.DIFFICULTY_HARD)) return tier3;
        else return tier4;
    }

    public int getTierForScore(int score)
    {
        if (score > 9)
            return 3;
        if (score > 8)
            return 2;
        if (score > 6)
            return 1;
        else
            return 0;
    }

    public void setTier(String difficulty, int score)
    {
        if (difficulty.equals(Question.DIFFICULTY_EASY))
        {
            if (score == 10)
            {
                if (tier1 < 3) this.tier1 = 3;

            }
            else if (score > 8)
            {
                if (tier1 < 2) this.tier1 = 2;
            }
            else if (score > 6)
            {
                if (tier1 < 1) this.tier1 = 1;
            }
        }
        if (difficulty.equals(Question.DIFFICULTY_MEDIUM))
        {
            if (score == 10)
            {
                if (tier2 < 3) this.tier2 = 3;

            }
            else if (score > 8)
            {
                if (tier2 < 2) this.tier2 = 2;
            }
            else if (score > 6)
            {
                if (tier2 < 1) this.tier2 = 1;
            }
        }
        if (difficulty.equals(Question.DIFFICULTY_HARD))
        {
            if (score == 10)
            {
                if (tier3 < 3) this.tier3 = 3;

            }
            else if (score > 8)
            {
                if (tier3 < 2) this.tier3 = 2;
            }
            else if (score > 6)
            {
                if (tier3 < 1) this.tier3 = 1;
            }
        }
        if (difficulty.equals(Question.DIFFICULTY_INSANE))
        {
            if (score == 10)
            {
                if (tier4 < 3) this.tier4 = 3;

            }
            else if (score > 8)
            {
                if (tier4 < 2) this.tier4 = 2;
            }
            else if (score > 6)
            {
                if (tier4 < 1) this.tier4 = 1;
            }
        }
    }

    public static int getMedalResourceForMedal(int tier)
    {
        switch (tier)
        {
            case 0:
                return R.drawable.medal_no_medal;
            case 1:
                return R.drawable.medal_bronze;
            case 2:
                return R.drawable.medal_silver;
            case 3:
                return R.drawable.medal_gold;
            default:
                return R.drawable.medal_no_medal;
        }
    }

    public static int getMedalString(int tier)
    {
        switch (tier)
        {
            case 0:
                return R.string.no_medal;
            case 1:
                return R.string.bronze;
            case 2:
                return R.string.silver;
            case 3:
                return R.string.gold;
            default:
                return R.string.no_medal;
        }
    }

    public int getRankString()
    {
        if (this.faction != null && !this.faction.equals(""))
        {
            if (tier1 > 2 && tier2 > 2 && tier3 > 2 && tier4 > 2)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.string.rank4magic;
                else return R.string.rank4war;
            }
            if (tier1 > 2 && tier2 > 2 && tier3 > 2)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.string.rank3magic;
                else return R.string.rank3war;
            }
            if (tier1 > 1 && tier2 > 1 && tier3 > 1)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.string.rank2magic;
                else return R.string.rank2war;
            }
            if (tier1 > 0 && tier2 > 0 && tier3 > 0)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.string.rank1magic;
                else return R.string.rank1war;
            }
            else
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.string.no_rankmagic;
                else return R.string.no_rankwar;
            }
        }
        else
        {
            return R.string.no_rank;
        }
    }

    public int getRankImage()
    {
        if (this.faction != null && !this.faction.equals(""))
        {
            if (tier1 > 2 && tier2 > 2 && tier3 > 2 && tier4 > 2)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.drawable.rank4_magic;
                else return R.drawable.rank4_war;
            }
            if (tier1 > 2 && tier2 > 2 && tier3 > 2)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.drawable.rank3_magic;
                else return R.drawable.rank3_war;
            }
            if (tier1 > 1 && tier2 > 1 && tier3 > 1)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.drawable.rank2_magic;
                else return R.drawable.rank2_war;
            }
            if (tier1 > 0 && tier2 > 0 && tier3 > 0)
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.drawable.rank1_magic;
                else return R.drawable.rank1_war;
            }
            else
            {
                if (this.faction.equals(App.FACTION_ALLIANCE)) return R.drawable.no_rank_magic;
                else return R.drawable.no_rank_war;
            }
        }
        else
        {
            return R.drawable.no_rank;
        }
    }
}

