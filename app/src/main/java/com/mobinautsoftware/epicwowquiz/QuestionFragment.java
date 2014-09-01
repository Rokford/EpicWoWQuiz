package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Question;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mobinautsoftware.epicwowquiz.QuestionFragment.OnAnswerSelectedListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment
{
    private static final String ARG_QUESTION = "ARG_QUESTION";

    private Question question;

    private OnAnswerSelectedListener mListener;

    public static QuestionFragment newInstance(Question question)
    {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_QUESTION, question);

        fragment.setArguments(args);
        return fragment;
    }

    public QuestionFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            question = (Question) getArguments().get(ARG_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    public void onAnswerSelected(boolean isCorrectAnswerSelected)
    {
        if (mListener != null)
        {
            mListener.onAnswerSelected(isCorrectAnswerSelected);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnAnswerSelectedListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnAnswerSelectedListener");
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
    public interface OnAnswerSelectedListener
    {
        // TODO: Update argument type and name
        public void onAnswerSelected(boolean isCorrectAnswerSelected);
    }

}
