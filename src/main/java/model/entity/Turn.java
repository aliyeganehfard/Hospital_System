package model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.entity.base.BaseEntity;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString
@Entity
public class Turn extends BaseEntity<Integer> {
    private Date date;
    private Time time;
    @ManyToOne
    private Clinic clinic;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Prescription prescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Turn turn = (Turn) o;
        return getId() != null && Objects.equals(getId(), turn.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Turn{" +
                "id ='" + super.getId() + '\'' +
                "date=" + date +
                ", time=" + time +
                ", clinic=" + clinic +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", prescription=" + prescription +
                '}';
    }
}
