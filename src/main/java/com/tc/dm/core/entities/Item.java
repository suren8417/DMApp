package com.tc.dm.core.entities;

import com.tc.dm.rest.dto.ItemContent;
import org.hibernate.envers.Audited;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
@Audited
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER, targetEntity = Collection.class)
    @JoinTable(name = "item_collection", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private Set<Collection> collections;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_origin")
    private Date dateOfOrigin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "added_by")
    private String addedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_validate")
    private Date dateValidated;

    @Column(name = "validated_by")
    private String validatedBy;

    @Column(name = "donor")
    private String donor;

    @Column(name = "description")
    private String description;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "status")
    private String status;

    @Column(name = "content_path")
    private String contentPath;

    @Transient
    private MultipartFile content;

    public MultipartFile getContent() {
        return content;
    }

    public void setContent(MultipartFile content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Collection> getCollections() {
        if(null == this.collections) {
            this.collections = new HashSet<>();
        }
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public static Item getInstance(){
        return new Item();
    }

    public Item() {
        this.collections = new HashSet<Collection>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (itemCode != null ? !itemCode.equals(item.itemCode) : item.itemCode != null) return false;
        if (type != null ? !type.equals(item.type) : item.type != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (dateOfOrigin != null ? !dateOfOrigin.equals(item.dateOfOrigin) : item.dateOfOrigin != null) return false;
        if (dateAdded != null ? !dateAdded.equals(item.dateAdded) : item.dateAdded != null) return false;
        if (addedBy != null ? !addedBy.equals(item.addedBy) : item.addedBy != null) return false;
        if (dateValidated != null ? !dateValidated.equals(item.dateValidated) : item.dateValidated != null)
            return false;
        if (validatedBy != null ? !validatedBy.equals(item.validatedBy) : item.validatedBy != null) return false;
        if (donor != null ? !donor.equals(item.donor) : item.donor != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (keywords != null ? !keywords.equals(item.keywords) : item.keywords != null) return false;
        if (status != null ? !status.equals(item.status) : item.status != null) return false;
        return !(contentPath != null ? !contentPath.equals(item.contentPath) : item.contentPath != null);

    }

    @Override
    public int hashCode() {
        int result = itemCode != null ? itemCode.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dateOfOrigin != null ? dateOfOrigin.hashCode() : 0);
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + (addedBy != null ? addedBy.hashCode() : 0);
        result = 31 * result + (dateValidated != null ? dateValidated.hashCode() : 0);
        result = 31 * result + (validatedBy != null ? validatedBy.hashCode() : 0);
        result = 31 * result + (donor != null ? donor.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (contentPath != null ? contentPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", dateOfOrigin=" + dateOfOrigin +
                ", dateAdded=" + dateAdded +
                ", addedBy='" + addedBy + '\'' +
                ", dateValidated=" + dateValidated +
                ", validatedBy='" + validatedBy + '\'' +
                ", donor='" + donor + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", status='" + status + '\'' +
                ", contentPath='" + contentPath + '\'' +
                '}';
    }

    public Item preDelete() {
       for(Collection collection : new HashSet<>(this.getCollections())) {
           removeCollection(collection);
       }
        return this;
    }

    public void removeCollection(Collection collection) {
        this.getCollections().remove(collection);
        collection.getItems().remove(this);
    }
}
