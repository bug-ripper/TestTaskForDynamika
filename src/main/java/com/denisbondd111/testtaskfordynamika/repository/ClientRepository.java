package com.denisbondd111.testtaskfordynamika.repository;

import com.denisbondd111.testtaskfordynamika.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
