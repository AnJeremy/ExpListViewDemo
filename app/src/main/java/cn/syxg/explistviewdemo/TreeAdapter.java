package cn.syxg.explistviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.MyViewHolder>implements StickHeaderDecoration.StickHeaderInterface{
    Context context;
    List list;
    Integer layout;
    int[] to;




    public TreeAdapter (Context context, List<Object> list, Integer layout, int[] to){
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.to = to;
    }

    //必须重写  不然item会错乱
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isStick(int position) {
        if (list.get(position) instanceof ParentEntity){//判断当前实体是否是Parent的实例

            return true;


        }else {//判断当前实体是否是Child的实例

            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    @Override
    public TreeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater
                .from(context).inflate(layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final TreeAdapter.MyViewHolder holder, int position) {

      /*  if (position == 0) {
            holder.tvStickyHeader.setVisibility(View.VISIBLE);
            holder.tvStickyHeader.setText(stickyBean.sticky);
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        } else {
            if (!TextUtils.equals(stickyBean.sticky, datas.get(position - 1).sticky)) {
                holder.tvStickyHeader.setVisibility(View.VISIBLE);
                holder.tvStickyHeader.setText(stickyBean.sticky);
                holder.itemView.setTag(HAS_STICKY_VIEW);
            } else {
                holder.tvStickyHeader.setVisibility(View.GONE);
                holder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
        //通过此处设置ContentDescription，作为内容描述，可以通过getContentDescription取出，功效跟setTag差不多。
        holder.itemView.setContentDescription(stickyBean.sticky);*/


      /*  if(position == 0){
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        }else {*/

            if(isStick(position)){
                holder.itemView.setBackgroundResource(R.color.colorAccent);
                //holder.child_name.setVisibility(View.GONE);
                //holder.parent_name.setVisibility(View.VISIBLE);
                ParentEntity parent = (ParentEntity) list.get(position);
                holder.parent_name.setText(parent.getName());
            }else {
                holder.itemView.setBackgroundResource(R.color.colorPrimary);
                //holder.parent_name.setVisibility(View.GONE);
                //holder.child_name.setVisibility(View.VISIBLE);
                ParentEntity.ChildEntity child = (ParentEntity.ChildEntity) list.get(position);
                holder.child_name.setText(child.getName());
            }
            /*if (list.get(position) instanceof ParentEntity){//判断当前实体是否是Parent的实例

                holder.child_name.setVisibility(View.GONE);
                holder.parent_name.setVisibility(View.VISIBLE);
                ParentEntity parent = (ParentEntity) list.get(position);
                holder.parent_name.setText(parent.getName());

            }else {//判断当前实体是否是Child的实例
                holder.parent_name.setVisibility(View.GONE);
                holder.child_name.setVisibility(View.VISIBLE);
                ParentEntity.ChildEntity child = (ParentEntity.ChildEntity) list.get(position);
                holder.child_name.setText(child.getName());

            }*/


        //}


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
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView parent_name;
        private TextView child_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            parent_name = (TextView) itemView.findViewById(to[0]);
            child_name = (TextView) itemView.findViewById(to[0]);
        }
    }

    /**
     * 添加所有child
     * @param lists
     * @param position
     */
    public void addAllChild(List<?> lists, int position) {
        list.addAll(position, lists);
        notifyItemRangeInserted(position, lists.size());
    }

    /**
     * 删除所有child
     * @param position
     * @param itemnum
     */
    public void deleteAllChild(int position, int itemnum) {
        for (int i = 0; i < itemnum; i++) {
            list.remove(position);
        }
        notifyItemRangeRemoved(position, itemnum);
    }
}
