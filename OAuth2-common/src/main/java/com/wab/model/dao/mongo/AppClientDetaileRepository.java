package com.wab.model.dao.mongo;

import com.wab.model.entity.AppBaseClientDetaile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppClientDetaileRepository extends MongoRepository<AppBaseClientDetaile, String> {
}
