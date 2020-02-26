package com.example.demoh2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<DemoProperty, Long> {
}
