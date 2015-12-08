package com.tc.dm.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItemSearchParam {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    public static enum SearchType {

        IMAGE("Image"), DOCUMENT("Document"), AUDIO("Audio"), VIDEO("Video"), COLLECTION("Collection");

        private String type;

        SearchType(String type) {
            this.type = type;
        }

        public String toString() {
            return type;
        }

        public static SearchType fromString(String type){
            if(IMAGE.toString().equals(type)) {
                return IMAGE;
            } else if(DOCUMENT.toString().equals(type)) {
                return DOCUMENT;
            } else if(AUDIO.toString().equals(type)) {
                return AUDIO;
            } else if(VIDEO.toString().equals(type)) {
                return VIDEO;
            } else if(COLLECTION.toString().equals(type)) {
                return COLLECTION;
            } else {
                return null;
            }
        }

    }

    public ItemSearchParam() {
        this.caseSensitive = false;
        this.matchWhole = false;
    }

    private String textToSearch;
    private SearchType type;
    private Date dateOfOriginFrom;
    private Date dateOfOriginTo;
    private boolean caseSensitive;
    private boolean matchWhole;

    public String getTextToSearch() {
        return textToSearch;
    }

    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }

    public SearchType getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type;
    }

    public Date getDateOfOriginFrom() {
        return dateOfOriginFrom;
    }

    public void setDateOfOriginFrom(Date dateOfOriginFrom) {
        this.dateOfOriginFrom = dateOfOriginFrom;
    }

    public Date getDateOfOriginTo() {
        return dateOfOriginTo;
    }

    public void setDateOfOriginTo(Date dateOfOriginTo) {
        this.dateOfOriginTo = dateOfOriginTo;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean isMatchWhole() {
        return matchWhole;
    }

    public void setMatchWhole(boolean matchWhole) {
        this.matchWhole = matchWhole;
    }

    public Map<String, String> toMap(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("textToSearch", textToSearch);
        params.put("type", type.toString());
        params.put("dateOfOriginFrom", sdf.format(dateOfOriginFrom));
        params.put("dateOfOriginTo", sdf.format(dateOfOriginTo));
        params.put("caseSensitive", String.valueOf(caseSensitive));
        params.put("matchWhole", String.valueOf(matchWhole));
        return params;
    }

    public static ItemSearchParam fromMap(Map<String, String> params) {
        ItemSearchParam itemSearchParam = new ItemSearchParam();
        itemSearchParam.setTextToSearch(params.get("textToSearch"));
        itemSearchParam.setType(SearchType.fromString(params.get("type")));
        try {
            itemSearchParam.setDateOfOriginFrom(sdf.parse(params.get("dateOfOriginFrom")));
        } catch (ParseException e) {
            //Default null will be set
        }
        try {
            itemSearchParam.setDateOfOriginTo(sdf.parse(params.get("dateOfOriginTo")));
        } catch (ParseException e) {
            //Default null will be set
        }
        itemSearchParam.setCaseSensitive(Boolean.parseBoolean(params.get("caseSensitive")));
        itemSearchParam.setMatchWhole(Boolean.parseBoolean(params.get("matchWhole")));
        return itemSearchParam;
    }
}
