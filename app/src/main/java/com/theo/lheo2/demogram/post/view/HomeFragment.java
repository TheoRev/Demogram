package com.theo.lheo2.demogram.post.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.crash.FirebaseCrash;
import com.theo.lheo2.demogram.R;
import com.theo.lheo2.demogram.adapter.PictureAdapterRecyclerView;
import com.theo.lheo2.demogram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp = "";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseCrash.report(new Exception("Reporte desde HomeFragment"));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);
        fabCamera = (FloatingActionButton) view.findViewById(R.id.fabCamera);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView
                = new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(null, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                FirebaseCrash.report(e);
            }

            if (photoFile != null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    Uri photoUri = FileProvider.getUriForFile(getActivity(), "com.theo.lheo2.demogram", photoFile);
                    intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                } else {
                    intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(photoPathTemp));
                }
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo = File.createTempFile(imageFileName, ".jpg", storageDir);

        photoPathTemp = "file:" + photo.getAbsolutePath();
        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {
            Log.d("HomeFragment", "CAMERA OK!! :)");
            Intent i = new Intent(getActivity(), NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TEMP", photoPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://diariouno.pe/wp-content/uploads/2015/10/3914.jpg", "Theo Revilla", "4 días", "3 Me Gusta"));
        pictures.add(new Picture("https://i.ytimg.com/vi/aqlKYruUXC8/hqdefault.jpg", "Almendra Cárdenas", "3 días", "6 Me Gusta"));
        pictures.add(new Picture("http://4.bp.blogspot.com/_m-K6wUKJ51s/S_mYxnw-DYI/AAAAAAAAAD0/9hOAxjjjLWo/s1600/kapawi3gv1.jpg", "Gabriela Carpio", "7 días", "5 Me Gusta"));
        return pictures;
    }

    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
