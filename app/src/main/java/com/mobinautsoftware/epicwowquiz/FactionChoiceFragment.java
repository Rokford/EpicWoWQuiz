package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mobinautsoftware.epicwowquiz.FactionChoiceFragment.OnFactionChosenListener} interface
 * to handle interaction events.
 * Use the {@link FactionChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FactionChoiceFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFactionChosenListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FactionChoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FactionChoiceFragment newInstance(String param1, String param2)
    {
        FactionChoiceFragment fragment = new FactionChoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FactionChoiceFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faction_choice, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onFactionChosen(String faction, String race)
    {
        if (mListener != null)
        {
            mListener.onFactionChosen(faction, race);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        RelativeLayout upperRL = (RelativeLayout) view.findViewById(R.id.upperRL);
        RelativeLayout lowerRL = (RelativeLayout) view.findViewById(R.id.loweRL);

        ImageView humanImageView = (ImageView) view.findViewById(R.id.humanImageView);
        ImageView nightelfImageView = (ImageView) view.findViewById(R.id.nightelfImageView);
        ImageView gnomeImageView = (ImageView) view.findViewById(R.id.gnomeImageView);

        ImageView orcImageView = (ImageView) view.findViewById(R.id.orcImageView);
        ImageView undeadImageView = (ImageView) view.findViewById(R.id.undeadImageView);
        ImageView bloodelfImageView = (ImageView) view.findViewById(R.id.bloodelfImageView);

        humanImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_ALLIANCE, App.RACE_HUMAN);
            }
        });

        nightelfImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_ALLIANCE, App.RACE_NIGHTELF);
            }
        });

        gnomeImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_ALLIANCE, App.RACE_GNOME);
            }
        });

        orcImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_HORDE, App.RACE_ORC);
            }
        });

        undeadImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_HORDE, App.RACE_UNDEAD);
            }
        });

        bloodelfImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFactionChosen(App.FACTION_HORDE, App.RACE_BLOODELF);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnFactionChosenListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnFactionChosenListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnFactionChosenListener
    {
        // TODO: Update argument type and name
        public void onFactionChosen(String faction, String race);
    }

}
