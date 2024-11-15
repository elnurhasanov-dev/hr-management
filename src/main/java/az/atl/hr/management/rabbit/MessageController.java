package az.atl.hr.management.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageSender messageSender;

    @PostMapping("/send")
    public String sendMsg(@RequestParam String message) {
        messageSender.sendMessage("myQueue", message);
        return "Message sent!";
    }
}