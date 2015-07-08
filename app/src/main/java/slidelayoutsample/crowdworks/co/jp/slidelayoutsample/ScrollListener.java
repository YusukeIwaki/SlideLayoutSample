package slidelayoutsample.crowdworks.co.jp.slidelayoutsample;

import android.support.v7.widget.RecyclerView;

abstract class ScrollListener extends RecyclerView.OnScrollListener {
    private int mBarHeight = 0;
    private int mToolbarOffset = 0;

    public void setBarHeight(int barHeight){
        mBarHeight = barHeight;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(mToolbarOffset > mBarHeight) {
            mToolbarOffset = mBarHeight;
        } else if(mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }


        onMove(mToolbarOffset);

        if((mToolbarOffset <mBarHeight && dy>0) || (mToolbarOffset >0 && dy<0)) {
            mToolbarOffset += dy;
        }
    }


    public abstract void onMove(int dy);
}
