package com.altik.musicplayer.app;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;


public class LibraryActivity extends Activity implements MediaScrubberFragment.ScrubberCallback
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        playerA = new MediaPlayer();
        try
        {
            playerA.reset();
            String stg = Environment.getRootDirectory().getPath() + "/Music/Other Music/Queen-Under_Pressure.mp3";
            Log.d("String!", stg);
            playerA.setDataSource(stg);
            playerA.prepare();
        }
        catch (IOException e)
        {
            Log.e("ERROR", e.getMessage());
        }
        //playerB = MediaPlayer.create(this, Uri.parse("/storage/emulated/0/Music/Tim & Eric/Tim and Eric - 44 Salame Rock.mp3"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    // TODO: something of a hack for now
    private MediaPlayer playerA;
    private MediaPlayer playerB;
    @Override
    public void Play()
    {
        playerA.start();
    }

    @Override
    public void Pause()
    {
        playerA.pause();
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
