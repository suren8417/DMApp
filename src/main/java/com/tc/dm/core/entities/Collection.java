package com.tc.dm.core.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "collections", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER, targetEntity = Item.class)
//    @JoinTable(name = "item_collection", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Item> getItems() {
        if(null == this.items) {
            this.items = new HashSet<>();
        }
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public static Collection getInstance(Long id, String name, String description) {
        Collection collection = new Collection();
        collection.setId(id);
        collection.setName(name);
        collection.setDescription(description);
        return collection;
    }

    public Collection() {
        this.items = new HashSet<Item>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collection)) return false;

        Collection that = (Collection) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection preDelete() {
        for(Item item : new HashSet<Item>(this.getItems())) {
            removeItem(item);
        }
        return this;
    }

    public void removeItem(Item item) {
        this.getItems().remove(item);
        item.getCollections().remove(this);
    }
}
