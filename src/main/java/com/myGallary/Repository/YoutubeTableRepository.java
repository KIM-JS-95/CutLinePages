package com.myGallary.Repository;


import com.myGallary.entity.YoutubeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeTableRepository extends JpaRepository<YoutubeTable, Long> {
   // YoutubeTable save(YoutubeTable youtubeTable);
}