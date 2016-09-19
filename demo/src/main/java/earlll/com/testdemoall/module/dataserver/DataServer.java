package earlll.com.testdemoall.module.dataserver;

import java.util.ArrayList;
import java.util.List;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.MultipleItem;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.MySection;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Status;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.entity.Video;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.AnimationAdatperActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.CollapsingAdapterActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.GroupStyleAdapterActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.ItemDragAndSwipeUseActivity;
import earlll.com.testdemoall.aosp.baserecyclerviewadapterhelper.ui.ItemDragSmallIconActivity;
import earlll.com.testdemoall.aosp.eventbus.ui.EventBusReciveActivity;
import earlll.com.testdemoall.core.ui.reciverui.bean.MainItemBean;
import earlll.com.testdemoall.core.ui.reciverui.ui.ContainerActivity;
import earlll.com.testdemoall.core.ui.reciverui.ui.SimpleFragment;
import earlll.com.testdemoall.module.annotationdemo.ui.SimpleAnnotationActivity;
import earlll.com.testdemoall.module.demo.ui.AnimationSimpleActivity;
import earlll.com.testdemoall.module.demo.ui.MultiFragmentActivity;
import earlll.com.testdemoall.module.demo.ui.SimpleFragmentActivity;
import earlll.com.testdemoall.module.demo.ui.TestDataSimpleActivity;
import earlll.com.testdemoall.module.demo.ui.fragment.ThreadFragment;
import earlll.com.testdemoall.module.dragger2.ui.DraggerActivity;
import earlll.com.testdemoall.module.mvpdemo.ui.MvpFragment;
import earlll.com.testdemoall.module.testnetframe.ui.TestNetActivity;
import earlll.com.testdemoall.module.viewdemo.ui.SimpleLayoutActivity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DataServer {

    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";

    private DataServer() {
    }

    public static List<Status> getSampleData(int lenth) {
        List<Status> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Status status = new Status();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    public static List<Status> addData(List list, int dataSize) {
        for (int i = 0; i < dataSize; i++) {
            Status status = new Status();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("Powerful and flexible RecyclerAdapter https://github.com/CymChad/BaseRecyclerViewAdapterHelper");
            list.add(status);
        }

        return list;
    }

    public static List<MySection> getSampleData() {
        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true, "Section 1", true));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(true, "Section 2", false));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(true, "Section 3", false));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(true, "Section 4", false));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(true, "Section 5", false));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new MySection(new Video(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        return list;
    }

    public static List<String> getStrData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK;
            if (i % 2 == 0) {
                str = CYM_CHAD;
            }
            list.add(str);
        }
        return list;
    }

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MultipleItem multipleItem = new MultipleItem();
            String str = null;
            multipleItem.setItemType(MultipleItem.IMG);
            if (i % 2 == 0) {
                str = CYM_CHAD;
                multipleItem.setItemType(MultipleItem.TEXT);
            } else if (i % 3 == 0) {
                multipleItem.setItemType(MultipleItem.IMGS);
            }
            multipleItem.setContent(str);
            list.add(multipleItem);
        }
        return list;
    }

    public static ArrayList<MainItemBean> getMainData() {
        ArrayList<MainItemBean> list = new ArrayList<>();
        list.add(TestData4Demo.getJumpBeanF(ContainerActivity.class,"Thead Demo",ThreadFragment.class));
        list.add(TestData4Demo.getJumpBeanF(ContainerActivity.class,"Mvp simple ",MvpFragment.class));
        list.add(TestData4Demo.getJumpBeanF(ContainerActivity.class,"接收Fragment并显示",SimpleFragment.class));
        list.add(TestData4Demo.getJumpBean(DraggerActivity.class,"Dragger2",null));

        list.add(TestData4Demo.getJumpBean(TestDataSimpleActivity.class,"测试数据反射生成器使用示例",null));
        list.add(TestData4Demo.getJumpBean(TestNetActivity.class,"测试网络框架",null));
        list.add(TestData4Demo.getJumpBean(SimpleFragmentActivity.class,"Fragment封装使用示例",null));

        list.add(TestData4Demo.getJumpBean(EventBusReciveActivity.class,"EventBus使用示例",null));
        list.add(TestData4Demo.getJumpBean(AnimationSimpleActivity.class,"AnimationSimple - 测试动画",null));
        list.add(TestData4Demo.getJumpBean(MultiFragmentActivity.class,"多个Fragment界面的展示示例", null));
        list.add(TestData4Demo.getJumpBean(SimpleAnnotationActivity.class,"注解使用示例", null));
        list.add(TestData4Demo.getJumpBean(SimpleLayoutActivity.class,"各种布局 ViewStub，merge，include 使用示例", null));

        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 动画",AnimationAdatperActivity.class,null));
        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - group分组",GroupStyleAdapterActivity.class,null));
        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 拖拽ItemView及滑动删除",ItemDragAndSwipeUseActivity.class,null));
        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 拖拽small Icon",ItemDragSmallIconActivity.class,null));
//        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 多类型",MultipleTypeAdapterActivity.class,null));
//        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 下拉刷新",PullToRefreshAdapterActivity.class,null));
        list.add(TestData4Demo.getJumpBean("RecyclerviewAdatper - 项部收缩",CollapsingAdapterActivity.class,null));

//        list.add(TestData4Demo.getJumpBean(InterceptUrlActivity.class,"Html页面打开时，针对http劫持逻辑的处理", null));
//        list.add(TestData4Demo.getJumpBean(FileOperationSimpleActivity.class,"FileOperationSimple - File操作使用示例",null));
//        list.add(TestData4Demo.getJumpBean(PopwindowSimpleActivity.class,"测试Popwindow",null));
//        list.add(TestData4Demo.getJumpBean(SimpleSwipeRefreshLayoutActivity.class,"下拉刷新SwipeRefreshLayout中放置WebView展示",
//                IntentUtils.setBString(null, ConstantsBaseKey.KEY_URL,"http://m.yintai.com/category/miaoindex?")));
//        list.add(TestData4Demo.getJumpBean(SimplePullRefreshLayoutActivity.class,"下拉刷新 PullRefreshLayout",null));
//        list.add(TestData4Demo.getJumpBean("展示示例", SimpleBaseAdapterActivity.class,null));
//        list.add(TestData4Demo.getJumpBean("ZXingActivity - 二维码扫码处理", ZXingActivity.class,null));

//        list.add(TestData4Demo.getJumpBean(SimpleBaseAdapterActivity.class,"单个item展示",null));
//        list.add(TestData4Demo.getJumpBean("xrecyclerview - 收缩项部ToolBar",CollapsingToolbarActivity.class,null));
//        list.add(TestData4Demo.getJumpBean("xrecyclerview - 多Hearder",MultiHeaderActivity.class,null));
//        list.add(TestData4Demo.getJumpBean("xrecyclerview - 交错",StaggeredGridActivity.class,null));
        return list;
    }
}