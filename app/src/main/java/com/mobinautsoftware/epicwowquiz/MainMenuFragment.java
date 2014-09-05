package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mobinautsoftware.epicwowquiz.MainMenuFragment.OnMainMenuFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.mobinautsoftware.epicwowquiz.MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private static ArrayList<String> mainMenuItems;

    // TODO: Rename and change types of parameters
    private String faction;

    private ListView mainMenuListView;


    private OnMainMenuFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragment newInstance(String faction)
    {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, faction);
        fragment.setArguments(args);
        return fragment;
    }

    public MainMenuFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            faction = getArguments().getString(ARG_PARAM1);
        }

//        SharedPreferences prefs = App.getContext().getSharedPreferences(App.SHARED_PREFERENCES_NAME, getActivity().MODE_PRIVATE);
//
//        String faction = prefs.getString(App.SHARED_PREFERENCES_FACTION, "");
//        int tier1 = prefs.getInt(App.SHARED_PREFERENCES_TIER1, 0);
//        int tier2 = prefs.getInt(App.SHARED_PREFERENCES_TIER2, 0);
//        int tier3 = prefs.getInt(App.SHARED_PREFERENCES_TIER3, 0);
//        int tier4 = prefs.getInt(App.SHARED_PREFERENCES_TIER4, 0);
//
        mainMenuItems = new ArrayList<String>();

        if (faction.length() > 0)
        {
            mainMenuItems.add(getString(R.string.continue_game));
        }

        mainMenuItems.add(getString(R.string.new_game));
        mainMenuItems.add(getString(R.string.help));
        mainMenuItems.add(getString(R.string.quit));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        ArrayAdapter mainMenuAdapter = new ArrayAdapter(getActivity(), R.layout.main_menu_list_item, R.id.mainMenuTextView, mainMenuItems);

        mainMenuListView = (ListView) view.findViewById(R.id.mainMenuListView);

        mainMenuListView.setAdapter(mainMenuAdapter);

        mainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (parent.getAdapter().getItem(position).equals(getResources().getString(R.string.quit)))
                {
                    getActivity().finish();
                }
                else if (parent.getAdapter().getItem(position).equals(getResources().getString(R.string.new_game)))
                {
                    showFactionChoice();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }


    public void showFactionChoice()
    {
        if (mListener != null)
        {
            mListener.onNewGameStart();
        }
    }


    public void setPlayerInfo(PlayerInfo info)
    {
        if (mListener != null)
        {
            mListener.onMainMenuFragmentInteraction(info);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnMainMenuFragmentInteractionListener) activity;
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
    public interface OnMainMenuFragmentInteractionListener
    {
        public void onMainMenuFragmentInteraction(PlayerInfo info);

        public void onNewGameStart();

        public void onHelp();

        public void onContinueGame();
    }

}
