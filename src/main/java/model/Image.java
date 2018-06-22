package model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table
public class Image {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Lob
    @Column(nullable = false)
    private Blob data;
    @OneToOne
    private Format format;
    private String extention;

    public Image() {
    }

    public Image(String name, Format format, String extention) {
        this.name = name;
        this.format = format;
        this.extention = extention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        return data.equals(image.data);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + data +
                ", format=" + format.toString() +
                ", extention='" + extention + '\'' +
                '}';
    }
}
