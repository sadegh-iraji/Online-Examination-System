package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.QType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DescriptiveQuestion extends Question {

    public DescriptiveQuestion(String title, String content, QType qType, Course course, Teacher teacher) {
        super(title, content, qType, course, teacher);
    }
}
