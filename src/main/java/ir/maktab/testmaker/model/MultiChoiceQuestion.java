package ir.maktab.testmaker.model;

import ir.maktab.testmaker.model.enumeration.QType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MultiChoiceQuestion extends Question {

    @OneToMany(mappedBy = "multiChoiceQuestion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Selection> selections = new ArrayList<>();

    public MultiChoiceQuestion(String title, String content, QType qType, Course course, Teacher teacher) {
        super(title, content, qType, course, teacher);
    }
}
