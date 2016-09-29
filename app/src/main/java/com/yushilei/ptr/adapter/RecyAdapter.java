package com.yushilei.ptr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yushilei.ptr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by  yushilei.
 * @time 2016/9/29 -11:03.
 * @Desc
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewH> {
    List<String> data = new ArrayList<>();
    Context mContext;
    int[] rids = new int[]{R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5, R.mipmap.a6, R.mipmap.a7, R.mipmap.a8, R.mipmap.a9, R.mipmap.a10};
    Random mRandom = new Random();

    public RecyAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 30; i++) {
            data.add("item+" + i);
        }
    }

    @Override
    public ViewH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        ViewH viewH = new ViewH(view);
        view.setTag(viewH);
        return viewH;
    }

    @Override
    public void onBindViewHolder(ViewH holder, int position) {
        int i = mRandom.nextInt(rids.length);
        holder.img.setImageResource(rids[i]);
        holder.tv.setText(data.get(position));
        holder.itemView.setTag(R.id.id_pos, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewH extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public ViewH(View itemView) {
            super(itemView);
            img = ((ImageView) itemView.findViewById(R.id.img));
            tv = ((TextView) itemView.findViewById(R.id.tv));
        }
    }
}
