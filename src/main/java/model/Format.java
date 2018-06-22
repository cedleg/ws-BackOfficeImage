package model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table
public class Format {

    @Id
    @NotNull
    private String name;
    @Enumerated
    private MimeType type;
    @NotNull
    private boolean isDefaultType = true;
    @ElementCollection
    private Map<String, String> properties;
    @ManyToOne
    private Project project;

    public Format() {
    }

    /**
     *
     * @param name
     * @param type
     * @param isDefaultType
     * @param properties
     * @param project
     */
    public Format(String name, MimeType type, boolean isDefaultType, Map<String, String> properties, Project project) {
        this.name = name;
        this.type = type;
        this.isDefaultType = isDefaultType;
        this.properties = properties;
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MimeType getType() {
        return type;
    }

    public void setType(MimeType type) {
        this.type = type;
    }

    public boolean isDefaultType() {
        return isDefaultType;
    }

    public void setDefaultType(boolean defaultType) {
        isDefaultType = defaultType;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Format format = (Format) o;

        return name.equals(format.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Format{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", isDefaultType=" + isDefaultType +
                ", properties=" + properties +
                ", project=" + project.toString() +
                '}';
    }
}
