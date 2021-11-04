package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Teacher extends User{

    public Teacher(String firstname, String lastname, String username, String password, UserType userType, Boolean isActive) {
        super(firstname, lastname, username, password, userType, isActive);
    }

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();
}
