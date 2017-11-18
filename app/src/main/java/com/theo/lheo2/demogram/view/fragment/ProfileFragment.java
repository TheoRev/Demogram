package com.theo.lheo2.demogram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.crash.FirebaseCrash;
import com.theo.lheo2.demogram.R;
import com.theo.lheo2.demogram.adapter.PictureAdapterRecyclerView;
import com.theo.lheo2.demogram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseCrash.log("Iniciando " + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, view);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById((R.id.pictureProfileRecycler));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView
                = new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
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
