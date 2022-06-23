package com.ium.example.booking.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.ium.example.booking.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class LoginActivity extends AppCompatActivity {
    //@BindView(R.id.activity_login_input_email)
    TextInputEditText emailValue;

    //@BindView(R.id.activity_login_input_password)
    TextInputEditText passwordValue;

    //@BindView(R.id.activity_login_button)
    Button loginButton;

    public static String servletURL = "http://10.0.2.2:8080/demo_war_exploded/ServletController";
    String URL = "http://localhost:8080/demo_war_exploded/ServletController";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view) {

        String email = emailValue.getText().toString();
        String password = passwordValue.getText().toString();

        if (email.isEmpty()) {
            emailValue.setError(getString(R.string.activity_login_input_email_error_empty));
            emailValue.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).find()) {
            emailValue.setError(getString(R.string.activity_login_input_email_error_invalid));
            emailValue.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordValue.setError(getString(R.string.activity_login_input_email_error_empty));
            passwordValue.requestFocus();
            return;
        }


        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Title", "Android Volley Demo");
            jsonBody.put("Author", "BNK");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent();
        disableView(true);
    }

    private void disableView(boolean bool) {
        runOnUiThread(() -> {
            emailValue.setEnabled(!bool);
            passwordValue.setEnabled(!bool);
            loginButton.setEnabled(!bool);
        });
    }
}
