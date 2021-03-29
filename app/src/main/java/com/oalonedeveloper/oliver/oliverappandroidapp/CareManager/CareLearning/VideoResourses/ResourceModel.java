package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

public class ResourceModel {

    String resource_doc_name,resource_name,resource_url,resource_extension;

    public ResourceModel() {
    }

    public ResourceModel(String resource_doc_name, String resource_name, String resource_url, String resource_extension) {
        this.resource_doc_name = resource_doc_name;
        this.resource_name = resource_name;
        this.resource_url = resource_url;
        this.resource_extension = resource_extension;
    }

    public String getResource_doc_name() {
        return resource_doc_name;
    }

    public void setResource_doc_name(String resource_doc_name) {
        this.resource_doc_name = resource_doc_name;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getResource_extension() {
        return resource_extension;
    }

    public void setResource_extension(String resource_extension) {
        this.resource_extension = resource_extension;
    }
}
