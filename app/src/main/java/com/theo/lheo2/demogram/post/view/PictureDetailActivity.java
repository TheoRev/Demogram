package com.theo.lheo2.demogram.post.view;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theo.lheo2.demogram.DemogramApplication;
import com.theo.lheo2.demogram.R;

public class PictureDetailActivity extends AppCompatActivity {

    private static final String TAG = "PictureDetailActivity";
    private static final String PHOTO_NAME = "JPEG_20171025_23-40-04_261169432.jpg";
    private ImageView imageHeader;
    private DemogramApplication app;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseCrash.log("Iniciando " + TAG);
        setContentView(R.layout.activity_picture_detail);

        app = (DemogramApplication) getApplicationContext();
        storageReference = app.getStorageReference();

        imageHeader = (ImageView) findViewById(R.id.imageHeader);

        showToolbar("", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }

        showData();
    }

    private void showData() {
        storageReference.child("postImages/" + PHOTO_NAME)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PictureDetailActivity.this).load(uri.toString()).into(imageHeader);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PictureDetailActivity.this, "Ocurrió un error al traer la foto", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }
}
