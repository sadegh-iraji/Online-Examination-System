package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student extends User {

    public Student(String firstname, String lastname, String username, String password, UserType userType, Boolean isActive) {
        super(firstname, lastname, username, password, userType, isActive);
    }

    @ManyToMany(mappedBy = "students")
    List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    List<Test_Student> test_studentList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Answer> answers = new ArrayList<>();
}
