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

package com.anpmech.launcher;

import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivityInfoAdapter extends BaseAdapter {
    final List<LauncherActivityInfo> apps = new ArrayList<>();

    public void clear() {
        apps.clear();
    }

    public void add(LauncherActivityInfo app) {
        apps.add(app);
    }

    public void sort() {
        apps.sort((o1, o2) -> o1.getLabel().toString().compareToIgnoreCase(o2.getLabel().toString()));
    }

    public void launch(LauncherApps launcher, int position) {
        LauncherActivityInfo app = apps.get(position);
        launcher.startMainActivity(app.getComponentName(), app.getUser(), null, Bundle.EMPTY);
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.app_grid_item, parent, false);
        }

        final LauncherActivityInfo app = apps.get(position);
        final TextView labelView = convertView.findViewById(R.id.appLabel);
        final ImageView iconView = convertView.findViewById(R.id.appIcon);
        labelView.setText(app.getLabel());
        iconView.setImageDrawable(app.getBadgedIcon(0));
        return convertView;
    }
}
