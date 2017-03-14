package WebService;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleIntegerProperty id;
    private SimpleStringProperty user_name;
    private SimpleStringProperty user_login;
    private SimpleIntegerProperty procentage;
    private SimpleBooleanProperty busy;

    public User(int id, String user_name, String user_login, int procentage, boolean busy) {
        this.id = new SimpleIntegerProperty(id);
        this.user_name = new SimpleStringProperty(user_name);
        this.user_login = new SimpleStringProperty(user_login);
        this.procentage = new SimpleIntegerProperty(procentage);
        this.busy = new SimpleBooleanProperty(busy);
    }

    public User(int id, String user_name, String user_login, int procentage) {
        this.id = new SimpleIntegerProperty(id);
        this.user_name = new SimpleStringProperty(user_name);
        this.user_login = new SimpleStringProperty(user_login);
        this.procentage = new SimpleIntegerProperty(procentage);
    }

    public boolean getBusy() {
        return busy.get();
    }

    public SimpleBooleanProperty busyProperty() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy.set(busy);
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

    public String getUser_name() {
        return user_name.get();
    }

    public SimpleStringProperty user_nameProperty() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name.set(user_name);
    }

    public String getUser_login() {
        return user_login.get();
    }

    public SimpleStringProperty user_loginProperty() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login.set(user_login);
    }

    public int getProcentage() {
        return procentage.get();
    }

    public SimpleIntegerProperty procentageProperty() {
        return procentage;
    }

    public void setProcentage(int procentage) {
        this.procentage.set(procentage);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name=" + user_name +
                ", user_login=" + user_login +
                ", procentage=" + procentage +
                ", busy=" + busy +
                '}';
    }
}
