package com.example.android.pmpdexamen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.pmpdexamen.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textView)).setText(mItem.details);
        }

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);

        Intent intentResultado = (new Intent()).putExtra("resultado", "OK");
        getActivity().setResult(Activity.RESULT_OK, intentResultado);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        mListener.cerrar();
    }

    private MyFragmentListener mListener;

    //Interfaz definida para indicar a las activities que estefragment se cierra
    public interface MyFragmentListener {
        void cerrar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MyFragmentListener) context;
    }

}
