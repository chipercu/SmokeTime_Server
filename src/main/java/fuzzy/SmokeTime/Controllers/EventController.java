package fuzzy.SmokeTime.Controllers;


import fuzzy.SmokeTime.Entity.Event;
import fuzzy.SmokeTime.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }


    @GetMapping("/getEventByName")
    public String getEventByName(@RequestParam(defaultValue = "null") String name){
        if (name.equals("null")){
            return "Не задан параметр name , http://localhost:8180/getEventByName?name=Имя ивента";
        }
        return eventService.getEventByName(name);
    }

    @DeleteMapping("/deleteEventById/{id}")
    public String deleteEventByID(@PathVariable(name = "id") Long id){
        return eventService.deleteEventByID(id);
    }

    @GetMapping("/addEvent")
    public String addEvent(@RequestParam(required = false) String name, @RequestParam(required = false) Long time, @RequestParam(defaultValue = "false") Boolean isRepeatable){
       return eventService.addEvent(name, time, isRepeatable);
    }

    @GetMapping("/generateRandomEvent")
    public String generateRandomEvents(@RequestParam(required = false, defaultValue = "3") Integer count){
        return eventService.generateRandomEvents(count);
    }



}
