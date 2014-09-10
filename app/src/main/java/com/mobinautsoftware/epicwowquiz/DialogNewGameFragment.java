package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;


public class DialogNewGameFragment extends android.support.v4.app.DialogFragment
{
    private OnDialogOptionChosenListener mListener;
    private String faction;

    public DialogNewGameFragment()
    {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Title
                .setTitle(getResources().getString(R.string.dialog_title))
                        // Set Dialog Message
                .setMessage(getResources().getString(R.string.dialog_text))

                        // Positive button
                .setPositiveButton(getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        onButtonPressed(faction, true);
                    }
                })

                        // Negative Button
                .setNegativeButton(getResources().getString(R.string.dialog_no), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        onButtonPressed(faction, false);
                    }
                }).create();
    }

    public void onButtonPressed(String faction, boolean shouldProceed)
    {
        if (mListener != null)
        {
            mListener.onDialogOptionChosen(faction, shouldProceed);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnDialogOptionChosenListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnDialogOptionChosenListener
    {
        public void onDialogOptionChosen(String faction, boolean shouldProceed);
    }

    public void setFaction(String faction)
    {
        this.faction = faction;
    }

}
