package com.altik.musicplayer.app;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.altik.musicplayer.app.Model.LibraryModel;

import java.io.IOException;


public class LibraryActivity extends Activity
        implements MediaScrubberFragment.ScrubberCallback,
                   LibraryListFragment.LibraryFragmentCallback,
                   MediaPlayer.OnCompletionListener
{

    // TODO: something of a hack for now
    private MediaPlayer[] players;
    private int curPlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        players = new MediaPlayer[2];
        players[0] = new MediaPlayer();
        players[1] = new MediaPlayer();
        players[0].setOnCompletionListener(this);
        players[1].setOnCompletionListener(this);
        MediaPlayer current = players[curPlayer];
        try
        {
            current.reset();
            String stg = "/storage/emulated/0/Music/Dream Theater/(2002) Six Degrees of Inner Turbulence/CD2/03 Dream Theater - III War Inside My Head.mp3";
            Log.d("String!", stg);
            current.setDataSource(stg);
            current.prepare();
        }
        catch (IOException e)
        {
            Log.e("ERROR", e.getMessage());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    @Override
    public void Play()
    {
        players[curPlayer].start();
    }

    @Override
    public void Pause()
    {
        players[curPlayer].pause();
    }

    @Override
    public void SkipForward()
    {
        // TODO
    }

    @Override
    public void SkipBack()
    {
        // TODO
    }

    @Override
    public int GetSongProgress()
    {
        return players[curPlayer].getCurrentPosition();
    }

    @Override
    public int GetSongDuration()
    {
        return players[curPlayer].getDuration();
    }

    @Override
    public void SetSongProgress(int seekPos)
    {
        players[curPlayer].seekTo(seekPos);
    }

    @Override
    public void SongIdSelected(int ID)
    {

        try
        {
            MediaPlayer next = players[curPlayer ^ 0x1];
            next.reset();
            String stg = LibraryModel.getInstance(this).GetPathFromSongId(ID);
            Log.d("New path!", stg);
            next.setDataSource(stg);
            next.prepare();
            players[curPlayer].setNextMediaPlayer(next);
        }
        catch (IOException e)
        {
            Log.e("Error setting new song", e.getMessage());
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        curPlayer ^= 0x1;
        // TODO: set next mediaPlayer
    }


    /* TODO
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.library, menu);
        return true;
    }
    */

    /* TODO
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
