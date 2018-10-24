package cn.syxg.explistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Administrator on 2018/10/12.
 */

public class TestF2 extends Fragment{

    private RecyclerView mRecyclerView;
    private MultiTypeAdapter adapter;

    //private RecyclerView rv;
    private TextView mSuspensionBar;

    private int mCurrentPosition = 0;




    private ExpandableStickyListHeadersListView mExpandableStickyListHeadersListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f2,null,false);
        //mRecyclerView = view.findViewById(R.id.rl);

        mExpandableStickyListHeadersListView = view.findViewById(R.id.ExpList);

        //mSuspensionBar = view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //startActivityForResult();


        StickyListHeadersAdapter stickyListHeadersAdapter =  new ExpListAdapter(getActivity());
        mExpandableStickyListHeadersListView.setAdapter(stickyListHeadersAdapter);
        mExpandableStickyListHeadersListView.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if (mExpandableStickyListHeadersListView.isHeaderCollapsed(headerId)) {
                    mExpandableStickyListHeadersListView.expand(headerId);
                } else {
                    mExpandableStickyListHeadersListView.collapse(headerId);
                }
            }
        });
        //ps：expandableStickyList的setOnItemClickListener不能用   github也有人问
       /* final ExpListAdapter myAdapter = (ExpListAdapter) stickyListHeadersAdapter;
        myAdapter.setOnMyItemClickListener(new MyAdapter.OnMyItemClickListener() {
            @Override
            public void onMyItemClick(int position, Object object) {
                Toast.makeText(MainActivity.this, "Click on item" + position, Toast.LENGTH_SHORT).show();

                myAdapter.addItem();
            }

            @Override
            public void onMyItemLongClick(int position, Object object) {
                Toast.makeText(MainActivity.this, "Click long  on item" + position, Toast.LENGTH_SHORT).show();
                myAdapter.deleteItem(position);
            }
        });*/


        //mRecyclerView = (RecyclerView) findViewById(R.id.rv);
       /* mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new StickyItemDecoration());
        mRecyclerView.addItemDecoration(new ListDividerItemDecoration());
        PerformerListAdapter adapter = new PerformerListAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new TreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
                if(getData().get(position).getItemType() != 10){

                    view.findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),position+"tt",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });*/



       // mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
/*
        adapter = new MultiTypeAdapter();

        //模拟数据
        List<Post> list = new ArrayList<>();
        int index = 0;
        int parentPostPos;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Post post = new Post("pos = " + index);
            parentPostPos = index;

            list.add(post);
            index++;

            int k = random.nextInt(5);
            post.comments = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                Comment comment = new Comment("pos = " + index, parentPostPos);
                post.comments.add(comment);
                index++;
            }
        }
        final Items items = new Items();
        items.addAll(flattenData(list));
        adapter.setItems(items);

        adapter.register(Post.class, new TextViewBinder());
        adapter.register(Comment.class, new TextViewBinder());

        adapter.setItems(items);
        mRecyclerView.setAdapter(adapter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            int mSuspensionHeight;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mSuspensionHeight = mSuspensionBar.getHeight();

                int firstVisPos = linearLayoutManager.findFirstVisibleItemPosition();

                Object firstVisibleItem = items.get(firstVisPos);
                Object nextItem = items.get(firstVisPos + 1);
                View nextView = linearLayoutManager.findViewByPosition(firstVisPos + 1);


                if (dy > 0) {
                    if (nextItem instanceof Post) {

                        if (nextView.getTop() <= mSuspensionHeight) {
                            //被顶掉的效果
                            mSuspensionBar.setY(-(mSuspensionHeight - nextView.getTop()));
                        } else {
                            mSuspensionBar.setY(0);
                        }
                    }

                    //判断是否需要更新悬浮条
                    if (mCurrentPosition != firstVisPos && firstVisibleItem instanceof Post) {
                        mCurrentPosition = firstVisPos;
                        //更新悬浮条
                        updateSuspensionBar();
                        mSuspensionBar.setY(0);
                    }
                } else {
                    // 1、nextItem -> Post and firstVisibleItem -> Comment       mCurrentPosition = ((Comment) firstVisibleItem).getParentPostPosition()
                    // 2、nextItem -> Post and firstVisibleItem -> Post          mCurrentPosition = firstVisPos
                    // 3、nextItem -> Comment and firstVisibleItem -> Comment    mSuspensionBar 不动
                    // 4、nextItem -> Comment and firstVisibleItem -> Post       mSuspensionBar 不动
                    if (nextItem instanceof Post) {
                        mCurrentPosition = firstVisibleItem instanceof Post ? firstVisPos : ((Comment) firstVisibleItem).getParentPostPosition();
                        updateSuspensionBar();

                        if (nextView.getTop() <= mSuspensionHeight) {
                            //被顶掉的效果
                            mSuspensionBar.setY(-(mSuspensionHeight - nextView.getTop()));
                        } else {
                            mSuspensionBar.setY(0);
                        }
                    }
                }
            }
        });

        //更新悬浮条
        updateSuspensionBar();

        mRecyclerView.setAdapter(adapter);*/

    }

    private void updateSuspensionBar() {
        String s = ((TextViewBean) adapter.getItems().get(mCurrentPosition)).getText();
        mSuspensionBar.setText(s);
    }

    private List<Object> flattenData(List<Post> posts) {
        final List<Object> items = new ArrayList<>();
        for (Post post : posts) {
        /* 将 post 加进 items，Binder 内部拿到它的时候，
         * 我们无视它的 comments 内容即可 */
            items.add(post);
        /* 紧接着将 comments 拿出来插入进 items，
         * 评论就能正好处于该条 post 下面 */
            items.addAll(post.comments);
        }
        return items;
    }


    private List<Performer> getData() {
        List<Performer> performers = new ArrayList<>();


        Performer performer = new Performer("香港明星");
        performers.add(performer);

        Performer ldh = new Performer("刘德华", 10);
        performers.add(ldh);
        Performer zxy = new Performer("张学友", 10);
        performers.add(zxy);
        Performer zrf = new Performer("周润发", 10);
        performers.add(zrf);
        Performer lcw = new Performer("梁朝伟", 10);
        performers.add(lcw);
        Performer wyj = new Performer("吴毅将", 10);
        performers.add(wyj);
        Performer lm = new Performer("黎明", 10);
        performers.add(lm);
        Performer cgx = new Performer("陈冠希", 10);
        performers.add(cgx);
        Performer gfc = new Performer("郭富城", 10);
        performers.add(gfc);
        Performer xtf = new Performer("谢霆锋", 10);
        performers.add(xtf);

        Performer performerTw = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw);

        Performer rxq = new Performer("任贤齐", 10);
        performers.add(rxq);
        Performer mtw = new Performer("孟庭苇", 10);
        performers.add(mtw);

        Performer performerTw2 = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw2);

        Performer rxq2 = new Performer("罗志祥", 10);
        performers.add(rxq2);

        Performer performerTw3 = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw3);

        Performer rxq3 = new Performer("李宗盛", 10);
        performers.add(rxq3);

        Performer performerNl = new Performer("内陆明星");
        performers.add(performerNl);

        Performer lh = new Performer("鹿晗", 10);
        performers.add(lh);
        Performer wzw = new Performer("王志文", 10);
        performers.add(wzw);
        Performer yq = new Performer("羽泉", 10);
        performers.add(yq);
        Performer lxl = new Performer("李小璐", 10);
        performers.add(lxl);
        Performer hh = new Performer("韩红", 10);
        performers.add(hh);
        Performer ny = new Performer("那英", 10);
        performers.add(ny);
        Performer lhh = new Performer("刘欢", 10);
        performers.add(lhh);
        Performer yk = new Performer("杨坤", 10);
        performers.add(yk);
        Performer zj = new Performer("周杰", 10);
        performers.add(zj);

        Performer performerOm = new Performer("美国明星");
        performers.add(performerOm);
        Performer mm = new Performer("梅梅", 10);
        performers.add(mm);
        Performer ade = new Performer("Gaga", 10);
        performers.add(ade);
        Performer hff = new Performer("黑寡妇", 10);
        performers.add(hff);
        Performer xlz = new Performer("小李子", 10);
        performers.add(xlz);

        Performer performerNba = new Performer("NBA明星");
        performers.add(performerNba);
        Performer xhd = new Performer("小皇帝", 10);
        performers.add(xhd);
        Performer kb = new Performer("科比", 10);
        performers.add(kb);
        Performer ym = new Performer("姚明", 10);
        performers.add(ym);
        Performer md = new Performer("麦迪", 10);
        performers.add(md);
        Performer dlt = new Performer("杜兰特", 10);
        performers.add(dlt);
        Performer kl = new Performer("库里", 10);
        performers.add(kl);
        Performer ouw = new Performer("欧文", 10);
        performers.add(ouw);
        Performer qd = new Performer("乔丹", 10);
        performers.add(qd);
        Performer alzw = new Performer("奥拉朱旺", 10);
        performers.add(alzw);
        Performer pp = new Performer("皮蓬", 10);
        performers.add(pp);
        Performer ldm = new Performer("罗德曼", 10);
        performers.add(ldm);
        Performer ke = new Performer("科尔", 10);
        performers.add(ke);
        Performer pesi = new Performer("皮尔斯", 10);
        performers.add(pesi);
        Performer jnt = new Performer("加内特", 10);
        performers.add(jnt);
        Performer lal = new Performer("雷阿伦", 10);
        performers.add(lal);
        Performer zmg = new Performer("字母哥", 10);
        performers.add(zmg);
        Performer adn = new Performer("安东尼", 10);
        performers.add(adn);

        Performer performerDy = new Performer("导演");
        performers.add(performerDy);
        Performer jzk = new Performer("贾樟柯", 10);
        performers.add(jzk);
        Performer ly = new Performer("李杨", 10);
        performers.add(ly);
        Performer fxg = new Performer("冯小刚", 10);
        performers.add(fxg);
        Performer lyy = new Performer("娄烨", 10);
        performers.add(lyy);
        Performer zym = new Performer("张艺谋", 10);
        performers.add(zym);

        return performers;
    }




}
