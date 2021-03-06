package com.tc.dm.rest.dto;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tc.dm.core.util.CommonUtil.*;

public class SearchParam {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public SearchParam() {
        this.caseSensitive = false;
        this.matchWhole = false;
        getTypes();
    }

    private String textToSearch;
    private List<ItemType> types;
    private List<ItemStatus> status;
    private Date dateOfOriginFrom;
    private Date dateOfOriginTo;
    private Date dateAddedFrom;
    private Date dateAddedTo;
    private boolean caseSensitive;
    private boolean matchWhole;

    public String getTextToSearch() {
        return textToSearch;
    }

    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }

    public List<ItemType> getTypes() {
        if(null == this.types) {
            this.types = new ArrayList<>();
        }
        return this.types;
    }

    public List<String> getTypesAsStrings() {
        List<String> itemTypesAsStrings = new ArrayList<>();
        for(ItemType type : this.getTypes()) {
            itemTypesAsStrings.add(type.toString());
        }
        return itemTypesAsStrings;
    }

    public void setTypes(List<ItemType> types) {
        this.types = types;
    }

    public List<ItemStatus> getStatus() {
        if(null == this.status) {
            this.status = new ArrayList<>();
        }
        return status;
    }

    public List<String> getStatusAsStrings() {
        List<String> itemStatusAsStrings = new ArrayList<>();
        for(ItemStatus status : this.getStatus()) {
            itemStatusAsStrings.add(status.toString());
        }
        return itemStatusAsStrings;
    }

    public void setStatus(List<ItemStatus> status) {
        this.status = status;
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

    public Date getDateAddedFrom() {
        return dateAddedFrom;
    }

    public void setDateAddedFrom(Date dateAddedFrom) {
        this.dateAddedFrom = dateAddedFrom;
    }

    public Date getDateAddedTo() {
        return dateAddedTo;
    }

    public void setDateAddedTo(Date dateAddedTo) {
        this.dateAddedTo = dateAddedTo;
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
        params.put("types", isNullOrEmpty(types)?null:StringUtils.collectionToDelimitedString(this.types, ","));
        params.put("status", isNullOrEmpty(status)?null:StringUtils.collectionToDelimitedString(this.status, ","));
        params.put("dateOfOriginFrom", isNullOrEmpty(dateOfOriginFrom)?null:sdf.format(dateOfOriginFrom));
        params.put("dateOfOriginTo", isNullOrEmpty(dateOfOriginTo)?null:sdf.format(dateOfOriginTo));
        params.put("dateAddedFrom", isNullOrEmpty(dateAddedFrom)?null:sdf.format(dateAddedFrom));
        params.put("dateAddedTo", isNullOrEmpty(dateAddedTo)?null:sdf.format(dateAddedTo));
        params.put("caseSensitive", String.valueOf(caseSensitive));
        params.put("matchWhole", String.valueOf(matchWhole));
        return params;
    }

    public static SearchParam fromMap(Map<String, String> params) {
        SearchParam searchParam = new SearchParam();
        searchParam.setTextToSearch(params.get("textToSearch"));
        String iTypes[] = params.get("types")==null?null:params.get("types").split(",");
        if(null != iTypes) {
            for(String type : iTypes) {
                searchParam.getTypes().add(ItemType.fromString(type));
            }
        }
        String iStatus[] = params.get("status")==null?null:params.get("status").split(",");
        if(null != iStatus) {
            for(String status : iStatus) {
                searchParam.getStatus().add(ItemStatus.fromString(status));
            }
        }
        try {
            searchParam.setDateOfOriginFrom(isNullOrEmpty(params.get("dateOfOriginFrom"))?null:sdf.parse(params.get("dateOfOriginFrom")));
        } catch (ParseException e) {
            //Default null will be set
        }
        try {
            searchParam.setDateOfOriginTo(isNullOrEmpty(params.get("dateOfOriginTo"))?null:sdf.parse(params.get("dateOfOriginTo")));
        } catch (ParseException e) {
            //Default null will be set
        }
        try {
            searchParam.setDateAddedFrom(isNullOrEmpty(params.get("dateAddedFrom"))?null:sdf.parse(params.get("dateAddedFrom")));
        } catch (ParseException e) {
            //Default null will be set
        }
        try {
            searchParam.setDateAddedTo(isNullOrEmpty(params.get("dateAddedTo"))?null:sdf.parse(params.get("dateAddedTo")));
        } catch (ParseException e) {
            //Default null will be set
        }
        searchParam.setCaseSensitive(Boolean.parseBoolean(params.get("caseSensitive")));
        searchParam.setMatchWhole(Boolean.parseBoolean(params.get("matchWhole")));
        return searchParam;
    }
}
