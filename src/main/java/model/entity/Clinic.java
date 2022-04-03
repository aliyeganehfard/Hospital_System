package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.entity.base.BaseEntity;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Clinic extends BaseEntity<Integer> {
    private String name;

    @Override
    public String toString() {
        return "Clinic{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Clinic clinic = (Clinic) o;
        return getId() != null && Objects.equals(getId(), clinic.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
