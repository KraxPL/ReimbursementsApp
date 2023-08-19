package pl.krax.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean enable;
}
