package fuzzy.SmokeTime.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "time")
    private Long time;
    @Column(name = "repeatable")
    private Boolean repeatable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
