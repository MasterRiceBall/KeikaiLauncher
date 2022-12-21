/*
 * Copyright 2015-2017 Hayai Software
 * Copyright 2018-2022 The KeikaiLauncher Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anpmech.launcher.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.os.UserHandle;
import android.widget.GridView;

import com.anpmech.launcher.LauncherActivityInfoAdapter;
import com.anpmech.launcher.R;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final String className = MainActivity.class.getCanonicalName();

    private final LauncherActivityInfoAdapter adapter = new LauncherActivityInfoAdapter();

//    public void launchApplicationDetails(final MenuItem item) {
//        final LaunchableActivity activity = getLaunchableActivity(item);
//        final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        intent.setData(Uri.parse("package:" + activity.getComponent().getPackageName()));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    private void loadAdapter(LauncherApps launcher) {
        adapter.clear();
        for (UserHandle user : launcher.getProfiles()) {
            for (LauncherActivityInfo app : launcher.getActivityList(null, user)) {
                if (!app.getName().equals(className)) adapter.add(app);
            }
        }
        adapter.sort();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LauncherApps launcher = (LauncherApps) getSystemService(LAUNCHER_APPS_SERVICE);
        setContentView(R.layout.activity_main);
        loadAdapter(launcher);

        final GridView appContainer = findViewById(R.id.appsContainer);
        appContainer.setAdapter(adapter);
        appContainer.setOnItemClickListener((parent, view, position, id) -> adapter.launch(launcher, position));
    }

    /**
     * This method is called when the user is already in this activity and presses the {@code home}
     * button. Use this opportunity to return this activity back to a default state.
     *
     * @param intent The incoming {@link Intent} sent by this activity
     */
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);

        final LauncherApps launcher = (LauncherApps) getSystemService(LAUNCHER_APPS_SERVICE);
        loadAdapter(launcher);

        // If the y coordinate is not at 0, let's reset it.
        final GridView view = findViewById(R.id.appsContainer);
        view.smoothScrollToPosition(0);
//        final int[] loc = {0, 0};
//        view.getLocationInWindow(loc);
//        if (loc[1] != 0) {
//        }
    }

    @Override
    public void onBackPressed() {
    }
}
