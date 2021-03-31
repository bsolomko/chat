package co.norse.chat.controller;

import co.norse.chat.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    List<Message> allMessage = new ArrayList<>();

@GetMapping("/chat")
    public String getHomePage(Model model,@ModelAttribute("newMessage") Message message){
    model.addAttribute("messages",allMessage);
    return "home";
}
@PostMapping("/addMessage")
    public String addMessage(@ModelAttribute("newMessage") Message message){
    Message _message = new Message();
    _message.setMessage(message.getMessage());
    _message.setAuthor(message.getAuthor());
    allMessage.add(message);
    return "redirect:/chat";
}

}
