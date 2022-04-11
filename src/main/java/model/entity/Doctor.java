package model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.entity.base.User;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Doctor extends User {
    private String specialty;

    @ManyToOne
    private Clinic clinic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Doctor doctor = (Doctor) o;
        return getId() != null && Objects.equals(getId(), doctor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id ='" + super.getId() + '\'' +
                "name ='" + super.getName() + '\'' +
                "username ='" + super.getUsername() + '\'' +
                "password ='" + super.getPassword() + '\'' +
                "specialty='" + specialty + '\'' +
                ", clinic=" + clinic +
                '}';
    }
}
