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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity<Long> {

    private String subject;

    private LocalDate startDate;

    private LocalDate finishDate;

    private int capacity;

    public Course(String subject, LocalDate startDate, LocalDate finishDate, int capacity) {
        this.subject = subject;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.capacity = capacity;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Student> students = new ArrayList<>();

}
