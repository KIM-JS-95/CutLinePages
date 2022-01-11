package com.myGallary.Repository;


import com.myGallary.entity.Gallary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GallaryRepository extends JpaRepository<Gallary, Long> {

    @Query("SELECT p FROM Gallary p ORDER BY p.id DESC")
    List<Gallary> findAllcontents();

    Optional<Gallary> findById(Long id);

    List<Gallary> findByTitle(String title);

    Long countByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Gallary p SET p.count = p.count+1 WHERE id = :id")
    int updateCount(Long id);

}
