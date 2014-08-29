package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquic.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobinautsoftware.epicwowquiz.App;
import com.mobinautsoftware.epicwowquiz.MainActivity;
import com.mobinautsoftware.epicwowquiz.R;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Question;

import java.util.ArrayList;

public class TierChoiceAdapter extends BaseAdapter
{
    private ViewHolder holder;
    private Context context;
    private LayoutInflater inflater;
    private boolean shouldShowExtraTier;

    public TierChoiceAdapter(Context context, boolean shouldShowExtraTier)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.shouldShowExtraTier = shouldShowExtraTier;
    }

    @Override
    public int getCount()
    {
        if (shouldShowExtraTier)
            return 4;
        else
            return 3;
    }

    @Override
    public Object getItem(int position)
    {
        switch (position)
        {
            case 0:
                return Question.DIFFICULTY_EASY;
            case 1:
                return Question.DIFFICULTY_MEDIUM;
            case 2:
                return Question.DIFFICULTY_HARD;
            case 3:
                return Question.DIFFICULTY_INSANE;
            default:
                return Question.DIFFICULTY_EASY;
        }
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        holder = null;

        if (convertView == null)
        {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.tier_choice_list_item, null);

            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.medalImageView = (ImageView) convertView.findViewById(R.id.medalImageView);
            holder.medalNameTextView = (TextView) convertView.findViewById(R.id.medalNameTextView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        PlayerInfo info = ((MainActivity) context).getPlayerInfo();

        Resources res = App.getContext().getResources();

        switch (position)
        {
            case 0:
                holder.nameTextView.setText(App.getContext().getString(R.string.easy));
                holder.medalNameTextView.setText(res.getString(PlayerInfo.getMedalString(info.getTier1())));
                holder.medalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier1()));
                break;
            case 1:
                holder.nameTextView.setText(App.getContext().getString(R.string.medium));
                holder.medalNameTextView.setText(res.getString(PlayerInfo.getMedalString(info.getTier2())));
                holder.medalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier2()));
                break;
            case 2:
                holder.nameTextView.setText(App.getContext().getString(R.string.hard));
                holder.medalNameTextView.setText(res.getString(PlayerInfo.getMedalString(info.getTier3())));
                holder.medalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier3()));
                break;
            case 3:
                holder.nameTextView.setText(App.getContext().getString(R.string.insane));
                holder.medalNameTextView.setText(res.getString(PlayerInfo.getMedalString(info.getTier4())));
                holder.medalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier4()));
                break;
        }


        return convertView;
    }

    public static class ViewHolder
    {
        public TextView nameTextView;
        public ImageView medalImageView;
        public TextView medalNameTextView;

    }

}
