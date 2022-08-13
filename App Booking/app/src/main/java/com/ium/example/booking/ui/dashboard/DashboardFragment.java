package com.ium.example.booking.ui.dashboard;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.ium.example.booking.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    RequestQueue requestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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
        System.out.println("Dashboard " + email);
        //TextView account = getView().findViewById(R.id.accountValue);
        //account.setText(email);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, MyURL.servletURL, new Response.Listener<String>() {
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
                params.put("action", "androidP");
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
            String status = row.getString("status");
            System.out.println(position);
            output.add(" - " + corso + "\n - " + docente + "\n - " + giorno + "\n - " + ore + "\n - " + status);
            System.out.println(output);
        }
        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getActivity(), R.layout.row, output);
        ListView listview = getView().findViewById(R.id.listview);

        listview.setAdapter(adapterlist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listview.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Prova :\n" +itemValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}