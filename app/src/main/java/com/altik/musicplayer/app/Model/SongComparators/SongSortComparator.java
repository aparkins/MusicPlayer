package com.altik.musicplayer.app.Model.SongComparators;

import com.altik.musicplayer.app.Model.SongCache;

import java.util.Comparator;

/**
 * Created by Altik_0 on 5/22/2014.
 */

// Intended to act as a base class for future song comparators, which sort on
// differing components. This one simply does normal comparisons of Integers,
// effectively sorting over Ids.
public class SongSortComparator implements Comparator<SongCache.SongCacheLine>
{
    @Override
    public int compare(SongCache.SongCacheLine line1, SongCache.SongCacheLine line2)
    {
        if (line1.ID > line2.ID)
            return 1;

        if (line1.ID < line2.ID)
            return -1;

        return 0;
    }
}
