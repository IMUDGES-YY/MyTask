package com.imudges.mytask.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.imudges.mytask.R;
import com.imudges.mytask.util.MyParamsBuilder;
import es.dmoral.toasty.Toasty;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by HUPENG on 2017/4/26.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    @ViewInject(R.id.et_username)
    private EditText etUsername;

    @ViewInject(R.id.et_password)
    private EditText etPassword;

    @Event(value = R.id.btn_login,
            type = View.OnClickListener.class)
    private void onLoginClick(View view){
        //确认用户名与密码
        if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            Toasty.error(LoginActivity.this, "请输入用户名与密码",Toast.LENGTH_SHORT).show();
            return;
        }
        //获取输入的用户名与密码
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        //创建请求参数对象
        RequestParams params = new MyParamsBuilder("public/login.html",true)
                .addParameter("username",username)
                .addParameter("password",password)
                .builder();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Toasty.info(LoginActivity.this, s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
