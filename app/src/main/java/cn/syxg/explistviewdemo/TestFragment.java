package cn.syxg.explistviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

/**
 * Created by Administrator on 2018/10/12.
 */

public class TestFragment extends Fragment{
    private CustomExpandListview listview;
    private MyAdapter myAdapter;
    View LV;

    private ArrayList<String> parent = new ArrayList<>();
    private ArrayList<String> parentS = new ArrayList<>();
    private Map<String, ArrayList<String>> datas = new HashMap<>();

    private PinnedHeaderItemDecoration mHeaderItemDecoration;
    private RecyclerView mRecyclerView;
    private TextView tvStickyHeaderView;
    //NormalAdapter adapter;
    StockAdapter mAdapter;

    TreeAdapter adapter;

    Button btn;
    List list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fg_layout,null,false);

        mRecyclerView = view.findViewById(R.id.rl);
       // btn = view.findViewById(R.id.btn);
        //getApplicationInfo().dataDir


/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              getActivity().startActivityForResult(new Intent(getActivity(),ResultActivity.class),10005);
              //startActivityForResult(new Intent(getActivity(),ResultActivity.class),10005);
                //getActivity().finish();

                //startActivity(new Intent(getActivity(),ResultActivity.class));

            }
        });*/

       /* for(int i=0;i<66;i++){
            parent.add("date : "+i);
        }*/

        //adapter = new NormalAdapter(getActivity(), parent);
       /* mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new StickHeaderDecoration(mRecyclerView));*/

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //tvStickyHeaderView = (TextView) view.findViewById(R.id.tv_sticky_header_view);

       /* tvStickyHeaderView = (TextView) view.findViewById(R.id.tv_sticky_header_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StickyExampleAdapter(getActivity(),getData());

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickview = recyclerView.findChildViewUnder(0 , 0);
                if(stickview!=null&&stickview.getContentDescription()!=null){
                    if(!TextUtils.equals(tvStickyHeaderView.getText(),String.valueOf(stickview.getContentDescription()))){
                        tvStickyHeaderView.setText(String.valueOf(stickview.getContentDescription()));
                    }
                }
                final View  transInfoView= recyclerView.findChildViewUnder(
                        0, tvStickyHeaderView.getHeight()+1 );
                transInfoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("BBB","点击="+transInfoView.getTop());
                    }
                });
                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int top = transInfoView.getTop();

                    if (transViewStatus == StickyExampleAdapter.HAS_STICKY_VIEW) {
                        if (top > 0) {
                            int dealtY =  top- tvStickyHeaderView.getMeasuredHeight();
                            tvStickyHeaderView.setTranslationY(dealtY);
                            Log.d("BBB","transInfoView++++="+transInfoView.getTop());
                        } else {
                            tvStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickyExampleAdapter.NONE_STICKY_VIEW) {
                        tvStickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });*/

        //View LV = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,null,false);
        // LV = getLayoutInflater().inflate(R.layout.activity_main, (ViewGroup) mRecyclerView.getParent(), false);
        //listview = (CustomExpandListview) LV.findViewById(R.id.listview);
        //listview.setGroupIndicator(null);
       /* listview = (CustomExpandListview) view.findViewById(R.id.listview);

        listview.setGroupIndicator(null);

        //模拟数据
        //for (int i = 0; i < parentSource.length; i++) {
        parent.add("08月");
        parent.add("07月");
        parent.add("06月");
        parent.add("05月");
        parent.add("04月");
        //}

        for (int i = 0; i < parent.size(); i++) {
            String str = parent.get(i);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                temp.add("" + j);
            }
            datas.put(str, temp);
        }

        //datas.put("03月",new ArrayList<String>());


        myAdapter = new MyAdapter(getContext(), parent, datas, listview);
        listview.setAdapter(myAdapter);

        //View view = LayoutInflater.from(getContext()).inflate(R.layout.heard_item,null);

        listview.addHeaderView(view);

        listview.setHeaderView(getLayoutInflater().inflate(
                R.layout.parent_layout, listview, false));

        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getContext(), "点击了第" + (i + 1) + " 类的第" + i1 + "项", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

        return view;
    }


    public List<StickyBean> getData() {
        List<StickyBean> stickyExampleModels = new ArrayList<>();

        for (int index = 0; index < 100; index++) {
            if (index < 15) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本1", "name" + index, "gender" + index));
            } else if (index < 25) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本2", "name" + index, "gender" + index));
            } else if (index < 35) {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本3", "name" + index, "gender" + index));
            } else {
                stickyExampleModels.add(new StickyBean(
                        "吸顶文本4", "name" + index, "gender" + index));
            }
        }

        return stickyExampleModels;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("resultAAfg",requestCode+">>"+resultCode);
    }

    boolean flag ;
    List<StockEntity.StockInfo> data;
    StockEntity stockEntity;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mRecyclerView.setItemAnimator(new FlipInTopXAnimator());
        list = new ArrayList();
        adapter = new TreeAdapter(getActivity(), list, R.layout.item,
                new int[]{R.id.tvText});
        //这里的点击事件很重要
        adapter.setOnItemClickLitener(new TreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (list.get(position) instanceof ParentEntity){//判断是否为父
                    ParentEntity parent = (ParentEntity) list.get(position);
                    if ((position + 1) == list.size()) {//判断是否为最后一个元素
                        adapter.addAllChild(parent.getChildren(), position + 1);
                    } else {
                        if (list.get(position + 1) instanceof ParentEntity) {//如果是父则表示为折叠状态需要添加儿子
                            adapter.addAllChild(parent.getChildren(), position + 1);
                        } else if (list.get(position + 1) instanceof ParentEntity.ChildEntity) {//如果是儿子则表示为展开状态需要删除儿子
                            adapter.deleteAllChild(position + 1, parent.getChildren().size());
                        }
                    }
                }else {//是儿子你想干啥就干啥吧
                    ParentEntity.ChildEntity child = (ParentEntity.ChildEntity) list.get(position);
                    Toast.makeText(getActivity(), child.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new StickHeaderDecoration(mRecyclerView));
        initData();




      /*  adapter.setOnItemClickLitener(new TreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position+" ", Toast.LENGTH_SHORT).show();
            }
        });*/




        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter = new StickyExampleAdapter(getActivity(),getData());

        //mRecyclerView.setAdapter(adapter);
       /* mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickview = recyclerView.findChildViewUnder(0 , 0);
                if(stickview!=null&&stickview.getContentDescription()!=null){
                    if(!TextUtils.equals(tvStickyHeaderView.getText(),String.valueOf(stickview.getContentDescription()))){
                        tvStickyHeaderView.setText(String.valueOf(stickview.getContentDescription()));
                    }
                }
                final View  transInfoView= recyclerView.findChildViewUnder(
                        0, tvStickyHeaderView.getHeight()+1 );
                transInfoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("BBB","点击="+transInfoView.getTop());
                    }
                });
                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int top = transInfoView.getTop();

                    if (transViewStatus == StickyExampleAdapter.HAS_STICKY_VIEW) {
                        if (top > 0) {
                            int dealtY =  top- tvStickyHeaderView.getMeasuredHeight();
                            tvStickyHeaderView.setTranslationY(dealtY);
                            Log.d("BBB","transInfoView++++="+transInfoView.getTop());
                        } else {
                            tvStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickyExampleAdapter.NONE_STICKY_VIEW) {
                        tvStickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });*/

       /* adapter.setOnItemClickListener(new StickyExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();

            }
        });*/



       /* for (int i = 0; i < 100; i++) {

            parentS.add("asdadas"+i);
        }

        parent.add("08月");
        parent.add("07月");
        parent.add("06月");
        parent.add("05月");
        parent.add("04月");

        for (int i = 0; i < parent.size(); i++) {
            String str = parent.get(i);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                temp.add("" + j);
            }
            datas.put(str, temp);
        }

        myAdapter = new MyAdapter(getContext(), parent, datas, listview);
        listview.setAdapter(myAdapter);
        listview.setHeaderView(getLayoutInflater().inflate(
                R.layout.parent_layout, listview, false));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RlAdapter adapter = new RlAdapter(R.layout.item_view,parentS);

        adapter.addHeaderView(LV);
        mRecyclerView.setAdapter(adapter);*/


        /*listview = (CustomExpandListview) getActivity().findViewById(R.id.LV);

        listview.setGroupIndicator(null);

        //模拟数据
        //for (int i = 0; i < parentSource.length; i++) {
        parent.add("08月");
        parent.add("07月");
        parent.add("06月");
        parent.add("05月");
        parent.add("04月");
        //}

        for (int i = 0; i < parent.size(); i++) {
            String str = parent.get(i);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                temp.add("" + j);
            }
            datas.put(str, temp);
        }

        //datas.put("03月",new ArrayList<String>());


        myAdapter = new MyAdapter(getContext(), parent, datas, listview);
        listview.setAdapter(myAdapter);

        //View view = LayoutInflater.from(getContext()).inflate(R.layout.heard_item,null);

        //listview.addHeaderView(view);

        listview.setHeaderView(getLayoutInflater().inflate(
                R.layout.parent_layout, listview, false));

        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getContext(), "点击了第" + (i + 1) + " 类的第" + i1 + "项", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/



     /*   new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                OnHeaderClickAdapter clickAdapter = new OnHeaderClickAdapter() {

                    @Override
                    public void onHeaderClick(View view, int id, int position) {
                        switch (id) {
                            case R.id.fl:
                                // case OnItemTouchListener.HEADER_ID:
                                Toast.makeText(getActivity(), "click, tag: " + mAdapter.getData().get(position).pinnedHeaderName, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.iv_more:
                                Toast.makeText(getActivity(), "click " + mAdapter.getData().get(position).pinnedHeaderName + "'s more button"+position, Toast.LENGTH_SHORT)
                                        .show();
*//*
                                    if(position == 0){

                                        if (flag){

                                            //data.remove()


                                        }else {

                                            //data.add(new StockEntity.StockInfo(StockEntity.StockInfo.TYPE_HEADER, "涨幅榜"));
                                            for (StockEntity.StockInfo info : stockEntity.increase_list) {
                                                info.setItemType(StockEntity.StockInfo.TYPE_DATA);
                                                //data.add(info);
                                                data.add(1,info);
                                            }

                                            mAdapter.notifyDataSetChanged();

                                            flag = true;
                                        }

                                    }*//*
                                break;

                        }
                    }

                };

                mHeaderItemDecoration = new PinnedHeaderItemDecoration.Builder(StockEntity.StockInfo.TYPE_HEADER).setDividerId(R.drawable.divider).enableDivider(true)
                        .setClickIds().disableHeaderClick(false).setHeaderClickListener(clickAdapter).create();
                mRecyclerView.addItemDecoration(mHeaderItemDecoration);

            }

            @Override
            protected String doInBackground(Void... voids) {
                return getStrFromAssets("rasking.json");
            }

            @Override
            protected void onPostExecute(String result) {

                Gson gson = new Gson();

                //final StockEntity stockEntity = gson.fromJson(result, StockEntity.class);
                 stockEntity = gson.fromJson(result, StockEntity.class);

                 data = new ArrayList<>();
               // List<StockEntity.StockInfo> data = new ArrayList<>();

                data.add(new StockEntity.StockInfo(StockEntity.StockInfo.TYPE_HEADER, "涨幅榜"));
                for (StockEntity.StockInfo info : stockEntity.increase_list) {
                    info.setItemType(StockEntity.StockInfo.TYPE_DATA);
                    data.add(info);
                }

                data.add(new StockEntity.StockInfo(StockEntity.StockInfo.TYPE_HEADER, "跌幅榜"));
                for (StockEntity.StockInfo info : stockEntity.down_list) {
                    info.setItemType(StockEntity.StockInfo.TYPE_DATA);
                    data.add(info);
                }

                data.add(new StockEntity.StockInfo(StockEntity.StockInfo.TYPE_HEADER, "换手率"));
                for (StockEntity.StockInfo info : stockEntity.change_list) {
                    info.setItemType(StockEntity.StockInfo.TYPE_DATA);
                    data.add(info);
                }

                data.add(new StockEntity.StockInfo(StockEntity.StockInfo.TYPE_HEADER, "振幅榜"));
                for (StockEntity.StockInfo info : stockEntity.amplitude_list) {
                    info.setItemType(StockEntity.StockInfo.TYPE_DATA);
                    data.add(info);
                }

                mAdapter = new StockAdapter(data);
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                    @Override
                    public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                       *//* if (view instanceof CheckBox) {
                            ((CheckBox) view).setChecked(!((CheckBox) view).isChecked());
                            mAdapter.getData().get(i).check = ((CheckBox) view).isChecked();
                            if (mHeaderItemDecoration.getPinnedHeaderView() != null && mHeaderItemDecoration.getPinnedHeaderPosition() >= i + mAdapter
                                    .getHeaderLayoutCount()) {
                                ((CheckBox) mHeaderItemDecoration.getPinnedHeaderView().findViewById(view.getId())).setChecked(((CheckBox) view).isChecked());
                            }


                        }*//*
                    }
                });


                //mAdapter.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.item_data, mRecyclerView, false));
                // 因为添加了1个头部，他是不在clickAdapter.getData这个数据里面的，所以这里要设置数据的偏移值告知ItemDecoration真正的数据索引
                mHeaderItemDecoration.setDataPositionOffset(mAdapter.getHeaderLayoutCount());


               *//* mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();

                    }
                });
*//*


            }

        }.execute();*/
    }


    public void initData(){
        for (int i = 0; i < 10; i++){
            ParentEntity parent = new ParentEntity();
            parent.setId(i);
            parent.setName("我是父" + i);
            List<ParentEntity.ChildEntity> children = new ArrayList<>();
            for (int j = 0; j < 4; j++){
                ParentEntity.ChildEntity child = new ParentEntity.ChildEntity();
                child.setId(j);
                child.setName("我是父 "+i+"的儿子" + j);
                children.add(child);
            }
            parent.setChildren(children);
            list.add(parent);
        }
        adapter.notifyDataSetChanged();
    }

      /* @return Json数据（String）
            * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
            */
    private String getStrFromAssets(String name) {

        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream is = assetManager.open(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private Context mContext;

        public RecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ViewHolder(view);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
           holder.mView.setText(parent.get(position));

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView mView;

            public ViewHolder(View view) {
                super(view);
                mView = view.findViewById(R.id.textView);
            }
        }
    }
}
