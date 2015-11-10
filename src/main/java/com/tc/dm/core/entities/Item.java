package com.tc.dm.core.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "title")
    private String title;

    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "date_of_origin")
    private Date dateOfOrigin;

    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "date_validate")
    private Date dateValidated;

    @Column(name = "donor")
    private String donor;

    @Column(name = "description")
    private String description;

    @Column(name = "key_word_id")
    private Long keyWordId;

    @Column(name = "status")
    private String status;

    @Column(name = "content_path")
    private String contentPath;

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    private Item() {
    }

    public static Item createItem() {
        return new Item();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Date getDateOfOrigin() {
        return dateOfOrigin;
    }

    public void setDateOfOrigin(Date dateOfOrigin) {
        this.dateOfOrigin = dateOfOrigin;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getKeyWordId() {
        return keyWordId;
    }

    public void setKeyWordId(Long keyWordId) {
        this.keyWordId = keyWordId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        if (!typeId.equals(item.typeId)) return false;
        if (!title.equals(item.title)) return false;
        if (!collectionId.equals(item.collectionId)) return false;
        if (!dateOfOrigin.equals(item.dateOfOrigin)) return false;
        if (dateAdded != null ? !dateAdded.equals(item.dateAdded) : item.dateAdded != null) return false;
        if (dateValidated != null ? !dateValidated.equals(item.dateValidated) : item.dateValidated != null)
            return false;
        if (!donor.equals(item.donor)) return false;
        if (!description.equals(item.description)) return false;
        if (!keyWordId.equals(item.keyWordId)) return false;
        if (!status.equals(item.status)) return false;
        return contentPath.equals(item.contentPath);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + typeId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + collectionId.hashCode();
        result = 31 * result + dateOfOrigin.hashCode();
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + (dateValidated != null ? dateValidated.hashCode() : 0);
        result = 31 * result + donor.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + keyWordId.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + contentPath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", title='" + title + '\'' +
                ", collectionId=" + collectionId +
                ", dateOfOrigin=" + dateOfOrigin +
                ", dateAdded=" + dateAdded +
                ", dateValidated=" + dateValidated +
                ", donor='" + donor + '\'' +
                ", description='" + description + '\'' +
                ", keyWordId=" + keyWordId +
                ", status='" + status + '\'' +
                ", contentPath='" + contentPath + '\'' +
                '}';
    }
}
