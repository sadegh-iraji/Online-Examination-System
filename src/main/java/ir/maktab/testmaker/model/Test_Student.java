package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Test_Student extends BaseEntity<Long> {

    private LocalDateTime finishTime;

    private double totalScore;

    @ManyToOne(cascade = CascadeType.MERGE)
    Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    Test test;

    @OneToMany(mappedBy = "test_student")
    List<Answer> answers = new ArrayList<>();

    public Test_Student(LocalDateTime finishTime, double totalScore, Student student, Test test) {
        this.finishTime = finishTime;
        this.totalScore = totalScore;
        this.student = student;
        this.test = test;
    }
}
