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

public class QuestionAdapter extends BaseAdapter
{
    private ViewHolder holder;
    private Context context;
    private LayoutInflater inflater;
    private int TYPE_QUESTION = 0;
    private int TYPE_ANSWER = 1;
    private int backgroundColor;

    public int getSelectedIndex()
    {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex)
    {
        this.selectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    private int selectedIndex = -1;

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    private Question question;

    public QuestionAdapter(Context context, PlayerInfo info)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;

        if (info.getFaction().equals(App.FACTION_ALLIANCE))
            backgroundColor = App.getContext().getResources().getColor(R.color.blue);
        else
            backgroundColor = App.getContext().getResources().getColor(R.color.red);
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position == 0)
            return TYPE_QUESTION;
        else
            return TYPE_ANSWER;
    }

    @Override
    public int getCount()
    {
        return 5;
    }

    @Override
    public boolean isEnabled(int position)
    {
        if (position > 0)
            return true;
        else
            return false;
    }

    @Override
    public Object getItem(int position)
    {
        return question.getAnswers().get(position - 1);
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

            if (getItemViewType(position) == TYPE_QUESTION)
            {
                convertView = inflater.inflate(R.layout.question_list_question_item, null);

                holder.questionTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            }
            else
            {
                convertView = inflater.inflate(R.layout.question_list_answer_item, null);

                holder.answerTextView = (TextView) convertView.findViewById(R.id.answerTextView);
            }

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if (getItemViewType(position) == TYPE_QUESTION)
        {
            holder.questionTextView.setText(question.getQuestionText());

        }
        else
        {
            holder.answerTextView.setText(question.getAnswers().get(position - 1).getAnswerText());

            if (selectedIndex != -1 && selectedIndex == position)
            {
                holder.answerTextView.setBackgroundColor(backgroundColor);
                holder.answerTextView.setTextColor(App.getContext().getResources().getColor(android.R.color.white));
            }
            else
            {
                holder.answerTextView.setBackgroundColor(App.getContext().getResources().getColor(android.R.color.white));
                holder.answerTextView.setTextColor(App.getContext().getResources().getColor(android.R.color.black));
            }
        }


        return convertView;
    }

    public static class ViewHolder
    {
        public TextView questionTextView;
        public TextView answerTextView;

    }

}
