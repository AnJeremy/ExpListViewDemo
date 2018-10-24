package cn.syxg.explistviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpf on 2018/1/16.
 */

public class PerformerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * context
     */
    public Context mContext;

    /**
     * 集合
     */
    public List<Performer> mPerformers = new ArrayList<>();

    public PerformerListAdapter(Context context, List<Performer> performers) {
        this.mContext = context;
        this.mPerformers = performers;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private TreeAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickLitener(TreeAdapter.OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 10) {
            return new ContentVH(LayoutInflater.from(mContext).inflate(R.layout.adapter_content, parent, false));
        }

        return new TitleVH(LayoutInflater.from(mContext).inflate(R.layout.adapter_title, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Performer performer = mPerformers.get(position);

        if (holder instanceof ContentVH) {
            ((ContentVH) holder).bindData(performer);
        }
        if (holder instanceof TitleVH) {
            ((TitleVH) holder).bindData(performer);
           /* ((TitleVH) holder).iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,position+"titile",Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPerformers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mPerformers.get(position).getItemType();
    }


    static class TitleVH extends RecyclerView.ViewHolder {

        TextView mTv;
        ImageView iv;

        public TitleVH(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.text);
            iv = itemView.findViewById(R.id.iv);
            itemView.setTag(true);
        }

        public void bindData(Performer performer) {
            mTv.setText(performer.getName());
        }

    }

    static class ContentVH extends RecyclerView.ViewHolder {

        TextView mTv;

        public ContentVH(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.text);
            itemView.setTag(false);
        }

        public void bindData(Performer performer) {
            mTv.setText(performer.getName());
        }

    }
}
