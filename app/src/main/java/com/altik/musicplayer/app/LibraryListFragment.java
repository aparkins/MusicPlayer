package com.altik.musicplayer.app;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.altik.musicplayer.app.Adapters.LibraryListAdapter;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class LibraryListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{

    // TODO: Rename and change types of parameters
    public static LibraryListFragment newInstance()
    {
        LibraryListFragment fragment = new LibraryListFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LibraryListFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setListAdapter(new LibraryListAdapter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle)
    {
        View v = super.onCreateView(inflater, vg, bundle);

        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(
                            getActivity(),                // Current context
                            android.R.layout.simple_list_item_1,  // Layout for a single row
                            null,                // No Cursor yet
                            null,        // Cursor columns to use
                            null,           // Layout fields to use
                            0                    // No flags
        );

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        // TODO
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle)
    {
        // TODO: check loaderID
        return new CursorLoader(getActivity(),
                                MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                                null, null, null, null);
    }

    SimpleCursorAdapter mAdapter;

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
    {
        mAdapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader)
    {

    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
