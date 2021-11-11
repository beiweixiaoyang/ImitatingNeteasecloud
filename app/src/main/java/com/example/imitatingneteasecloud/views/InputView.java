package com.example.imitatingneteasecloud.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imitatingneteasecloud.R;

/**
 * 自定义输入框
 */
public class InputView extends FrameLayout {

    private int input_icon;
    private boolean input_password;
    private String input_hint;

    private View mView;
//    InputView种的子view
    private ImageView iv_icon;
    private EditText editText;

    public InputView(@NonNull Context context) {
        super(context);
        initView(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        //获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputView);
        input_icon=typedArray.getResourceId(R.styleable.InputView_input_icon,R.mipmap.logo);
        input_password=typedArray.getBoolean(R.styleable.InputView_input_password,false);
        input_hint=typedArray.getString(R.styleable.InputView_input_hint);
        typedArray.recycle();//释放掉TypedArray

//        加载自定义的布局文件
        mView= LayoutInflater.from(context).inflate(R.layout.inputview,this,false);
        iv_icon=mView.findViewById(R.id.iv_icon);
        editText=mView.findViewById(R.id.editText);

        //通过自定义的属性设置子view的属性
        iv_icon.setImageResource(input_icon);
        editText.setHint(input_hint);
        //设置editText的输入类型
        editText.setInputType(input_password ?
                InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD
                :InputType.TYPE_CLASS_PHONE);
        //添加子视图
        addView(mView);
    }

    /**
     * 对外部提供一个public方法用于获取editText中输入的文本内容
     */

    public String getInputText(){
        return editText.getText().toString().trim();
    }
}
