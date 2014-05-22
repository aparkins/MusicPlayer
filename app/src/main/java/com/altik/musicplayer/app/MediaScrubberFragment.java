package com.altik.musicplayer.app;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import java.io.InvalidClassException;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link MediaScrubberFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MediaScrubberFragment extends Fragment implements SeekBar.OnSeekBarChangeListener
{
    public static interface ScrubberCallback
    {
        public void Play();
        public void Pause();
        public void SkipForward();
        public void SkipBack();

        public int GetSongProgress();
        public int GetSongDuration();

        public void SetSongProgress(int seekPos);
        // TODO: more?
    }

    private ScrubberCallback callback;

    // Buttons!
    private ToggleButton playButton;

    // Seekbar!
    private SeekBar scrubber;
    private Handler handler;

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

    // Since the MediaPlayer doesn't provide a nice interface for getting position
    // updates, we'll just have to poll for it. :P
    private boolean shouldUpdate = true;
    private Runnable updateSeekBarAction = new Runnable()
    {
        @Override
        public void run()
        {
            if (shouldUpdate)
            {
                scrubber.setMax(callback.GetSongDuration());
                scrubber.setProgress(callback.GetSongProgress());
                handler.postDelayed(updateSeekBarAction, 100);
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

        scrubber = (SeekBar)v.findViewById(R.id.scrubber);
        scrubber.setOnSeekBarChangeListener(this);

        // Setup scrubber updating
        handler = new Handler();
        handler.postDelayed(updateSeekBarAction, 100);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof ScrubberCallback)
            callback = (ScrubberCallback)getActivity();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        // Nothing - our updating all gets taken care of in that damned poll loop.
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        // We want to make sure we stop updating the seekBar for a bit, so we'll just
        // turn that loop off by removing any actions from the handler
        handler.removeCallbacks(updateSeekBarAction);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        // First, alert our callback that we've changed the position
        callback.SetSongProgress(seekBar.getProgress());

        // Alert the handler that we'd like (well, maybe "like" isn't quite the right
        // word) to start polling again.
        handler.postDelayed(updateSeekBarAction, 100);
    }
}
