package com.jhinx.GamingSite.repository;

import com.jhinx.GamingSite.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
