package com.altik.musicplayer.app.Model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import android.net.Uri;
import android.util.Log;

import com.altik.musicplayer.app.Model.SongComparators.TitleComparator;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Altik_0 on 5/19/2014.
 */
// Handles general purpose actions over the music library. Includes:
//  - Providing cached data for all entries in the library
//  - Searching for URI of songs in library, presumably for playing
//  - Determining next song to play, or tracking what previous chain of songs was
//  - Allowing temporary queueing of songs
//
// Because this class is responsible for interfacing with MediaStore, a
// Context must be provided. This is a singleton class to avoid costs on
// reinstantiation, however.
public class LibraryModel
{
    private Context c;
    private SongCache cache;
    private ArrayList<Integer> sortedIds;

    private static LibraryModel instance;

    public synchronized static LibraryModel getInstance(Context _c)
    {
        if (instance == null)
            instance = new LibraryModel(_c);
        return instance;
    }

    private LibraryModel(Context _c)
    {
        c = _c;
        cache = new SongCache();

        ContentResolver resolver = c.getContentResolver();
        String[] projection = new String[] {MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.IS_MUSIC};

        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            do
            {
                if(cursor.getInt(2) != 0)
                {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    Log.i("ID - title", Integer.toString(id) + " - " + title);
                    cache.AddCacheLine(id, title);
                }
            } while(cursor.moveToNext());
        }

        // Now that we've filled the cache, build our local sorted list:
        sortedIds = cache.GetSortedIdArray(new TitleComparator());

        // TODO: filter results by blacklist
        // TODO: sort results by whatever criteria
    }

    public synchronized int GetLibraryCount()
    {
        return sortedIds.size();
    }

    public synchronized SongCache.SongCacheLine GetSongCacheAtIndex(int i)
    {
        return cache.GetEntryFromId(sortedIds.get(i));
    }

    public synchronized boolean CacheIsEmpty()
    {
        return sortedIds.isEmpty();
    }

    public String GetPathFromSongId(int ID)
    {
        Log.i("ID to get path for", Integer.toString(ID));
        ContentResolver cr = c.getContentResolver();
        String[] projection = new String[] {MediaStore.MediaColumns.DATA};
        String filter = MediaStore.MediaColumns._ID + " = ?";

        Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                filter,
                new String[] {Integer.toString(ID)},
                null);

        if(cursor.moveToFirst())
            return cursor.getString(0);

        // If we didn't find one, return null
        return null;
    }
}
