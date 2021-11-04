package ir.maktab.testmaker.model;

import ir.maktab.testmaker.base.model.BaseEntity;
import ir.maktab.testmaker.model.enumeration.UserType;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<Long> {


    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    Boolean isActive;

}
