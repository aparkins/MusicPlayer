package com.altik.musicplayer.app.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Altik_0 on 5/15/2014.
 */
public class SongCache
{
    public class SongCacheLine
    {
        public long ID;
        public String Title;
        // TODO: more cache data?

        public SongCacheLine(int _id, String _title)
        {
            ID = _id;
            Title = _title;
        }
    }

    private ArrayList<SongCacheLine> cacheEntries;

    public SongCache()
    {
        cacheEntries = new ArrayList<SongCacheLine>();
    }

    public void AddCacheLine(int id, String title)
    {
        SongCacheLine toAdd = new SongCacheLine(id, title);
        cacheEntries.add(toAdd);
    }

    public ArrayList<SongCacheLine> GetEntries()
    {
        return new ArrayList<SongCacheLine>(cacheEntries);
    }

    public SongCacheLine GetEntry(int i)
    {
        return cacheEntries.get(i);
    }

    public int EntryCount()
    {
        return cacheEntries.size();
    }

    public boolean IsEmpty()
    {
        return cacheEntries.isEmpty();
    }
}
