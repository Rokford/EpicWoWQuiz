package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mobinautsoftware.epicwowquiz.SummaryFragment.OnSummaryButtonPressedListener} interface
 * to handle interaction events.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SCORE = "ARG_SCORE";
    private static final String ARG_MEDAL_RESOURCE = "ARG_MEDAL_RESOURCE";
    private static final String ARG_MEDAL_STRING = "ARG_MEDAL_STRING";

    // TODO: Rename and change types of parameters
    private int score;
    private int medal_drawable;
    private int medal_string;

    private OnSummaryButtonPressedListener mListener;

    public static SummaryFragment newInstance(int score, int medalDrawable, int medalString)
    {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_MEDAL_RESOURCE, medalDrawable);
        args.putInt(ARG_MEDAL_STRING, medalString);
        fragment.setArguments(args);
        return fragment;
    }

    public SummaryFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            this.score = getArguments().getInt(ARG_SCORE);
            this.medal_drawable = getArguments().getInt(ARG_MEDAL_RESOURCE);
            this.medal_string = getArguments().getInt(ARG_MEDAL_STRING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        TextView scoreTextView = (TextView) view.findViewById(R.id.scoreTextView);
        TextView medalTextView = (TextView) view.findViewById(R.id.medalNameTextView);
        ImageView medalImageView = (ImageView) view.findViewById(R.id.medalImageView);

        Button anotherGameButton = (Button) view.findViewById(R.id.anotherGameButton);
        Button backToMenuButton = (Button) view.findViewById(R.id.backButton);

        if (mListener.getPlayerInfo().getFaction().equals(App.FACTION_ALLIANCE))
        {
            anotherGameButton.setBackgroundColor(getResources().getColor(R.color.blue));
            backToMenuButton.setBackgroundColor(getResources().getColor(R.color.blue));
        }
        else
        {
            anotherGameButton.setBackgroundColor(getResources().getColor(R.color.red));
            backToMenuButton.setBackgroundColor(getResources().getColor(R.color.red));
        }

        scoreTextView.setText(Integer.valueOf(score).toString());
        medalTextView.setText(getString(medal_string));
        medalImageView.setImageResource(medal_drawable);

        anotherGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonPressed(true);
            }
        });
        backToMenuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonPressed(false);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(boolean anotherGame)
    {
        if (mListener != null)
        {
            mListener.onSummaryButtonPressed(anotherGame);
        }
    }

    public void getPlayerinfo()
    {
        if (mListener != null)
        {
            mListener.getPlayerInfo();
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnSummaryButtonPressedListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnSummaryButtonPressedListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnSummaryButtonPressedListener
    {
        // TODO: Update argument type and name
        public void onSummaryButtonPressed(boolean anotherGame);

        public PlayerInfo getPlayerInfo();
    }

}
