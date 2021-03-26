package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

public class VideoModel {

    String position,video_image,video_name,video_url;

    public VideoModel() {
    }

    public VideoModel(String position, String video_image, String video_name, String video_url) {
        this.position = position;
        this.video_image = video_image;
        this.video_name = video_name;
        this.video_url = video_url;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVideo_image() {
        return video_image;
    }

    public void setVideo_image(String video_image) {
        this.video_image = video_image;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
