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

import com.altik.musicplayer.app.Model.LibraryModel;
import com.altik.musicplayer.app.Model.SongCache;

import java.util.LinkedList;

/**
 * Created by Altik_0 on 5/14/2014.
 */
public class LibraryListAdapter extends BaseAdapter
{
    LibraryModel lm;
    Context c;

    public LibraryListAdapter(Context _c)
    {
        c = _c;
        lm = LibraryModel.getInstance(c);
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
        return lm.GetLibraryCount();
    }

    @Override
    public Object getItem(int i)
    {
        return lm.GetSongCacheAtIndex(i);
    }

    @Override
    public long getItemId(int i)
    {
        return lm.GetSongCacheAtIndex(i).ID;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        // TODO
        if (view == null || !(view instanceof TextView))
            view = new TextView(c);
        ((TextView)view).setText(((SongCache.SongCacheLine)getItem(i)).Title);
        ((TextView)view).setTextSize(24.0f);
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
        return lm.CacheIsEmpty();
    }
}
