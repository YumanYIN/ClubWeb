package beans.backingbeans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    @Column(nullable = false, unique = true, length = 100)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String role; // member or visiter

    public User(){
        this.firstName = "firstname";
        this.lastName = "lastName";
        this.address = "address";
        this.role = "visiter";
    }

    public User(String userName, String password, String email, String firstName, String lastName, String address, String role){
        this.setRole(role);
        this.setPassword(password);
        this.setUserName(userName);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setAddress(address);
        //this.setUserId(userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
