package com.mdstudio.chatapp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mdstudio.chatapp.R;
import com.mdstudio.chatapp.databinding.FragmentSigninBinding;
import com.mdstudio.chatapp.view.MainActivity;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private FragmentSigninBinding binding;
    // Fireabase
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Sign in
        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {

        final String username = binding.edUsername.getText().toString().trim();
        final String email = binding.edEmail.getText().toString().trim();
        final String password = binding.edPassword.getText().toString().trim();


        if (username.equals("")) {
            Toast.makeText(getContext(), getString(R.string.enter_username), Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            Toast.makeText(getContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(getContext(), getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                String user_id = mAuth.getCurrentUser().getUid();

                                mReference = FirebaseDatabase.getInstance().getReference("users").child(user_id);

                                HashMap<String, String> user_data = new HashMap<>();
                                user_data.put("id", user_id);
                                user_data.put("username", username);
                                user_data.put("email", email);
                                user_data.put("password", password);

                                mReference.setValue(user_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // sign in success
                                            startActivity(new Intent(getContext(), MainActivity.class));
                                            getActivity().finish();
                                        }
                                    }
                                });


                            } else {
                                // sign in failed
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
