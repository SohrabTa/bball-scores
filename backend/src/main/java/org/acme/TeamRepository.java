package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface TeamRepository extends PanacheRepository<Team> {
    
}
