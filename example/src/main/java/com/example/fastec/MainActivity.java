package com.example.fastec;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.launcher.ILauncherListener;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;
import com.example.latte_ec.launcher.LauncherDelegate;
import com.example.latte_ec.launcher.LauncherScrollDelegate;
import com.example.latte_ec.sign.ISignListener;
import com.example.latte_ec.sign.SignUpDelegate;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.delegate_example);
//    }
//}
public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
//        return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登陆了", Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignUpDelegate());
                break;
            default:
                break;
        }
    }
}