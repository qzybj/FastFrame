package com.frame.fastframelibrary.utils.jump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.frame.fastframelibrary.utils.dataprocess.IntentUtils;

public class JumpBaseUtils {

    public static void goActivity(Context con,IJumpInfo bean){
        if(con!=null&&bean!=null){
            Intent intent = new Intent();
            intent.setClassName(con,bean.getTarget().getName());
            if(!IntentUtils.isEmpty(bean.getArgs())){
                intent.putExtras(bean.getArgs());
            }
            con.startActivity(intent);
        }
    }

    public interface IJumpInfo {
        Class getTarget();
        String getTitle();
        Bundle getArgs();
    }
}