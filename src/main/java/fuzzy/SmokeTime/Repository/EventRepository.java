package fuzzy.SmokeTime.Repository;

import fuzzy.SmokeTime.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

//    Event findEventByName(String name);
    Event findEventByName(String name);
    Event getEventByName(String name);

    Event getEventById(Long id);
}
