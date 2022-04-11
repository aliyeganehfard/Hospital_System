package model.entity;

import lombok.*;
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
@ToString
@Entity
public class Prescription extends BaseEntity<Integer> {
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Prescription that = (Prescription) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id ='" + super.getId() + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
