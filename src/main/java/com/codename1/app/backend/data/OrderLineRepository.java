package com.codename1.app.backend.data;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLineEntity, Long> {
}