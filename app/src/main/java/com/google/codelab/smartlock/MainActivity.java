/**
 * Copyright Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.codelab.smartlock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int RC_SAVE = 1;
    private static final int RC_READ = 3;
    private static final String IS_RESOLVING = "is_resolving";
    private static final String IS_REQUESTING = "is_requesting";
    private static final String SPLASH_TAG = "splash_fragment";
    private static final String SIGN_IN_TAG = "sign_in_fragment";
    public static final int DELAY_MILLIS = 3000;

    // Add mGoogleApiClient and mIsResolving fields here.
    private boolean mIsResolving;
    private boolean mIsRequesting;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(getIntent());

        if (savedInstanceState != null) {
            mIsResolving = savedInstanceState.getBoolean(IS_RESOLVING);
            mIsRequesting = savedInstanceState.getBoolean(IS_REQUESTING);
        }

        // When not using Smart Lock show set Fragment in onCreate.
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setFragment(null);
            }
        }, DELAY_MILLIS);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current sign in state
        savedInstanceState.putBoolean(IS_RESOLVING, mIsResolving);
        savedInstanceState.putBoolean(IS_REQUESTING, mIsRequesting);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        mHandler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    /**
     * Set the appropriate fragment given the state of the Activity and the Intent used to start it.
     * If the intent is a launcher intent the Splash Fragment is shown otherwise the SignIn Fragment is shown.
     *
     * @param intent Intent used to determine which Fragment is used.
     */
    private void setFragment(Intent intent) {
        Fragment fragment;
        String tag;
        if (intent != null && intent.hasCategory(Intent.CATEGORY_LAUNCHER)) {
            fragment = new SplashFragment();
            tag = SPLASH_TAG;
        } else {
            fragment = new SignInFragment();
            tag = SIGN_IN_TAG;
        }
        String currentTag = getCurrentFragmentTag();
        if (currentTag == null || !currentTag.equals(tag)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, tag)
                    .commit();
        }
    }

    /**
     * Start the Content Activity and finish this one.
     */
    protected void goToContent() {
        startActivity(new Intent(this, ContentActivity.class));
        finish();
    }

    /**
     * If the currently displayed Fragment is the SignIn Fragment then enable or disable the sign in form.
     *
     * @param enable Enable form when true, disable form when false.
     */
    private void setSignInEnabled(boolean enable) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SIGN_IN_TAG);
        if (fragment != null && fragment.isVisible()) {
            ((SignInFragment) fragment).setSignEnabled(enable);
        }
    }

    /**
     * Get the tag of the currently set Fragment.
     *
     * @return Tag of currently set Fragment, or null if no fragment is set.
     */
    private String getCurrentFragmentTag() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList == null || fragmentList.size() == 0) {
            return null;
        }
        Fragment fragment = fragmentList.get(0);
        if (fragment != null) {
            return fragment.getTag();
        }
        return null;
    }

    /**
     * Check if the Splash Fragment is the currently selected Fragment.
     *
     * @return true if Splash Fragment is the current Fragment, false otherwise.
     */
    private boolean onSplashFragment() {
        return getCurrentFragmentTag().equals(SPLASH_TAG);
    }

    protected boolean isResolving() {
        return mIsResolving;
    }

    protected boolean isRequesting() {
        return mIsRequesting;
    }

}
