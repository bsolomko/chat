package co.norse.chat.controller;

import co.norse.chat.model.Message;
import co.norse.chat.model.User;
import co.norse.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ChatController {
    @Autowired
    MessageService messageService;

    @PostMapping("/user/registration")
    public String registration(@RequestBody User user){
        return "redirect:home";
    }

    @GetMapping("/user/{id}")
    public String home(@PathVariable("id") Long id ){
     return "home";
    }

    @PostMapping("/user/{id}/message/to/{recipientID}")
    public String sendMessage(@RequestBody Message message ,@PathVariable("id") Long id,@PathVariable("recipientID") Long recipientID){
        return "redirect:home";
    }

    @PostMapping("/user/{id}/message/delete/{recipientID}")
    public  String deleteAllMessagesUserInChat(@PathVariable("id") Long id,@PathVariable Long recipientID){
        return "redirect:home";
    }
    @GetMapping("/user/{id}/search/{userName}")
    public String searchByUserName(@PathVariable("userName") String userName){
        return "redirect:home";
    }


}
