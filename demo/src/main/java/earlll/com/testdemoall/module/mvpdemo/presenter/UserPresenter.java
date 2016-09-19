package earlll.com.testdemoall.module.mvpdemo.presenter;

import earlll.com.testdemoall.module.mvpdemo.interfaces.IUserModel;
import earlll.com.testdemoall.module.mvpdemo.interfaces.IUserView;
import earlll.com.testdemoall.module.mvpdemo.model.UserBean;
import earlll.com.testdemoall.module.mvpdemo.model.UserModel;

public class UserPresenter {
    private IUserView mUserView ;
    private IUserModel mUserModel ;

    public UserPresenter (IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser(int id , String name ,String nickName) {
        mUserModel.save(id,name,nickName);
    }

    public void loadUser( int id ) {
        UserBean user = mUserModel.load (id );
        if(user!=null){
            mUserView.setName(user.getName());
            mUserView .setNickName(user.getNickName());
        }else{
            mUserView.setError("user is not exist");
        }
    }
}