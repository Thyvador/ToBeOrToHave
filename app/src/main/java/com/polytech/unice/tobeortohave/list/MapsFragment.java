package com.polytech.unice.tobeortohave.list;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
    private List<Marker> markerShopDetailMap;
    private Marker currentLocation;
    private RequestQueue queue;

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

        (rootView.findViewById(R.id.fab)).setOnClickListener((ShopListActivity) getActivity());

        mMapView = (MapView) rootView.findViewById(R.id.map);
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

                queue = Volley.newRequestQueue(getContext());

                final LatLngBounds.Builder builder = new LatLngBounds.Builder();


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
                                        builder.include(new LatLng(location.getDouble("lat"), location.getDouble("lng")));
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

                queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {
                        LatLngBounds bounds = builder.build();
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 150);
                        googleMap.moveCamera(cu);

                    }
                });
            }
        });

        return rootView;
    }

    public void setCurrentPosition(String str) {
        String stringBuilder = "https://maps.googleapis.com/maps/api/geocode/json?address=" + Uri.parse(str.replace(" ", "+")) +
                "&key=" + getResources().getString(R.string.google_maps_key) + "&language=fr";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, stringBuilder, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject location = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                            final CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getDouble("lat"), location.getDouble("lng")))
                                    .zoom(15).build();
                            mMapView.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap googleMap) {
                                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                }
                            });

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

    interface OnclickLIstener extends View.OnClickListener {
        @Override
        void onClick(View v);
    }

}
