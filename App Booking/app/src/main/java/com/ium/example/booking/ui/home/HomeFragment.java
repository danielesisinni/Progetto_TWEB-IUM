package com.ium.example.booking.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ium.example.booking.R;
import com.ium.example.booking.controller.MyURL;
import com.ium.example.booking.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RequestQueue requestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
        });*/

        System.out.println("Forse ci sono");
        start();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    public void start(){
        String email = getActivity().getIntent().getExtras().getString("account");
        System.out.println(email);
        //TextView account = getView().findViewById(R.id.accountValue);
        //account.setText(email);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, MyURL.servletURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                try {
                    stampa(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        }) {
            //Aggiungo i parametri alla richiesta
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("action", "androidR");
                params.put("action2", "android");
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void stampa(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        ArrayList<String> output = new ArrayList<>();

        for (int position = 0; position < jsonArray.length(); position++) {
            JSONObject row = jsonArray.getJSONObject(position);

            String docente = row.getString("docente");
            String corso = row.getString("corso");
            String giorno = row.getString("giorno");
            String ore = row.getString("ora");
            System.out.println(position);
            output.add(" - " + docente + "\n - " + corso + "\n - " + giorno + "\n - " + ore);
            System.out.println(output);
        }
        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getActivity(), R.layout.row, output);
        ListView listview = getView().findViewById(R.id.listview);

        listview.setAdapter(adapterlist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listview.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Position : " + position + "ListItem :" +itemValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}