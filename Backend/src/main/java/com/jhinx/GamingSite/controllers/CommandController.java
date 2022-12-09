package com.jhinx.GamingSite.controllers;

import com.jhinx.GamingSite.exceptions.CommandNotFoundException;
import com.jhinx.GamingSite.exceptions.ProductNotFoundException;
import com.jhinx.GamingSite.model.Command;
import com.jhinx.GamingSite.model.CommandView;
import com.jhinx.GamingSite.model.Product;
import com.jhinx.GamingSite.payload.request.CommandRequest;
import com.jhinx.GamingSite.payload.request.ProductRequest;
import com.jhinx.GamingSite.payload.response.MessageResponse;
import com.jhinx.GamingSite.repository.CommandRepository;
import com.jhinx.GamingSite.service.CommandService;
import com.jhinx.GamingSite.service.ProductService;
import com.mysql.cj.xdevapi.JsonString;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comma")
public class CommandController {
    @Autowired
    CommandService commandService;
    @Autowired
    ProductService productService;
    @Autowired
    CommandRepository commandRepository;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CommandRequest commandRequest){
        Command comm = new Command(commandRequest.getAddress(),commandRequest.getUserId(),
        commandRequest.getProdId());

        if(commandService.existsById(comm.getId()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Order is already added!"));

        commandService.saveCommand(comm);
        productService.subQuantity(comm.getProdId());

        return ResponseEntity.ok(new MessageResponse("Order added successfully!"));
    }

    @GetMapping("/getAll")
    public List<Command> list() {
        return commandService.getAllProducts();
    }

    @GetMapping ("/getAllById")
    public List<CommandView> listById(@RequestParam Long id) {
        List <Command> aux = list();
        List<CommandView> auxView = new ArrayList<>();

        List <Command> commands = aux.stream().filter((item)-> item.getUserId()==id).collect(Collectors.toList());

        for(Command i : commands)
        {
            Product prod = productService.getById(i.getProdId());
            auxView.add(new CommandView(prod.getName(),prod.getPrice(),i.getAddress()));
        }

        return auxView;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Command comaReq) {
        if (commandService.existsById(comaReq.getId())) {
            Command old = commandService.getById(comaReq.getId());

            if(comaReq.getAddress()!=null && comaReq.getAddress()!="")
            old.setAddress(comaReq.getAddress());

            commandRepository.save(old);

            return ResponseEntity.ok(new MessageResponse("Product updated successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Product doesn`t exist"));
    }

    @DeleteMapping("/{id}")
    public String deleteCommand(@PathVariable Long id){
        if(!commandService.existsById(id)){
            throw new CommandNotFoundException(id);
        }
        commandService.deleteById(id);
        return  "Product with id "+id+" has been deleted successfully.";
    }
}
