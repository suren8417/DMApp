package com.tc.dm.rest.dto;

import java.util.Date;

/**
 * Created by sg40304 on 12/12/15.
 */
public class SearchQueryDto {

    private String searchText;
    private String image;
    private String document;
    private String audio;
    private String video;
    private String collection;
    private Date startDate;
    private Date endDate;

    public String getSearchText() {
        return searchText;
    }

    public String getDocument() {
        return document;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public String getVideo() {
        return video;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getCollection() {
        return collection;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setDocument(String document) {
        this.document = document;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
