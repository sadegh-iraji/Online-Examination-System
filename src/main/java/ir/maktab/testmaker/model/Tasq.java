package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*This Class is For Test, Answer, Score, Question and Map Relations between them*/

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tasq extends BaseEntity<Long> {

    @Column(nullable = false)
    private double score;

    @OneToMany(mappedBy = "tasq")
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Question question;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Test test;

    public Tasq(double score, Question question, Test test) {
        this.score = score;
        this.question = question;
        this.test = test;
    }
}
