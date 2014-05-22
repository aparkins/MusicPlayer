package com.altik.musicplayer.app.Model;

import android.content.Context;

import com.altik.musicplayer.app.Model.SongComparators.SongSortComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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

    //private ArrayList<SongCacheLine> cacheEntries;
    private HashMap<Integer, SongCacheLine> cacheEntries;

    public SongCache()
    {
        cacheEntries = new HashMap<Integer, SongCacheLine>();
    }

    public void AddCacheLine(int id, String title)
    {
        SongCacheLine toAdd = new SongCacheLine(id, title);
        cacheEntries.put(id, toAdd);
    }

    public ArrayList<Integer> GetSortedIdArray(final SongSortComparator comparator)
    {
        ArrayList<Integer> toRet = new ArrayList<Integer>(cacheEntries.keySet());

        // We build our own comparator, which dereferences the hashed values
        Comparator<Integer> myComparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer i1, Integer i2)
            {
                return comparator.compare(cacheEntries.get(i1), cacheEntries.get(i2));
            }
        };

        Collections.sort(toRet, myComparator);
        return toRet;
    }

    public ArrayList<SongCacheLine> GetEntries()
    {
        return new ArrayList<SongCacheLine>(cacheEntries.values());
    }

    public SongCacheLine GetEntryFromId(int id)
    {
        return cacheEntries.get(id);
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
