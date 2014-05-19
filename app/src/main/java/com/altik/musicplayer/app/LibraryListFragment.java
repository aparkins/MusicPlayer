package com.altik.musicplayer.app;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.altik.musicplayer.app.Adapters.LibraryListAdapter;


/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class LibraryListFragment extends ListFragment
{
    private static interface LibraryFragmentCallback
    {
        public void SongIdSelected(long ID);
        // TODO: others
    }

    private LibraryFragmentCallback songSelectionListener;

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

        // Create list adapter:
        setListAdapter(new LibraryListAdapter(getActivity()));

        // We require our parent activity implement LibraryFragmentCallback:
        if (!(getActivity() instanceof LibraryFragmentCallback))
        {
            // TODO - error
        }
        songSelectionListener = (LibraryFragmentCallback)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle bundle)
    {
        View v = super.onCreateView(inflater, vg, bundle);

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        // TODO
    }
}
