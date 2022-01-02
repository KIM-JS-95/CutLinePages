package com.myGallary.Repository;


import com.myGallary.entity.GallaryReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GallaryReplyRepository extends JpaRepository<GallaryReply, Long> {


}
