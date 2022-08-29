package com.ium.example.booking.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.ium.example.booking.MainActivity;
import java.util.ArrayList;
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
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    public void start(){
        String email = getActivity().getIntent().getExtras().getString("account");
        TextView account = getView().findViewById(R.id.accountValue);
        if(email.equals("Ospite")) {
            account.setText("Benvenuto " + email);
            TextView text = getView().findViewById(R.id.textOspite);
            text.setVisibility(View.VISIBLE);
        }else{
            TextView text = getView().findViewById(R.id.text);
            text.setVisibility(View.VISIBLE);
            account.setText("Bentornato " + email);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, MyURL.URLGETR, new Response.Listener<String>() {
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
        });
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
            if(status.equals("DISPONIBILE"))
                output.add("●" + corso + "\n  " + docente + "\n  " + giorno + "\n  " + ore);
        }
        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getActivity(), R.layout.row, output);
        ListView listview = getView().findViewById(R.id.listview);

        listview.setAdapter(adapterlist);
        if(!getActivity().getIntent().getExtras().getString("account").equals("Ospite")) {
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemValue = (String) listview.getItemAtPosition(position);
                    try {
                        JSONObject row = jsonArray.getJSONObject(position);
                        String codice = row.getString("codice");
                        prenota(codice);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(), "Hai effettuato la prenotazione di :\n" + itemValue, Toast.LENGTH_LONG).show();
                    refresh();
                }
            });
        }
    }

    public void prenota(String codice){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MyURL.URLPOST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                if (response.equals("aggiunta")) {
                }else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
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
                params.put("action", "Prenota");
                params.put("action2", codice);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void refresh(){
        getActivity().finish();
        startActivity(getActivity().getIntent());
    }
}