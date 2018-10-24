package cn.syxg.explistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Administrator on 2018/10/16.
 */

public class ExpListAdapter extends BaseAdapter implements StickyListHeadersAdapter{


    private List<String> listStr=new ArrayList<>();
    private LayoutInflater inflater;
    private OnMyItemClickListener listener;

    private String[] mCountries;
    private int[] mSectionIndices;
    private Character[] mSectionLetters;


    public ExpListAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        mCountries = context.getResources().getStringArray(R.array.countries);
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        //this.listStr=listStr;
        //this.listStr = new ArrayList(listStr);//解决java.lang.UnsupportedOperationException
    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        char lastFirstChar = mCountries[0].charAt(0);
        sectionIndices.add(0);
        for (int i = 1; i < mCountries.length; i++) {
            if (mCountries[i].charAt(0) != lastFirstChar) {
                lastFirstChar = mCountries[i].charAt(0);
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    private Character[] getSectionLetters() {
        Character[] letters = new Character[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = mCountries[mSectionIndices[i]].charAt(0);
        }
        return letters;
    }


    /**
     * 内部接口回调方法
     */
    public interface OnMyItemClickListener {
        void onMyItemClick(int position, Object object);
        void onMyItemLongClick(int position, Object object);
    }
    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mCountries.length;
    }

    @Override
    public Object getItem(int position) {
        return mCountries[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.id_tv_item);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMyItemClick(position,listStr.get(position));
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onMyItemLongClick(position, listStr.get(position));
                    return false;
                }
            });
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(mCountries[position]);

        return convertView;

    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.id_tv_head_item);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        //String headerText = listStr.get(position).subSequence(0, 1).charAt(0)+" pos:" + position;
        CharSequence headerChar = mCountries[position].subSequence(0, 1);
        holder.text.setText(headerChar);
        return convertView;

    }

    @Override
    public long getHeaderId(int position) {
        return mCountries[position].subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }

    void addItem(){
        listStr.add("新增数据");
        notifyDataSetChanged();
    }
    void deleteItem(int position){
        listStr.remove(position);
        notifyDataSetChanged();
    }


}
