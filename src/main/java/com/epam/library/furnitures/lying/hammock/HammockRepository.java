package com.epam.library.furnitures.lying.hammock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HammockRepository extends JpaRepository<Hammock, Long> {
}
