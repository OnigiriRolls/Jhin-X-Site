package com.jhinx.GamingSite.exceptions;

public class CommandNotFoundException  extends RuntimeException{
    public CommandNotFoundException(Long id){
        super("Could not found the command with id "+ id);
    }
}
