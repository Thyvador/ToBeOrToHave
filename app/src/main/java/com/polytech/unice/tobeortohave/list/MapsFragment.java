package com.polytech.unice.tobeortohave.list;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.dummy.ShopContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    private MapView mMapView;
    private EditText editText;
    private List<Marker> markerShopDetailMap;
    private LatLng position;

    public MapsFragment() {
        markerShopDetailMap = new ArrayList<>();
    }

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, null);
        mMapView = (MapView) rootView.findViewById(R.id.map);
        editText = (EditText) rootView.findViewById(R.id.editText);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                final RequestQueue queue = Volley.newRequestQueue(getContext());

                position = new LatLng(10,10);
                final Marker currentLocation = googleMap.addMarker(new MarkerOptions().position(position).title("Marker Title").snippet("Marker Description"));


                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            String stringBuilder = "https://maps.googleapis.com/maps/api/geocode/json?address=" + Uri.parse(v.getText().toString().replace(" ", "+")) +
                                    "&key=" + getContext().getResources().getString(R.string.google_maps_key) + "&language=fr";

                            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, stringBuilder, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                JSONObject location = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                                                currentLocation.setPosition(new LatLng(location.getDouble("lat"), location.getDouble("lng")));
                                                currentLocation.setZIndex(2.0f);
                                                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getDouble("lat"), location.getDouble("lng")))
                                                        .zoom(12).build();
                                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "Erreur : Adresse Invalide", Toast.LENGTH_LONG).show();
                                }
                            });
                            queue.add(stringRequest);
                            return true;
                        }
                        return false;
                    }
                });

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map


                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (markerShopDetailMap.contains(marker)) {
                            if (marker.isInfoWindowShown())
                                marker.hideInfoWindow();
                            else
                                marker.showInfoWindow();
                            return true;
                        }
                        return false;
                    }
                });

                for (final ShopContent.ShopDetail shopDetail : ShopContent.ITEMS) {
                    String stringBuilder = "https://maps.googleapis.com/maps/api/geocode/json?address=" + Uri.parse(shopDetail.adress.replace(" ", "+")) +
                            "&key=" + getContext().getResources().getString(R.string.google_maps_key) + "&language=fr";

                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, stringBuilder, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject location = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                                        Log.d("LOCATION : ", location.toString());

                                        markerShopDetailMap.add(googleMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(location.getDouble("lat"), location.getDouble("lng")))
                                                .title(shopDetail.name)
                                                .snippet(shopDetail.adress)
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(stringRequest);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
