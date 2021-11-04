package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Manager extends User{
    public Manager(String firstname, String lastname, String username, String password, UserType userType, Boolean isActive) {
        super(firstname, lastname, username, password, userType, isActive);
    }
}
