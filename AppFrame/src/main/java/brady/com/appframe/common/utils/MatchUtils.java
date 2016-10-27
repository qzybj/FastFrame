package brady.com.appframe.common.utils;

import com.frame.fastframelibrary.utils.dataprocess.ListUtils;

/**
 * Created by ZhangYuanBo on 2016/10/27.
 */

public class MatchUtils {
    /**
     *  用于int类型标记判断
     * @param matchCode
     * @param targetCodes
     * @return true 匹配成功  false  匹配不成功
     */
    public static boolean matchIntCode(int matchCode,int... targetCodes){
        if(ListUtils.isNotEmpty(targetCodes)){
            for (int targetCode : targetCodes) {
                if(matchCode ==targetCode){
                    return true;
                }
            }
        }
        return false;
    }
}
