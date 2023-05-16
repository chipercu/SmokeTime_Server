package fuzzy.SmokeTime.Service;

import fuzzy.SmokeTime.Entity.Event;
import fuzzy.SmokeTime.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public String getEventByName(String name){
        final Event eventByName = repository.getEventByName(name);
        if (eventByName != null){
            return eventByName.toString();
        }
        return "Событье с именем \"" + name + "\" не существует";
    }

    public String addEvent(String name, Long time, Boolean isRepeatable) {
        final Event event = new Event();

        if (name == null){
            return "Не указано имя ивента";
        }
        if (time == null){
            return "Не указано время ивента";
        }
        if (isRepeatable == null){
            event.setRepeatable(false);
        }
        event.setName(name);
        event.setTime(time);
        event.setRepeatable(isRepeatable);
        repository.saveAndFlush(event);
        String s = event.getRepeatable() ? "обновится": "закончится";
        return "Ивент " + name + " был успешно добавлен! Ивент " + s + "  через " + (time /1000) + " сек.";
    }

    public String deleteEventByID(Long id) {
        final Event eventById = repository.getEventById(id);
        if (eventById == null){
            return "Ивент c ID:" + id + " не был найден";
        }
        repository.delete(eventById);
        return "Ивент " + eventById.getName() + " был успешно удален";
    }

    public List<Event> getAllEvents() {
        final List<Event> all = repository.findAll();
        return all;
    }

    public String generateRandomEvents(Integer count) {
        try {
            Random rnd = new Random();
            final List<Event> eventList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                final Event event = new Event();
                event.setName("Test" + rnd.nextInt(10, 99));
                event.setTime(rnd.nextLong(10000, 99000));
                event.setRepeatable(rnd.nextBoolean());
                eventList.add(event);
            }
            repository.saveAllAndFlush(eventList);
            return "В базе были успешно сгенерированы " + eventList.size() + "ивентов";
        }catch (Exception e){
            return "Не удалось сгенерировать ивенты";
        }
    }
}
