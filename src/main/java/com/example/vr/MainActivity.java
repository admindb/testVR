package com.example.vr;

import android.arch.persistence.room.Room;
import android.graphics.BitmapFactory;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vr.bean.User;
import com.example.vr.data.UserDatabase;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private VrPanoramaView mVrPanoramaView;
    private VrPanoramaView.Options paNormalOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVrPaNormalView();

    }

    //初始化VR图片
    private void initVrPaNormalView() {
        mVrPanoramaView = (VrPanoramaView) findViewById(R.id.mVrPanoramaView);
        paNormalOptions = new VrPanoramaView.Options();
        paNormalOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
//      mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        mVrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        mVrPanoramaView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
        mVrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听
        //加载本地的图片源
        mVrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes), paNormalOptions);
//        mVrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.vr2), paNormalOptions);
        //设置网络图片源
//        panoWidgetView.loadImageFromByteArray();


        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseOperation();
            }
        }).start();


    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {//图片加载成功
        }


        @Override
        public void onLoadError(String errorMessage) {//图片加载失败
        }

        @Override
        public void onClick() {//当我们点击了VrPanoramaView 时候触发            super.onClick();
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
        }
    }


    private void databaseOperation(){
        UserDatabase userDatabase=UserDatabase.getInstance(this);
        User user=new User();
        user.setName("小李");
        user.setAge(10);
        userDatabase.getUserDao().insert(user);
        User user2=new User();
        user2.setName("困 困 困分");
        user2.setAge(15);
        userDatabase.getUserDao().insert(user2);

        List<User> usersList=userDatabase.getUserDao().getAllUsers();
        for (User users:usersList){
            Log.e(TAG,"姓名："+users.getName()+" 年龄："+users.getAge());
        }

        User user3=userDatabase.getUserDao().getUser(2);
        user3.setAge(1000);
        userDatabase.getUserDao().update(user3);
        Log.e(TAG,"根据id查询="+userDatabase.getUserDao().getUser(2).getAge());

    }
}
