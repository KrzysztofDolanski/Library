package com.epam.library.furnitures.lying.sofa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SofaRepository extends JpaRepository<Sofa, Long> {
}
