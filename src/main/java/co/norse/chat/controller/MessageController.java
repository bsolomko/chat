package co.norse.chat.controller;

import co.norse.chat.User;
import co.norse.chat.model.Message;
import co.norse.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;


@GetMapping("/chat")
    public String getHomePage(Model model,@ModelAttribute("newMessage") Message message){
    model.addAttribute("messages",messageRepository.getAllMessages());
    return "home";
}

@PostMapping("/addMessage")
    public String addMessage(@ModelAttribute("newMessage") Message message){
    Message _message = new Message();
    _message.setMessage(message.getMessage());
    _message.setAuthor(message.getAuthor());
    _message.setCreateDataTime(message.getCreateDataTime());
    _message.setId(message.getId());
    messageRepository.addMessage(message);
    return "redirect:/chat";
}
@PostMapping("/deleteAllMessage")
    public String deleteChat(){
            messageRepository.deleteAllMessages();
            return "redirect:/chat";
}

@GetMapping("/registration")
    public String registration(@ModelAttribute("newUser")User user){
    return "registration";
}
}
