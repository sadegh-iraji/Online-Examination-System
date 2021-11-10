package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Test extends BaseEntity<Long> {

    private String subject;

    private String description;

    private int time;

    @ManyToMany
    private Set<Student> students = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Course course;

    public Test(String subject, String description, int time, Course course) {
        this.subject = subject;
        this.description = description;
        this.time = time;
        this.course = course;
    }

    public Test(String subject, String description, int time) {
        this.subject = subject;
        this.description = description;
        this.time = time;
    }
}
