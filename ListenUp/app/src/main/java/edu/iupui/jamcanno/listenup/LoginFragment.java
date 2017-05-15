package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eric Smith on 4/26/2017.
 */

public class LoginFragment extends Fragment {

    private Button loginBtn;
    private EditText mEditUsername;
    private EditText mEditPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //INFLATE LAYOUT FILE
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //WIRE UP STUFF

        loginBtn = (Button) view.findViewById(R.id.loginButton);
        mEditUsername = (EditText) view.findViewById(R.id.userName);
        mEditPassword = (EditText) view.findViewById(R.id.passWord);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEditUsername.getText().toString();
                String password = mEditPassword.getText().toString();

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("gPodderUsername", username);
                editor.putString("gPodderPassword", password);
                editor.commit();

                //MAKE CONNECTION TO THE API
                GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, "samtrissler", "284ujenrs");

                // username, password ^^

                Call<String> call = client.getLogin(username);
                Log.d("LISTENUP", "Attempting to Login");

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("LISTENUP", "Success!");
                        Log.d("LISTENUP", String.valueOf(response.code()) + String.valueOf(response));
                        Log.d("LISTENUP", String.valueOf(response.body()));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("LISTENUP", "womp womp on login");
                    }
                });

                Intent intent = new Intent(getActivity(),TagListActivity.class);
                //  startActivity(intent);

            }
        });


        return view;

    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

}

