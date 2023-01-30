//package com.example.my_test.registrasiya;
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

import com.example.my_test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Rasp_Fragment extends Fragment {
    private Button save;
    private EditText password, login, sername, name;
    private String login_st, password_str;
    private ActionCodeSettings actionCodeSettings;
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;
    private ProgressBar progres;
    private String userId;
    private ProgressBar reg_progres;
    private TextView timer;

    public Rasp_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rasp_, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //method
        inis(view);

        ///////
        login_st = login.getText().toString();
        password_str = password.getText().toString();
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        sername = view.findViewById(R.id.sername);
        name = view.findViewById(R.id.name);
        reg_progres = view.findViewById(R.id.reg_progres);
        reg_progres.setVisibility(View.GONE);
        timer = view.findViewById(R.id.timer);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  firebase();
                reg_progres.setVisibility(View.VISIBLE);
                if (login.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Fill in in the edit text", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(getContext(), "Акаунт создан", Toast.LENGTH_SHORT).show();
                                        //   FirebaseUser user = auth.getCurrentUser();
                                        userId = auth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fstore.collection("users").document(userId);
                                        Map<String, Object> userr = new HashMap<>();
                                        userr.put("Name", name.getText().toString());
                                        userr.put("Sername", sername.getText().toString());
                                        userr.put("Email", login.getText().toString());
                                        userr.put("Password", password.getText().toString());
                                        // save.setEnabled(false);
                                        documentReference.set(userr).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
             Toast.makeText(getContext(), "Первый этап завершен", Toast.LENGTH_SHORT).show();

             two_step_Fragment two_step_fragment = new two_step_Fragment();
             getActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.time_profile_fragment, two_step_fragment, "two")
                                                        .commit();

                                                //    getActivity().onBackPressed();

                                            }
                                        });


                                    } else {
                                        Toast.makeText(getContext(), "Ошибка при создании акаунта", Toast.LENGTH_SHORT).show();
                                        reg_progres.setVisibility(View.GONE);
                                        //    save.setEnabled(true);
                                        //  Toast.makeText(requireContext(), password.getText().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            //reload();
        }
    }

    private void inis(View v) {
        save = v.findViewById(R.id.save);
        login = v.findViewById(R.id.login_id);
        password = v.findViewById(R.id.password_id);
    }

    private void onClick() {
        // button

    }

    private void create_akkaunt() {

    }




    /*ActionCodeSettings actionCodeSettings=new ActionCodeSettings().newBuilder()
             .setUrl("")
             .setHandleCodeInApp(true)//Будет ли ссылка действия электронной почты сначала открыта в мобильном приложении или в веб-ссылке.
             .setIOSBundleId("")// Это попытается открыть ссылку в приложении iOS, если оно установлено
             .setAndroidPackageName("",true,"12")//Это попытается открыть ссылку в приложении для Android
             .build();
 */


}