package cn.syxg.explistviewdemo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */

public class NormalAdapter  extends RecyclerView.Adapter<NormalAdapter.InnerHolder> implements StickHeaderDecoration.StickHeaderInterface{

    //新增开始
    NormalAdapter(Activity activity, List<String> dates){
        setHasStableIds(true);
        this.activity = activity;
        this.dates = dates;
    }

    //必须重写  不然item会错乱
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isStick(int position) {
        return position % 6 == 0;
    }
    //新增结束

    Activity activity;
    private List<String> dates;

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item, parent,
                false);
        return new InnerHolder(inflate);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private TreeAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickLitener(TreeAdapter.OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(final InnerHolder holder, int position) {
        if(isStick(position)){
            holder.itemView.setBackgroundResource(R.color.colorAccent);
            holder.tvText.setText(position / 6 +"");
        }else{
            holder.itemView.setBackgroundResource(R.color.colorPrimary);
            holder.tvText.setText(dates.get(position));
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
        return dates.size();
    }

    class InnerHolder extends RecyclerView.ViewHolder{
        TextView tvText;
        public InnerHolder(View itemView) {
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}
