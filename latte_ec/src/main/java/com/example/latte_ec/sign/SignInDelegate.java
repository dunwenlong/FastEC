package com.example.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Dun Wenlong
 * @time 2020/7/10 16:29
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }
    @OnClick(R2.id.bt_sign_in)
    void OnClickSignIn(){
//        if (checkForm()) {
        if (true) {
            RestClient.Builder()
                    .url("http://192.168.31.9/restserver/api/userprofile.php")
//                    .params("name", mName.getText().toString())
//                    .params("email", mEmail.getText().toString())
//                    .params("phone", mPhone.getText().toString())
//                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e("Respones", response);
//                            MyLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(),"验证成功", Toast.LENGTH_LONG).show();
        }
    }

//    @OnClick(R2.id.icon_sign_in_wechat)
//    void OnClickSignInWechat(){
//
//    }

    @OnClick(R2.id.tv_link_sign_up)
    void OnClickLinkSignUp(){
        start(new SignUpDelegate());
    }
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) throws ClassNotFoundException {

    }
}
