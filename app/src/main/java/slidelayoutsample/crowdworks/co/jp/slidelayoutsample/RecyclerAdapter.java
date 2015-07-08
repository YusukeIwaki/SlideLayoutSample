package slidelayoutsample.crowdworks.co.jp.slidelayoutsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 100;
    private static final int TYPE_ITEM = 1;
    private int mHeaderSize = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView itemText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView)itemView.findViewById(R.id.recycler_view_item);
        }
    }

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    public void setHeaderSize(int headerSize){
        mHeaderSize = headerSize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            final View v = new View(parent.getContext());
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,mHeaderSize));
            v.setBackgroundColor(0xff000000);
            return new RecyclerAdapter.ViewHolder(v);
        }
        else if(viewType==TYPE_ITEM) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new RecyclerAdapter.ViewHolder(view);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final int viewType = getItemViewType(position);
        if(viewType==TYPE_HEADER){
            if(viewHolder.itemView.getHeight()!=mHeaderSize) {
                viewHolder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeaderSize));
            }
        }
        else if(viewType==TYPE_ITEM) {
            RecyclerAdapter.ViewHolder vh = (RecyclerAdapter.ViewHolder) viewHolder;
            String itemText = mItemList.get(position);
            vh.itemText.setText(itemText);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mItemList.size()) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount()+1;
    }

}