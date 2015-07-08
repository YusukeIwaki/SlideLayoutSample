package slidelayoutsample.crowdworks.co.jp.slidelayoutsample;

import android.support.v7.widget.RecyclerView;

abstract class ScrollListener extends RecyclerView.OnScrollListener {
    private boolean mControlsVisible = true;

    private int mBarHeight = 0;
    private int mToolbarOffset = 0;

    public void setBarHeight(int barHeight){
        mBarHeight = barHeight;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (mControlsVisible) {
                if (mToolbarOffset > mBarHeight*7/8) {
                    setInvisible();
                } else {
                    setVisible();
                }
            } else {
                if ((mBarHeight - mToolbarOffset) > mBarHeight*3/4) {
                    setVisible();
                } else {
                    setInvisible();
                }
            }
        }
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


    private void setVisible() {
        if(mToolbarOffset > 0) {
            onShow();
            mToolbarOffset = 0;
        }
        mControlsVisible = true;
    }

    private void setInvisible() {
        if(mToolbarOffset < mBarHeight) {
            onHide();
            mToolbarOffset = mBarHeight;
        }
        mControlsVisible = false;
    }

    public abstract void onMove(int dy);
    public abstract void onShow();
    public abstract void onHide();
}
