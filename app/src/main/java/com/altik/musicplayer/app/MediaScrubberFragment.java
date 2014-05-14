package com.altik.musicplayer.app;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.io.InvalidClassException;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link MediaScrubberFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MediaScrubberFragment extends Fragment
{
    public interface ScrubberCallback
    {
        public void Play();
        public void Pause();
        public void SkipForward();
        public void SkipBack();
        // TODO: more?
    }

    private ScrubberCallback callback;

    // Buttons!
    private ToggleButton playButton;

    // Button listeners!
    private ToggleButton.OnCheckedChangeListener playListener = new ToggleButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
        {
            if (callback != null)
            {
                if(b)
                    callback.Play();
                else
                    callback.Pause();
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MediaScrubberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MediaScrubberFragment newInstance()
    {
        MediaScrubberFragment fragment = new MediaScrubberFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }
    public MediaScrubberFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //{
        //if (getArguments() != null)
        //    mParam1 = getArguments().getString(ARG_PARAM1);
        //    mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_media_scrubber, container, false);
        playButton = (ToggleButton)v.findViewById(R.id.playButton);
        playButton.setOnCheckedChangeListener(playListener);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ScrubberCallback)
            callback = (ScrubberCallback)getActivity();
    }
}
