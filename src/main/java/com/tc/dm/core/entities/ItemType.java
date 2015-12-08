package com.tc.dm.core.entities;

import javax.persistence.*;

@Entity
@Table(name = "item_type")
public class ItemType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemType", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Item> items;

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

//    public Set<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<Item> items) {
//        this.items = items;
//    }

    public static ItemType getInstance(Long id, String name, String description) {
        ItemType itemType = new ItemType();
        itemType.setId(id);
        itemType.setName(name);
        itemType.setDescription(description);
        return itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemType)) return false;

        ItemType itemType = (ItemType) o;

        if (id != null ? !id.equals(itemType.id) : itemType.id != null) return false;
        if (name != null ? !name.equals(itemType.name) : itemType.name != null) return false;
        return !(description != null ? !description.equals(itemType.description) : itemType.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
