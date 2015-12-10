package com.tc.dm.rest.dto;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tc.dm.core.util.CommonUtil.*;

public class ItemSearchParam {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public ItemSearchParam() {
        this.caseSensitive = false;
        this.matchWhole = false;
        getTypes();
    }

    private String textToSearch;
    private List<ItemType> types;
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
        params.put("types", isNullOrEmpty(types)?null:StringUtils.collectionToDelimitedString(this.types, ","));
        params.put("dateOfOriginFrom", isNullOrEmpty(dateOfOriginFrom)?null:sdf.format(dateOfOriginFrom));
        params.put("dateOfOriginTo", isNullOrEmpty(dateOfOriginTo)?null:sdf.format(dateOfOriginTo));
        params.put("caseSensitive", String.valueOf(caseSensitive));
        params.put("matchWhole", String.valueOf(matchWhole));
        return params;
    }

    public static ItemSearchParam fromMap(Map<String, String> params) {
        ItemSearchParam itemSearchParam = new ItemSearchParam();
        itemSearchParam.setTextToSearch(params.get("textToSearch"));
        String iTypes[] = StringUtils.split(params.get("types"), ",");
        if(null != iTypes) {
            for(String type : iTypes) {
                itemSearchParam.getTypes().add(ItemType.fromString(type));
            }
        }
        try {
            itemSearchParam.setDateOfOriginFrom(isNullOrEmpty(params.get("dateOfOriginFrom"))?null:sdf.parse(
                    params.get("dateOfOriginFrom")));
        } catch (ParseException e) {
            //Default null will be set
        }
        try {
            itemSearchParam.setDateOfOriginTo(isNullOrEmpty(params.get("dateOfOriginTo"))?null:sdf.parse(
                    params.get("dateOfOriginTo")));
        } catch (ParseException e) {
            //Default null will be set
        }
        itemSearchParam.setCaseSensitive(Boolean.parseBoolean(params.get("caseSensitive")));
        itemSearchParam.setMatchWhole(Boolean.parseBoolean(params.get("matchWhole")));
        return itemSearchParam;
    }
}
