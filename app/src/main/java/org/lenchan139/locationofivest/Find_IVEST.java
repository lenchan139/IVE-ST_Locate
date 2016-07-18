package org.lenchan139.locationofivest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.plus.Plus;

public class Find_IVEST extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    GoogleApiClient client;
    LatLng curr;
    Location mLastLocation;
    LatLng targetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final LatLng NKUT = new LatLng(23.979548, 120.696745);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ivest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });


        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button btnReposition = (Button) findViewById(R.id.btnRePosition);
        btnReposition.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                client.disconnect();
                client.connect();
                mMap.clear();
                markerX();
                if(curr != null ) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curr, 16));
                }

            }
        });
        Button btnTargetLocation = (Button) findViewById(R.id.btnTargetPosision) ;
        btnTargetLocation.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                client.disconnect();
                client.connect();
                mMap.clear();
                markerX();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 16));


            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == id + 1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            Intent intent = new Intent(this, News.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();
        }  else if (id == R.id.nav_comments) {
            Intent intent = new Intent(this, Comment.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        } else if (id == R.id.nav_About_ivest) {
            Intent intent = new Intent(this, About_IVEST.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        } else if (id == R.id.nav_find_ivest) {
            Intent intent = new Intent(this, Find_IVEST.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        } else if (id == R.id.nav_facilities) {
            Intent intent = new Intent(this, Facilities.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        }  else if (id == R.id.nav_ive_website) {
            String url = "https://www.ive.edu.hk/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (id == R.id.nav_github) {
            String url = "https://github.com/lenchan139/IVE-ST_Locate";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else{}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void finishX() {
        overridePendingTransition(0, 0);
        finish();
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        targetLocation = new LatLng(22.390516, 114.1960243);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);

            return;

        }
        mMap.addMarker(new MarkerOptions().position(targetLocation).title("The Hong Kong Institute of Vocational Education (Sha Tin)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 16));

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(Find_IVEST.this, "Error!", Toast.LENGTH_SHORT).show();
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                client);
        if (mLastLocation != null) {
            curr = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            Toast.makeText(Find_IVEST.this, curr.latitude + "|" + curr.longitude, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    protected void onStart() {
        client.connect();
        super.onStart();
    }

    protected void onStop() {
        client.disconnect();
        super.onStop();
    }

    public void markerX(){
        if(curr != null ) {
            MarkerOptions markerOpt = new MarkerOptions();
            markerOpt.position(curr);
            markerOpt.title("Your location");
            markerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(markerOpt).showInfoWindow();
        }

        MarkerOptions markerOpt2 = new MarkerOptions();
        markerOpt2.position(targetLocation);
        markerOpt2.title("The Hong Kong Institute of Vocational Education (Sha Tin)");
        markerOpt2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOpt2).showInfoWindow();

        if(curr != null) {
            PolylineOptions polylineOpt = new PolylineOptions();
            polylineOpt.add(curr);
            polylineOpt.add(targetLocation);

//線條顏色
            polylineOpt.color(Color.RED);
//線條寬度
            polylineOpt.width(10);
            Polyline polyline = mMap.addPolyline(polylineOpt);
        }

    }
}
