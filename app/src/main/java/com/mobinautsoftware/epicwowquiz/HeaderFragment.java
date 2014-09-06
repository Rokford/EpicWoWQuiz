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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;

public class HeaderFragment extends Fragment
{
    private OnHeaderFragmentInteractionListener mListener;

    private ImageView portraitImageView;
    private ImageView iconImageView;
    private TextView textUnderIconTextView;

    private ImageView easyMedalImageView;
    private ImageView mediumMedalImageView;
    private ImageView hardMedalImageView;
    private ImageView insaneMedalImageView;

    private TextView easyMedalTextView;
    private TextView mediumMedalTextView;
    private TextView hardMedalTextView;
    private TextView insaneMedalTextView;

    private LinearLayout insaneLinearLayout;


    public HeaderFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onHeaderFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        textUnderIconTextView = (TextView) view.findViewById(R.id.textUnderIconTextView);
        insaneLinearLayout = (LinearLayout) view.findViewById(R.id.insaneLL);
        portraitImageView = (ImageView) view.findViewById(R.id.portraitImageView);
        iconImageView = (ImageView) view.findViewById(R.id.iconImageView);
        easyMedalImageView = (ImageView) view.findViewById(R.id.easyMedalImageView);
        mediumMedalImageView = (ImageView) view.findViewById(R.id.mediumMedalImageView);
        hardMedalImageView = (ImageView) view.findViewById(R.id.hardMedalImageView);
        insaneMedalImageView = (ImageView) view.findViewById(R.id.insaneMedalImageView);
        easyMedalTextView = (TextView) view.findViewById(R.id.tier1TextView);
        mediumMedalTextView = (TextView) view.findViewById(R.id.tier2TextView);
        hardMedalTextView = (TextView) view.findViewById(R.id.tier3TextView);
        insaneMedalTextView = (TextView) view.findViewById(R.id.tier4TextView);

        updateContent(mListener.headerViewCreated());
    }

    public void updateContent(PlayerInfo info)
    {
        if (info != null)
        {
            if (info.getFaction().equals(App.FACTION_ALLIANCE))
                textUnderIconTextView.setText(App.getContext().getString(R.string.alliance));
            else if (info.getFaction().equals(App.FACTION_HORDE))
                textUnderIconTextView.setText(App.getContext().getString(R.string.horde));
            else textUnderIconTextView.setText("");

            if (!mListener.shouldShowExtraTier())
            {
                insaneLinearLayout.setVisibility(View.GONE);
            }
            else
            {
                insaneMedalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier4()));
                insaneMedalTextView.setText(PlayerInfo.getMedalString(info.getTier4()));
            }

            easyMedalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier1()));
            mediumMedalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier2()));
            hardMedalImageView.setImageResource(PlayerInfo.getMedalResourceForMedal(info.getTier3()));

            easyMedalTextView.setText(PlayerInfo.getMedalString(info.getTier1()));
            mediumMedalTextView.setText(PlayerInfo.getMedalString(info.getTier2()));
            hardMedalTextView.setText(PlayerInfo.getMedalString(info.getTier3()));
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnHeaderFragmentInteractionListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnMainMenuFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHeaderFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onHeaderFragmentInteraction(Uri uri);

        public boolean shouldShowExtraTier();

        public PlayerInfo headerViewCreated();
    }

}
