package com.example.my_test.registrasiya;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class Forgot_Fragment extends Fragment {
    private Button sentt;
    private FirebaseAuth auth;
    private EditText gmail;
private TextView timer;
    public Forgot_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sentt = view.findViewById(R.id.sentt);
        gmail = view.findViewById(R.id.gmail);
        timer = view.findViewById(R.id.timer);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user;
        user = auth.getCurrentUser();

        sentt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gmail.getText().toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Fill in the fields", Toast.LENGTH_SHORT).show();
                } else {

                    auth.sendPasswordResetEmail(gmail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (user != null) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(requireContext(), "Ok send", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(requireContext(), "Not send", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(requireContext(), "Users is not found", Toast.LENGTH_SHORT).show();
                                    }
                                    Timer();
                                    sentt.setEnabled(false);
                                }

                            });


                }
            }
        });
    }

    public void Timer() {

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.format(Locale.getDefault(), "%d sec.", millisUntilFinished / 1000L));
            }
            public void onFinish() {
                timer.setText("Time is over.");
                sentt.setEnabled(true);
            }
        }.start();
    }

}