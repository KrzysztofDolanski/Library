package com.epam.library.furnitures.lying.futon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FutonRepository  extends JpaRepository<Futon, Long> {
}
