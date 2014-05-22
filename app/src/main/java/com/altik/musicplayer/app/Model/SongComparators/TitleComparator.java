package com.altik.musicplayer.app.Model.SongComparators;

import com.altik.musicplayer.app.Model.SongCache;

import java.util.regex.Pattern;

/**
 * Created by Altik_0 on 5/22/2014.
 */

// Sorts first by Title, then by ID.
public class TitleComparator extends SongSortComparator
{
    // Explanation of the regex:
    //  ( the | ... | an ) === This is referring to trimming out any leading terms
    //                         we don't want to affect our sort. E.g. 'the' or 'a'.
    //  \\s                === We want there to be a space after that word, which
    //                         ensures we don't treat "them" as equal to "m".
    //  .+                 === This means we want to ensure there is something else
    //                         left in the title, again ensuring the song "the" is
    //                         not sorted as the empty string.
    private static String trimRegex = "^(the|a|an)\\s(.+)$";

    @Override
    public int compare(SongCache.SongCacheLine line1, SongCache.SongCacheLine line2)
    {
        String title1 = line1.Title.trim().toLowerCase().replaceAll(trimRegex, "$2");
        String title2 = line2.Title.trim().toLowerCase().replaceAll(trimRegex, "$2");
        int compr = title1.compareTo(title2);
        if (compr == 0)
            return super.compare(line1, line2);

        return compr;
    }
}
