package elements.rogue.smartlog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import elements.rogue.smartlog.R;

public class LogViewFragment extends Fragment {

    private TextView txtSituation;
    private TextView txtDescription;
    private TextView txtComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_view, container, false);

        txtSituation = view.findViewById(R.id.txtSituation);
        txtDescription = view.findViewById(R.id.txtDescription);
        txtComment = view.findViewById(R.id.txtComment);

//        TextView textView = (TextView) view.findViewById(R.id.section_label);
//        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        txtSituation.setText(getArguments().getString("situation"));
        txtDescription.setText(getArguments().getString("description"));
        txtComment.setText(getArguments().getString("comment"));

        return view;
    }
}
