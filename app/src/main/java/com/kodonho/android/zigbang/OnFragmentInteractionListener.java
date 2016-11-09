package com.kodonho.android.zigbang;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by fastcampus on 2016-11-08.
 */

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
    DatabaseReference getRoomReference();
}