package com.tc.dm.rest.dto;

public enum ItemType {

    IMAGE("Image"), DOCUMENT("Document"), AUDIO("Audio"), VIDEO("Video"), COLLECTION("Collection"), MAP("Map");

    private String type;

    ItemType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

    public static ItemType fromString(String type){
        if(null == type) return null;
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
        } else if(MAP.toString().equals(type)) {
            return MAP;
        } else {
            return null;
        }
    }

}
