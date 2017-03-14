package WebService;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Project {
    private SimpleIntegerProperty id;
    private SimpleStringProperty project_name;
    private SimpleStringProperty project_desc;
    private SimpleIntegerProperty status;
    private SimpleIntegerProperty price;

    public Project(int id, String project_name, String project_desc, int status, int price) {
        this.id = new SimpleIntegerProperty(id);
        this.project_name = new SimpleStringProperty(project_name);
        this.project_desc = new SimpleStringProperty(project_desc);
        this.status = new SimpleIntegerProperty(status);
        this.price = new SimpleIntegerProperty(price);
    }

    public Project(String project_name, String project_desc, int status) {
        this.project_name = new SimpleStringProperty(project_name);
        this.project_desc = new SimpleStringProperty(project_desc);
        this.status = new SimpleIntegerProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProject_name() {
        return project_name.get();
    }

    public SimpleStringProperty project_nameProperty() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name.set(project_name);
    }

    public String getProject_desc() {
        return project_desc.get();
    }

    public SimpleStringProperty project_descProperty() {
        return project_desc;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc.set(project_desc);
    }

    public int getStatus() {
        return status.get();
    }

    public SimpleIntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return " id - " + id.get() +
                ", name - " + project_name.get() +
                ", description - " + project_desc.get()+
                ", status - " + status.get() +
                ", price - " + price.get()
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (project_name != null ? !project_name.equals(project.project_name) : project.project_name != null)
            return false;
        if (project_desc != null ? !project_desc.equals(project.project_desc) : project.project_desc != null)
            return false;
        if (status != null ? !status.equals(project.status) : project.status != null) return false;
        return price != null ? price.equals(project.price) : project.price == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (project_name != null ? project_name.hashCode() : 0);
        result = 31 * result + (project_desc != null ? project_desc.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
