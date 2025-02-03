package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Groups;
import com.example.project2.repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Groups> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public Groups saveGroup(Groups group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}