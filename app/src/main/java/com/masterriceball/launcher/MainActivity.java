/*
 * Copyright 2015-2017 Hayai Software
 * Copyright 2018-2022 The KeikaiLauncher Project
 * Copyright 2022 MasterRiceBall
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

package com.masterriceball.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private final LauncherActivityInfoAdapter adapter = new LauncherActivityInfoAdapter();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter.init(this);

        final GridView appContainer = findViewById(R.id.appsContainer);
        appContainer.setAdapter(adapter);
        appContainer.setOnItemClickListener((parent, view, position, id) -> adapter.launch(position));
        appContainer.setOnItemLongClickListener((parent, view, position, id) -> adapter.launchAppDetails(position));
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

        adapter.init(this);

        final GridView view = findViewById(R.id.appsContainer);
        view.smoothScrollToPosition(0);
    }

    @Override
    public void onBackPressed() {
    }
}
