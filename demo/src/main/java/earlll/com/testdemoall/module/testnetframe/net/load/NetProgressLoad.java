package earlll.com.testdemoall.module.testnetframe.net.load;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.frame.fastframelibrary.net.core.config.NetConstants;
import com.frame.fastframelibrary.net.core.interfaces.IProgressLoad;
import com.frame.fastframelibrary.net.core.interfaces.ITag;
import com.frame.fastframelibrary.utils.dataprocess.MapUtils;
import java.util.HashMap;


public class NetProgressLoad implements IProgressLoad {
    private String Tag = "ProgressLoad";
    private FragmentActivity mActivity;
    private DialogFragment mFragment;
    private boolean isShow= false;
    private HashMap<Long,Integer> map = new HashMap<Long,Integer>();

    public NetProgressLoad(FragmentActivity activity) {
        this.mActivity = activity;
        buildFragment();
    }

    @Override
    public synchronized void startRequest(ITag tag) {
        if(mFragment==null){
            buildFragment();
        }
        if(!isShow&&checkActivity()){
            if(mFragment.isHidden()){
                mActivity.getFragmentManager().beginTransaction().show(mFragment).commit();
            }else{
                mFragment.show(mActivity.getFragmentManager(),Tag);
            }
            isShow = true;
        }
        insert(tag);
    }

    @Override
    public synchronized void stopRequest(ITag tag) {
        if(isShow&&mFragment!=null){
            if(checkHide(tag)){
                mFragment.dismiss();
                isShow= false;
            }
        }
    }

    @Override
    public synchronized void destroy() {
        if(checkActivity()&&mFragment!=null){
            mActivity.getFragmentManager().beginTransaction().hide(mFragment).remove(mFragment).commit();
        }
        mActivity = null;
        mFragment= null;
        map.clear();
    }

    private boolean checkActivity() {
        if(mActivity!=null&&!mActivity.isFinishing()){
            return true;
        }
        return false;
    }

    private void buildFragment() {
        if(mFragment==null){
            if(checkActivity()){
                mFragment = (ProgressDialogFragment)mActivity.getFragmentManager().findFragmentByTag(Tag);
            }
            if(mFragment==null){
                mFragment = ProgressDialogFragment.newInstance();
            }
        }
    }
    private void insert(ITag tag) {
        if(tag!=null){
            if(tag.getLoadingType()!=NetConstants.LoadingType.LOADING_NONE){
                if(map!=null){
                    map = new HashMap<Long,Integer>();
                }
                map.put(tag.getRequstTime(),tag.getLoadingType());
            }
        }
    }
    private void remove(ITag tag) {
        if(tag!=null&&MapUtils.isNotEmpty(map)){
            map.remove(tag.getRequstTime());
        }
    }

    /**Return true need hide */
    private boolean checkHide(ITag tag) {
        if(tag!=null&&MapUtils.isNotEmpty(map)){
            Integer loadType = map.get(tag.getRequstTime());
            if(loadType==null) return false;
            remove(tag);
            switch (loadType){
                case NetConstants.LoadingType.LOADING_NORMAL:
                case NetConstants.LoadingType.LOADING_END:
                    boolean flag = map.containsValue(NetConstants.LoadingType.LOADING_NORMAL)||
                            map.containsValue(NetConstants.LoadingType.LOADING_END);
                    if(flag) return false;
                default:
                    return true;
            }
        }
        return true;
    }
}