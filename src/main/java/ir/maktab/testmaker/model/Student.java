package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Student extends User{

    public Student(String firstname, String lastname, String username, String password, UserType userType, Boolean isActive) {
        super(firstname, lastname, username, password, userType, isActive);
    }

    @ManyToMany(mappedBy = "students")
    List<Course> courses = new ArrayList<>();
}
