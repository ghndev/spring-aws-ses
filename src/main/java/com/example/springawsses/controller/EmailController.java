package com.example.springawsses.controller;

import com.example.springawsses.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public String home(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "home";
    }

    @PostMapping
    public String sendEmail(@RequestParam(value = "email") String email, RedirectAttributes redirectAttributes) throws Exception {
        ArrayList<String> receivers = new ArrayList<>();
        receivers.add(email);

        String subject = "AWS SES test";
        String content = "Hello world!";

        emailService.send(subject, content, receivers);
        redirectAttributes.addAttribute("message", "success");
        return "redirect:/";
    }
}
