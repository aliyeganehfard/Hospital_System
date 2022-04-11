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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Secretary extends User {
    @ManyToOne
    private Clinic clinic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Secretary secretary = (Secretary) o;
        return getId() != null && Objects.equals(getId(), secretary.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "id ='" + super.getId() + '\'' +
                "name ='" + super.getName() + '\'' +
                "username ='" + super.getUsername() + '\'' +
                "password ='" + super.getPassword() + '\'' +
                "clinic=" + clinic +
                '}';
    }
}
