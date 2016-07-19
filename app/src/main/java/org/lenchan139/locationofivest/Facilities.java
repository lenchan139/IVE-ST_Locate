package org.lenchan139.locationofivest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Facilities extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private WebView webView;
    private ProgressDialog progressDialog;
    private Button btnG,btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnG = (Button) findViewById(R.id.btnGF);
        btn1 = (Button) findViewById(R.id.btn1F);
        btn2 = (Button) findViewById(R.id.btn2F);
        btn3 = (Button) findViewById(R.id.btn3F);
        btn4 = (Button) findViewById(R.id.btn4F);
        btn5 = (Button) findViewById(R.id.btn5F);
        webView = (WebView) findViewById(R.id.viewIvestMaps);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        progressDialog = new ProgressDialog(Facilities.this);
        progressDialog.setMessage("Now loading......");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressDialog.dismiss();
            }
        });

        webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-g-f.jpg");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-g-f.jpg");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-1-f.jpg");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-2-f.jpg");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-3-f.jpg");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-4-f.jpg");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                webView.loadUrl("https://lenchan139.org/myWorks/IvestMaps/map-5-f.jpg");
            }
        });
        fab.setVisibility(View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == id+1) {
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
        } else if (id == R.id.nav_About_ivest) {
            Intent intent = new Intent(this, About_IVEST.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        } else if (id == R.id.nav_find_ivest) {
            Intent intent = new Intent(this, Find_IVEST.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finishX();

        }  else if (id == R.id.nav_comments) {
            Intent intent = new Intent(this, Comment.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
    public void finishX(){
        overridePendingTransition(0, 0);
        finish();
    }
}
