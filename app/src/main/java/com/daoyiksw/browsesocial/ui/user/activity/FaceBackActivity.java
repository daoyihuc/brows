package com.daoyiksw.browsesocial.ui.user.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseTitleActivity;
import com.daoyiksw.browsesocial.consts.GlobalData;
import com.daoyiksw.browsesocial.https.HttpMethods;
import com.daoyiksw.browsesocial.pub.holder.GlideEngine;
import com.daoyiksw.browsesocial.pub.holder.GridSpacingItemDecoration;
import com.daoyiksw.browsesocial.ui.user.adapter.FacebackImageAdapter;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.views.dialog.CallDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.youth.banner.util.LogUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * AUTHOR : daoyi
 * TODO : 意见反馈页面
 * DATE : 2020/3/25
 * VERSION : 1.0
 */
public class FaceBackActivity extends BaseTitleActivity {

    private final int REQUEST_CODE_CHOOSE = 0x100;
    private static final int REQ_PERMISSION_CODE = 0x101;

    private View view;
    private RecyclerView imageRv;
    private EditText contentEt;
    private TextView submitBtn;
    private FacebackImageAdapter mAdapter;
    private List<String> images;
    private List<MultipartBody.Part> parts = new ArrayList<>();
    private List<String> uploadPaths =new ArrayList<>();

    public static void start(Activity activity) {
        Intent intent=new Intent();
        intent.setClass(activity,FaceBackActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MacUtils.initWindows(this, 0xffffffff, false, null, true);
        titlabar.setCenterTitle("意见反馈");
        view =getLayoutInflater().inflate(R.layout.activity_face_back, relativeLayout,true);
        initViewAndData();

    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected <T> T Https() {
        return super.Https();
    }

    @Override
    protected void setTitlabar() {
        super.setTitlabar();
    }

    private void initViewAndData() {

        contentEt =findViewById(R.id.content_et);

        images =new ArrayList<>();
        images.add("#");
        imageRv =findViewById(R.id.image_rv);
        GridLayoutManager glManager =new GridLayoutManager(this, 4);
        imageRv.setLayoutManager(glManager);
        mAdapter =new FacebackImageAdapter(this, images);
        imageRv.setAdapter(mAdapter);
        imageRv.addItemDecoration(new GridSpacingItemDecoration(4, MacUtils.dpto(9), false));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if(checkPermission(FaceBackActivity.this)) {

                    if ("#".equals(images.get(position))) {  //添加图片

                        selectPhoto();
                    } else{  //查看大图

//                        LookImageIntent lookIntent =new LookImageIntent();
//                        lookIntent.setUrlDatas(images);
//                        lookIntent.setCurPosition(position);
//                        LookImageActivity.start(FaceBackActivity.this, lookIntent);
                    }
                }

            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                images.remove(position);
                if(!"#".equals(images.get(images.size() -1)))
                    images.add("#");
                mAdapter.notifyDataSetChanged();
            }
        });

        submitBtn =findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contentStr =contentEt.getText().toString();
                if(TextUtils.equals("", contentStr)){
                    MacUtils.ToastShow(FaceBackActivity.this,"哎呀，您忘记填写问题和意见了哦");
                    return;
                }
                if(contentStr.length() <10) {
                    MacUtils.ToastShow(FaceBackActivity.this,"哎呀，问题和意见长度要大于10位哦");
                    return;
                }

                if(images !=null && images.size() >0){
                     if("#".equals(images.get(images.size() -1)))
                         images.remove(images.size() -1);
                }

                if(images ==null || images.size() ==0) {
//                    dailog.show();
                    reqeustSubmitData();
                } else{
//                    dailog.show();
                    uploadImg();
                }

            }
        });

        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBackDialog();
            }
        });
    }

    //选择图片
    private void selectPhoto() {

        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .theme(R.style.picture_white_style)
                .maxSelectNum(5 -images.size())
                .minSelectNum(1)
                .compress(true)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {

                        if(result !=null && result.size() >0) {

                            for(int i=0;i<result.size();i++) {

                                if(result.get(0).isCompressed())
                                    images.add(images.size() -1, result.get(i).getCompressPath());   //压缩过的图片
                                else
                                    images.add(images.size() -1,result.get(i).getPath());   //原图
                            }

                            if(images.size() >3) {
                                images.remove(3);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancel() { }
                });
    }

    private int uploadIndex =0;
    //上传图片
    private void uploadImg() {



    }

    //继续上传图片
    private void uploadMoreImg(String urlPath) {

    }

    //提交意见反馈
    private void reqeustSubmitData() {

    }

    //权限检查
    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GlobalData.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GlobalData.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GlobalData.getContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GlobalData.getContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }

            if (permissions.size() != 0) {
                String[] permissionsArray = permissions.toArray(new String[1]);
                ActivityCompat.requestPermissions(activity,
                        permissionsArray,
                        REQ_PERMISSION_CODE);
                return false;
            }
        }

        return true;
    }

    /**
     * 系统请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    selectPhoto();
                } else{

                    Toast.makeText(FaceBackActivity.this, "未完成授权，无法使用该功能", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void actionBackDialog() {

        CallDialog callDialog=new CallDialog(FaceBackActivity.this,"您的问题还未提交，确定离开吗？");
        callDialog.setTitle("温馨提示");
        callDialog.setOkAndCancel("取消", "离开");
        callDialog.setOnCancelClickListener(new CallDialog.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        callDialog.show();
    }

    @Override
    public void onBackPressed() {

        actionBackDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
