package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import ir.maktab.testmaker.model.enumeration.QType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity<Long> {

    @Column(length = 20)
    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private QType qType;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "question")
    private List<Tasq> tasqList = new ArrayList<>();

    public Question(String title, String content, QType qType, Course course, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.qType = qType;
        this.course = course;
        this.teacher = teacher;
    }
}
