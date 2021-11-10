package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseEntity<Long> {

    private String content;

    private double score;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Tasq tasq;

}
