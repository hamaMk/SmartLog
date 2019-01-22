package elements.rogue.smartlog.pagers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import elements.rogue.smartlog.fragments.LogViewFragment;
import elements.rogue.smartlog.types.Log;

public class LogViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Log> logs;
    private int currentPos;

    public LogViewPagerAdapter(FragmentManager fm, List<Log> logs, int currentPos) {
        super(fm);
//        fm.beginTransaction().replace()
        this.logs = logs;
        this.currentPos = currentPos;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("situation", logs.get(position).getSituation());
        bundle.putString("description", logs.get(position).getDescrition());
        bundle.putString("comment", logs.get(position).getComment());
//        bundle.putString("position", String.valueOf(currentPos));
        LogViewFragment fragment = new LogViewFragment();
        fragment.setArguments(bundle);

//        if (position == currentPos)return fragment;

        return fragment;
    }


    @Override
    public int getCount() {
        return logs.size();
    }
}
