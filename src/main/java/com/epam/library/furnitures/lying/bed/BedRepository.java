package com.epam.library.furnitures.lying.bed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BedRepository extends JpaRepository<Bed, Long> {
}
