package com.altik.musicplayer.app.Adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created by Altik_0 on 5/14/2014.
 */
public class LibraryListAdapter implements ListAdapter
{
    public LibraryListAdapter()
    {
        // First, track down all the music files! :D
        // TODO: having a bit of difficulty figuring this bit out atm
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
    public void registerDataSetObserver(DataSetObserver dataSetObserver)
    {
        // TODO
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver)
    {
        // TODO
    }

    @Override
    public int getCount()
    {
        // TODO
        return 0;
    }

    @Override
    public Object getItem(int i)
    {
        // TODO
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        // TODO
        return 0;
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
        return null;
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
        // TODO
        return false;
    }
}
