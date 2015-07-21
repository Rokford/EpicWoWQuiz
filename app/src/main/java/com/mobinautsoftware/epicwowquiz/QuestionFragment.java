package com.mobinautsoftware.epicwowquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.internal.ch;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquic.adapters.QuestionAdapter;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Answer;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;
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

    private static final int TIME_LIMIT = 1 * 25 * 1000;

    private Question question;

    private Answer answer;

    private CountDownTimer timer;

    private OnAnswerSelectedListener mListener;

    private ProgressBar progressBar;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        final ListView questionListView = (ListView) view.findViewById(R.id.listView);

        final QuestionAdapter adapter = new QuestionAdapter(getActivity(), mListener.getPlayerInfo());

        adapter.setQuestion(question);

        questionListView.setAdapter(adapter);

        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                answer = (Answer) parent.getAdapter().getItem(position);
                adapter.setSelectedIndex(position);
            }
        });

        questionListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button nextButton = (Button) view.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer();
            }
        });

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        if (mListener.getPlayerInfo().getFaction().equals(App.FACTION_ALLIANCE))
        {
            nextButton.setBackgroundColor(getResources().getColor(R.color.blue));
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_alliance));
        }
        else
        {
            nextButton.setBackgroundColor(getResources().getColor(R.color.red));
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_horde));
        }

        progressBar.setProgress(100);

        int callInterval = 100;

        /** CountDownTimer */
        timer = new CountDownTimer(TIME_LIMIT, callInterval)
        {

            public void onTick(long millisUntilFinished)
            {

                int secondsRemaining = (int) millisUntilFinished / 1000;

                float fraction = millisUntilFinished / (float) TIME_LIMIT;

                // progress bar is based on scale of 1 to 100;
                progressBar.setProgress((int) (fraction * 100));
            }

            @Override
            public void onFinish()
            {
                checkAnswer();
            }
        }.start();


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause()
    {
        if (timer != null)
            timer.cancel();

        super.onPause();
    }

    private void checkAnswer()
    {
        if (answer != null)
        {
            onAnswerSelected(answer.isCorrectAnswer());
        }
        else
            onAnswerSelected(false);
    }

    public PlayerInfo getPlayerinfo()
    {
        if (mListener != null)
        {
            return  mListener.getPlayerInfo();
        }
        else
            return null;
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

    public interface OnAnswerSelectedListener
    {
        // TODO: Update argument type and name
        public void onAnswerSelected(boolean isCorrectAnswerSelected);

        public PlayerInfo getPlayerInfo();
    }

}
