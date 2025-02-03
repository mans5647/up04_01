package com.example.project2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project2.model.Groups;
import com.example.project2.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String getAllGroups(Model model) {
        List<Groups> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "group/list";
    }

    @GetMapping("/create")
    public String createGroupForm(Model model) {
        model.addAttribute("group", new Groups());
        return "group/create";
    }

    @PostMapping
    public String createGroup(@Valid @ModelAttribute("group") Groups group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "group/create";
        }
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String editGroupForm(@PathVariable Long id, Model model) {
        Optional<Groups> group = groupService.getGroupById(id);
        if (group.isPresent()) {
            model.addAttribute("group", group.get());
            return "group/edit";
        }
        return "redirect:/groups";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable Long id, @Valid @ModelAttribute("group") Groups group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "group/edit";
        }
        group.setId(id);
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }
}
