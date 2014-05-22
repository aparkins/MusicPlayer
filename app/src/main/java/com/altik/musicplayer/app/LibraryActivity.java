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
    LibraryModel model;
    private MediaPlayer[] players;
    private int curPlayer = 0;
    private int songPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        model = LibraryModel.getInstance(this);
        players = new MediaPlayer[2];
        players[0] = new MediaPlayer();
        players[1] = new MediaPlayer();
        players[0].setOnCompletionListener(this);
        players[1].setOnCompletionListener(this);
        SetPresentSongById(model.GetSongIdForPosition(songPos));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    private void SetPresentSongById(int id)
    {
        try
        {
            MediaPlayer current = players[curPlayer];
            boolean shouldStartPlaying = current.isPlaying();
            current.reset();
            String stg = LibraryModel.getInstance(this).GetPathFromSongId(id);
            Log.d("New path!", stg);
            current.setDataSource(stg);
            current.prepare();
            RefreshNextSong();
            if (shouldStartPlaying)
                current.start();
        }
        catch (IOException e)
        {
            Log.e("Error setting new song", e.getMessage());
        }
    }

    private void RefreshNextSong()
    {
        SetNextSongById(model.GetSongIdForPosition(songPos + 1));
    }

    private void SetNextSongById(int id)
    {
        try
        {
            MediaPlayer next = players[curPlayer ^ 0x1];
            next.reset();
            String stg = LibraryModel.getInstance(this).GetPathFromSongId(id);
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
        songPos++;
        SetPresentSongById(model.GetSongIdForPosition(songPos));
    }

    @Override
    public void SkipBack()
    {
        if (players[curPlayer].getCurrentPosition() > 4000)
            players[curPlayer].seekTo(0);
        else
        {
            songPos--;
            SetPresentSongById(model.GetSongIdForPosition(songPos));
        }
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
        songPos = model.GetPositionForSongId(ID);
        SetPresentSongById(ID);
        if (!players[curPlayer].isPlaying())
            players[curPlayer].start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        curPlayer ^= 0x1;
        songPos++;
        SetNextSongById(model.GetSongIdForPosition(songPos));
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
