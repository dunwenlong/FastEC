package com.example.fastec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.delegate_example);
//    }
//}
public class MainActivity extends ProxyActivity{

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}