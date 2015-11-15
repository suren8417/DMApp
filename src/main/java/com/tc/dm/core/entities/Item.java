package com.tc.dm.core.entities;

import com.tc.dm.rest.dto.ItemContent;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private ItemType itemType;

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "item_collection", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private Set<Collection> collections;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_origin")
    private Date dateOfOrigin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_added")
    private Date dateAdded;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_validate")
    private Date dateValidated;

    @Column(name = "donor")
    private String donor;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "item_keyword", joinColumns = @JoinColumn(name = "item_id"),inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<KeyWord> keyWords;

    @Column(name = "status")
    private String status;

    @Column(name = "content_path")
    private String contentPath;

    @Transient
    private ItemContent content;

    public ItemContent getContent() {
        return content;
    }

    public void setContent(ItemContent content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Collection> getCollections() {
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

    public Set<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<KeyWord> keyWords) {
        this.keyWords = keyWords;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (itemType != null ? !itemType.equals(item.itemType) : item.itemType != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (collections != null ? !collections.equals(item.collections) : item.collections != null) return false;
        if (dateOfOrigin != null ? !dateOfOrigin.equals(item.dateOfOrigin) : item.dateOfOrigin != null) return false;
        if (dateAdded != null ? !dateAdded.equals(item.dateAdded) : item.dateAdded != null) return false;
        if (dateValidated != null ? !dateValidated.equals(item.dateValidated) : item.dateValidated != null)
            return false;
        if (donor != null ? !donor.equals(item.donor) : item.donor != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (keyWords != null ? !keyWords.equals(item.keyWords) : item.keyWords != null) return false;
        if (status != null ? !status.equals(item.status) : item.status != null) return false;
        return !(contentPath != null ? !contentPath.equals(item.contentPath) : item.contentPath != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (collections != null ? collections.hashCode() : 0);
        result = 31 * result + (dateOfOrigin != null ? dateOfOrigin.hashCode() : 0);
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + (dateValidated != null ? dateValidated.hashCode() : 0);
        result = 31 * result + (donor != null ? donor.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (keyWords != null ? keyWords.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (contentPath != null ? contentPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", collections=" + collections +
                ", dateOfOrigin=" + dateOfOrigin +
                ", dateAdded=" + dateAdded +
                ", dateValidated=" + dateValidated +
                ", donor='" + donor + '\'' +
                ", description='" + description + '\'' +
                ", keyWords=" + keyWords +
                ", status='" + status + '\'' +
                ", contentPath='" + contentPath + '\'' +
                '}';
    }

    public Item preDelete() {
       for(Collection collection : new HashSet<>(this.getCollections())) {
           removeCollection(collection);
       }
       for(KeyWord keyWord : new HashSet<>(this.getKeyWords())) {
           removeKeyWord(keyWord);
       }
        this.setItemType(null);
        return this;
    }

    public void removeCollection(Collection collection) {
        this.getCollections().remove(collection);
        collection.getItems().remove(this);
    }

    public void removeKeyWord(KeyWord keyWord) {
        this.getKeyWords().remove(keyWord);
        keyWord.getItems().remove(this);
    }

    public Item() {
        this.collections = new HashSet<Collection>();
        this.keyWords = new HashSet<KeyWord>();
    }
}
