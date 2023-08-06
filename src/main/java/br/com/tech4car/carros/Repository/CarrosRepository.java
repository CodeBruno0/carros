package br.com.tech4car.carros.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4car.carros.Model.Carros;

public interface CarrosRepository extends MongoRepository <Carros, String>{

    
    
}
