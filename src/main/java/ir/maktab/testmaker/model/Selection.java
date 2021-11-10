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
public class Selection extends BaseEntity<Long> {

    private String content;

    boolean isAnswer;

    public Selection(String content, boolean isAnswer) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    @ManyToOne
    private MultiChoiceQuestion multiChoiceQuestion;
}
