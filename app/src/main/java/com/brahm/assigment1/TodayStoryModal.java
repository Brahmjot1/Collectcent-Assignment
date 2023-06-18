package com.brahm.assigment1;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TodayStoryModal
{


    String imageViewTodayStory;

    String txtStoryView;


    public TodayStoryModal(String imageViewTodayStory, String txtStoryView) {
        this.imageViewTodayStory = imageViewTodayStory;
        this.txtStoryView = txtStoryView;
    }

    public String getTxtStoryView() {
        return txtStoryView;
    }

    public void setTxtStoryView(String txtStoryView) {
        this.txtStoryView = txtStoryView;
    }



    public String getImageView() {
        return imageViewTodayStory;
    }

    public void setImageView(String imageView) {
        this.imageViewTodayStory = imageView;
    }
}
