package com.jhinx.GamingSite.service;

import com.jhinx.GamingSite.model.Command;
import com.jhinx.GamingSite.model.Product;
import com.jhinx.GamingSite.repository.CommandRepository;
import com.jhinx.GamingSite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    public Command saveCommand(Command command) {
        if(!existsById(command.getId()))
            return commandRepository.save(command);

        return command;
    }

    public void deleteById(Long id) {
        commandRepository.deleteById(id);
    }

    public List<Command> getAllProducts() {
        return commandRepository.findAll();
    }

    public boolean existsById(Long id) {
        return commandRepository.existsById(id);
    }

    public Command getById(Long id){
        return commandRepository.getReferenceById(id);
    }
}
