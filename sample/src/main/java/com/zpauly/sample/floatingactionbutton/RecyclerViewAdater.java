package com.zpauly.sample.floatingactionbutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zpauly.materialcomponents.buttons.FlatButton;
import com.zpauly.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-10.
 */
public class RecyclerViewAdater extends RecyclerView.Adapter<RecyclerViewAdater.MyViewHolder> {
    private Context mContext;
    private View mView;

    public RecyclerViewAdater(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.fab_recyclerview_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText("FlatButton" + position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        /*@Bind(R.id.flatbutton)
        protected FlatButton mFlatButton;*/
        @Bind(R.id.fab_recyclerview_item_text)
        protected TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
