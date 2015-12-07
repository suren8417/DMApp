package com.tc.dm.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "keyword")
public class KeyWord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "key_word")
    private String keyWord;

    @ManyToMany(mappedBy = "keyWords", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public static KeyWord getInstance(String keyWordStr) {
        KeyWord keyWord = new KeyWord();
        keyWord.setKeyWord(keyWordStr);
        return keyWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyWord)) return false;

        KeyWord keyWord1 = (KeyWord) o;

        if (id != null ? !id.equals(keyWord1.id) : keyWord1.id != null) return false;
        return !(keyWord != null ? !keyWord.equals(keyWord1.keyWord) : keyWord1.keyWord != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keyWord != null ? keyWord.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KeyWord{" +
                "keyWord='" + keyWord + '\'' +
                ", id=" + id +
                '}';
    }
}
