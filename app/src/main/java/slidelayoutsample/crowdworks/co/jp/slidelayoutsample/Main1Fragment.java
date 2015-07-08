package slidelayoutsample.crowdworks.co.jp.slidelayoutsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hugo.weaving.DebugLog;

public class Main1Fragment extends Fragment {

    View mRootView;

    public Main1Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState){
        mRootView = inflater.inflate(R.layout.fragment_main1,root,false);

        initializeListView();
        initializeTopBar();

        return mRootView;
    }

    private void initializeListView() {
        RecyclerView recyclerView = (RecyclerView)mRootView.findViewById(R.id.scroll_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initializeTopBar(){
        final View topbar = mRootView.findViewById(R.id.top_bar);
        final RecyclerView recyclerView = (RecyclerView)mRootView.findViewById(R.id.scroll_content);

        final ScrollListener l = new ScrollListener() {

            @DebugLog
            @Override
            public void onMove(int dy) {
                topbar.setY(-dy);
            }

            @DebugLog
            @Override
            public void onShow(){
                topbar.animate().translationY(0).start();
            }

            @DebugLog
            @Override
            public void onHide(){
                final int height = topbar.getHeight();
                topbar.animate().translationY(-height).start();
            }
        };
        recyclerView.addOnScrollListener(l);

        topbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)  {
                ((RecyclerAdapter) recyclerView.getAdapter()).setHeaderSize(bottom - top);
                l.setBarHeight(bottom - top);
                topbar.removeOnLayoutChangeListener(this);
            }
        });
    }

    public static ArrayList<String> createItemList(){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<30;i++){
            list.add("item - "+i);
        }
        return list;
    }
}
