package com.sjq.githubapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.presenters.LoginPresenter;
import com.sjq.githubapp.views.LoginView;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.PermissionChecker;
import jp.wasabeef.blurry.Blurry;


public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {


    private EditText userNameEt;
    private EditText psdEt;
    private Button loginBtn;

    private LoadingDialog mLoadingView;
    private ConstraintLayout wrap;

    boolean isGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }


    public void initView() {
        mLoadingView = new LoadingDialog(this);
        mLoadingView.setLoadingText("加载中")
                .setSuccessText("加载成功")//显示加载成功时的文字
                //.setFailedText("加载失败")
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                .setRepeatCount(1000);

        userNameEt = (EditText) findViewById(R.id.user_name_et);
        psdEt = (EditText) findViewById(R.id.psd_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        wrap = (ConstraintLayout) findViewById(R.id.wrap);
//        Blurry.with(this).radius(25).sampling(2).onto(wrap);
        Blurry.with(this)
                .radius(10)
                .sampling(8)
                .color(Color.argb(66, 255, 255, 0))
                .async()
                .animate(500)
                .onto(wrap);


        requestPermissonWithCode(new onRequestPermissonResultListener() {
            @Override
            public void onAllowPermission() {

            }

            @Override
            public void onRefusePermission() {

            }
        }, 1990, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (isGranted) {
                    mLoadingView.show();
                    mPresenter.doLogin(userNameEt.getText().toString().trim(), psdEt.getText().toString().trim());
                } else {
                    Toast.makeText(LoginActivity.this, "请允许权限", Toast.LENGTH_SHORT).show();
                }

        }

    }


    @Override
    public void loginSuccess() {
        mLoadingView.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void loginFailed(String errMsg) {
        Snackbar.make(wrap, errMsg, 1500).show();
        mLoadingView.close();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Android M请求权限封装
     *
     * @param onRequestPermissonResultListener 请求结果回调
     * @param requestCode                      请求权限请求码
     * @param permissions                      所请求的权限（数组）
     */
    public void requestPermissonWithCode(onRequestPermissonResultListener onRequestPermissonResultListener,
                                         int requestCode,
                                         String... permissions) {
        if (permissions == null || permissions.length == 0) {
            Toast.makeText(this, "权限请求异常", Toast.LENGTH_SHORT).show();
            return;
        }


        isGranted = checkPermissionIsGranted(permissions);
        if (isGranted) {
            //如果已经被授权
            onRequestPermissonResultListener.onAllowPermission();
        } else {
            //请求权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, requestCode);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("TAG", "------------------------->onRequestPermissionsResult");
        if (requestCode == 1990) {
            //避免出现一个授权请求组多个授权，只要有一个没授权就算所有的拒绝授权
            StringBuffer str = new StringBuffer();
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    str.append("允许\n");
                } else {
                    str.append("拒绝\n");
                }
            }
            Log.i("TAG", str.toString());
            boolean isAllAllow = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isAllAllow = false;
                    break;
                }
            }
            if (isAllAllow) {
                isGranted = true;
            } else {
                isGranted = false;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //检查权限是否授予
    private boolean checkPermissionIsGranted(String... permissions) {
        boolean result = true;
        //判断版本号
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int targetSdkVersion = getTargetSdkVersion();
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                for (String permission : permissions) {
                    result = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                    //避免出现一次请求多个权限，第一个权限没有被授权第二个权限被受过权导致的result = true;
                    //当申请的权限组中有一个未授权时就全部重新请求
                    if (!result) {
                        break;
                    }
                }
            } else {
                for (String permission : permissions) {
                    result = PermissionChecker.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
                    if (!result) {
                        break;
                    }
                }

            }
        }
        return result;
    }

    //获取targetSdk
    private int getTargetSdkVersion() {
        int targetVersion = -1;
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            targetVersion = packageInfo.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return targetVersion;
    }


}
