package com.sthiddov.macdiscussions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private LinearLayout layoutProgress;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        layoutProgress = (LinearLayout) findViewById(R.id.layoutProgress);
        webView.setVisibility(View.GONE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.c0:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/fhrs-thdyat-almhndsh-hbh-hta-la-tghybwa-ena/246534");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c1:
                        webView.loadUrl("https://macdiscussions.udacity.com");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c2:
                        webView.loadUrl("https://macdiscussions.udacity.com/c/android-track");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c3:
                        webView.loadUrl("https://macdiscussions.udacity.com/c/general-lounge");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c4:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/make-your-own-card/153245");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c5:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/share-your-just-java-app/209953");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c6:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/share-your-miwok-app/209885");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c7:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/share-your-court-counter-app/209880");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c8:
                        webView.loadUrl("https://macdiscussions.udacity.com/t/share-your-music-player-app/209954");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.c9:
                        webView.loadUrl("https://macdiscussions.udacity.com/c/android-share-your-projects-student-feedback");
                        dl.closeDrawer(GravityCompat.START);
                        break;
                }
                return false;
            }

        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                layoutProgress.setVisibility(View.GONE);
                progressBar.setIndeterminate(false);

                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                layoutProgress.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);

                super.onPageStarted(view, url, favicon);
            }
        });
        if (isOnline()) {
            webView.loadUrl("https://macdiscussions.udacity.com");
        } else {
            String summary = "<html><body><font color='red'>No Internet Connection</font></body></html>";
            webView.loadData(summary, "text/html", null);
            toast("No Internet Connection.");
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // Go to previous page
            return true;
        }
        // Use this as else part
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item)){}
        switch (item.getItemId()) {
            case R.id.back:
                webView.goBack();
                return true;
            case R.id.forward:
                webView.goForward();
                return true;
            case R.id.refresh:
                webView.reload();
                return true; }


        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolweb,menu);
        return super.onCreateOptionsMenu(menu);
    }



}