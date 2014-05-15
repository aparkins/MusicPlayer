package com.altik.musicplayer.app.Adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Altik_0 on 5/14/2014.
 */
public class LibraryListAdapter extends BaseAdapter
{
    LinkedList<String> songTitles;
    Context c;

    public LibraryListAdapter(Context _c)
    {
        c = _c;
        songTitles = new LinkedList<String>();

        // Track down all the music titles:
        ContentResolver resolver = c.getContentResolver();
        String[] projection = new String[] {MediaStore.Audio.AudioColumns.TITLE,
                                            MediaStore.MediaColumns.DATA};

        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            do
            {
                songTitles.add(cursor.getString(0));
                //Log.i("Title - IS_MUSIC", cursor.getString(0) + " - " + cursor.getString(1));
            } while(cursor.moveToNext());
        }
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int i)
    {
        return true;
    }

    @Override
    public int getCount()
    {
        return songTitles.size();
    }

    @Override
    public Object getItem(int i)
    {
        return songTitles.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        // TODO
        return i;
    }

    @Override
    public boolean hasStableIds()
    {
        // TODO
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        // TODO
        if (view == null || !(view instanceof TextView))
            view = new TextView(c);
        ((TextView)view).setText(songTitles.get(i));
        return view;
    }

    @Override
    public int getItemViewType(int i)
    {
        // TODO
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        // TODO
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return songTitles.isEmpty();
    }
}
