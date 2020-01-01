package com.app.appName.entity;

public class BowenTagEntity {
    private int id;
    private int tagId;
    private int bowenId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getBowenId() {
        return bowenId;
    }

    public void setBowenId(int bowenId) {
        this.bowenId = bowenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BowenTagEntity that = (BowenTagEntity) o;

        if (id != that.id) return false;
        if (tagId != that.tagId) return false;
        if (bowenId != that.bowenId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + tagId;
        result = 31 * result + bowenId;
        return result;
    }
}
