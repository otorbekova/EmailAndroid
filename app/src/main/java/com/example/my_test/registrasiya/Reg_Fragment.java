package com.example.my_test.registrasiya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_test.Main_Fragment;
import com.example.my_test.R;
import com.example.my_test.tool.Prefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Reg_Fragment extends Fragment {

    private Button button,check;
    private View frmain;
    private TextView regist_id, forgot, timer;
    private FirebaseAuth auth;
    private EditText login, password;
    private ActionCodeSettings actionCodeSettings;
    private FirebaseUser user;
    private FirebaseFirestore fstore;
    private ProgressBar progres;
    private String userId;


    public Reg_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reg_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inis(view);
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progres.setVisibility(View.GONE);

        ONclick();
        //     firebase();
    }

    ////////////////////////////////
    private void inis(View view) {
        button = view.findViewById(R.id.sign_up);
        frmain = view.findViewById(R.id.fragment_main);
        regist_id = view.findViewById(R.id.regist_id);
        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);
        forgot = view.findViewById(R.id.forgot);
        progres = view.findViewById(R.id.progres);
        check=view.findViewById(R.id.check);
    }

    private void ONclick() {
        regist_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rasp_Fragment ras=new Rasp_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,ras,"Ras_id")
                       // .replace(R.id.fragment_main,ras,"vjkjjkfd")
                        .addToBackStack(null)
                        .commit();
                /*
                  two_step_Fragment forgot_fragment = new two_step_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, forgot_fragment, "Asdf")
                        .addToBackStack(null)
                        .commit();
                 */
            }
        });


        ////
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Prefs(requireActivity()).isShown(true); //zapis cho 1 raz
                if (login.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Fill in the form", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progres.setVisibility(View.VISIBLE);
                                        Main_Fragment main_fragment = new Main_Fragment();
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.fragment_main, main_fragment, "Tll")
                                                // .addToBackStack(null)
                                                .commit();
                                    } else {
                                        Toast.makeText(requireContext(), "Error out", Toast.LENGTH_SHORT).show();
                                        progres.setVisibility(View.GONE);
                                    }
                                }

                                ;
                            });
                }
            }
        });
/////////////////////////////////////

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  firebase();

                Forgot_Fragment forgot_fragment = new Forgot_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, forgot_fragment, "As")
                        .addToBackStack(null)
                        .commit();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_step_Fragment forgot_fragment = new two_step_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, forgot_fragment, "Asdf")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    private void firebase() {

        //   auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Toast.makeText(requireContext(), user.getUid(), Toast.LENGTH_LONG).show();
        if (user != null) {
            String url = "http://www.my_test.com/verify?uid=" + user.getUid();
            //String url = login_st + user.getUid();
            actionCodeSettings = ActionCodeSettings.newBuilder()
                    .setUrl(url)
                    // .setHandleCodeInApp(true)//Будет ли ссылка действия электронной почты сначала открыта в мобильном приложении или в веб-ссылке.
                    .setIOSBundleId("com.my_test.ios")// Это попытается открыть ссылку в приложении iOS, если оно установлено
                    .setAndroidPackageName("com.my_test.android", false, null)//Это попытается открыть ссылку в приложении для Android
                    .build();

            user.sendEmailVerification(actionCodeSettings)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Ok send", Toast.LENGTH_SHORT).show();
                            } else {
                                //  Toast.makeText(requireContext(), "Not send", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "Not user", Toast.LENGTH_SHORT).show();
        }

        //  FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(login.getText().toString(), actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Ok send", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Not send", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}








/*

  user = auth.getCurrentUser();
        auth.sendSignInLinkToEmail(login.getText().toString(), actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                             user = auth.getCurrentUser();
                            Toast.makeText(requireContext(), "Email.sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (user != null) {
            String url = "http://www.example.com/verify?uid=" + user.getUid();
            //String url = login_st + user.getUid();
            actionCodeSettings = ActionCodeSettings.newBuilder()
                    .setUrl(url)
                    // .setHandleCodeInApp(true)//Будет ли ссылка действия электронной почты сначала открыта в мобильном приложении или в веб-ссылке.
                    .setIOSBundleId("com.example.ios")// Это попытается открыть ссылку в приложении iOS, если оно установлено
                    .setAndroidPackageName("com.example.android", false, null)//Это попытается открыть ссылку в приложении для Android
                    .build();
            user.sendEmailVerification(actionCodeSettings)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Ok send", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "Not user", Toast.LENGTH_SHORT).show();
        }

        //  FirebaseAuth auth = FirebaseAuth.getInstance();

    }
 */