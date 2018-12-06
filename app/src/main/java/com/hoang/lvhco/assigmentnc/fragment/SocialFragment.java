package com.hoang.lvhco.assigmentnc.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.hoang.lvhco.assigmentnc.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SocialFragment extends Fragment {
    private ImageView img;
    private TextView text1;
    private TextView text2;
    private ShareButton fbShareButton;
    private static String TAG = SocialFragment.class.getName();
    private CallbackManager callbackManager;

    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_fragment,container,false);
        return view ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageShare(view);

        img = (ImageView) view.findViewById(R.id.img);
        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        fbShareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
        shareDialog = new ShareDialog(getActivity());
//        fbShareButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (ShareDialog.canShow(ShareLinkContent.class)){
////                    shareLinkContent = new ShareLinkContent.Builder().setContentTitle()
////                }
////            }
////        });
    }

    private void setImageShare(View view) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.avata);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Connect with Le Hoang")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareButton shareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        private FacebookCallback<Sharer.Result> callback = new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.v(TAG, "Successfully posted");
                // Write some code to do some operations when you shared content successfully.
            }

            @Override
            public void onCancel() {
                Log.v(TAG, "Sharing cancelled");
                // Write some code to do some operations when you cancel sharing content.
            }

            @Override
            public void onError(FacebookException error) {
                Log.v(TAG, error.getMessage());
                // Write some code to do some operations when some error occurs while sharing content.
            }
        };
    }