package earlll.com.testdemoall.module.annotationdemo.bean;

import com.google.gson.annotations.SerializedName;
import earlll.com.testdemoall.module.annotationdemo.annotation.AnnotationTest;

/**
 * Created by ZhangYuanBo on 2016/7/25.
 */
public class AnnotationTestBean {
    @AnnotationTest(value= {AnnotationTest.Type.TYPEA,AnnotationTest.Type.TYPEB},bgColor= AnnotationTest.Color.BULE)
    @SerializedName("g_name")
    public String name;
    @AnnotationTest(value= {AnnotationTest.Type.TYPEA,AnnotationTest.Type.TYPEC},bgColor= AnnotationTest.Color.BULE)
    @SerializedName("g_likename")
    public String likename;
}
