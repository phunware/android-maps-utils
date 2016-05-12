package com.google.maps.android.ui;

/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.maps.android.R;

/**
 * Created by mrand on 5/12/16.
 */
public class IconGenerator {

    private ViewGroup mContainer;
    private TextView mTextView;
    private View mContentView;

    public IconGenerator(Context context) {
        mContainer = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.amu_icon_text_marker, null);
        mTextView =(TextView) mContainer.findViewById(R.id.title);
    }

    public Bitmap makeIcon(CharSequence text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }

        return makeIcon();
    }

    public Bitmap makeIcon(CharSequence text, Bitmap iconImage) {
        if (mContainer.findViewById(R.id.icon) != null) {
            ((ImageView)mContainer.findViewById(R.id.icon)).setImageBitmap(iconImage);
        }
        return makeIcon(text);
    }

    public Bitmap makeIcon(CharSequence text, int iconResId) {
        if (mContainer.findViewById(R.id.icon) != null) {
            ((ImageView)mContainer.findViewById(R.id.icon)).setImageResource(iconResId);
        }
        return makeIcon(text);
    }

    public Bitmap makeIcon() {
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mContainer.measure(measureSpec, measureSpec);

        int measuredWidth = mContainer.getMeasuredWidth();
        int measuredHeight = mContainer.getMeasuredHeight();

        mContainer.layout(0, 0, measuredWidth, measuredHeight);

        Bitmap r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        r.eraseColor(Color.TRANSPARENT);

        Canvas canvas = new Canvas(r);

        mContainer.draw(canvas);
        return r;
    }

    public void setContentView (View contentView) {
        mContainer.removeAllViews();
        mContainer.addView(contentView);
        mContentView = contentView;
        final View view = mContainer.findViewById(R.id.title);
        mTextView = view instanceof TextView ? (TextView)view : null;
    }

}
