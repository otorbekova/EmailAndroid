package com.example.my_test.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.my_test.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;


public class Provile_Fragment extends Fragment {
    private ImageView profil_image;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView value_name,emaill;


    public Provile_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_provile_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        Glide.with(this).load(R.drawable.bart).circleCrop().into(profil_image);


//import { auth } from "firebase/auth";

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                //    Uri photoUrl = profile.getPhotoUrl();
                value_name.setText(name);
                emaill.setText(email);
            }
        }

    }

    private void init(View view) {
        profil_image = view.findViewById(R.id.profil_image);
        value_name = view.findViewById(R.id.value_fam);
        emaill=view.findViewById(R.id.email);
    }
}