package com.myGallary.service;

import com.myGallary.Repository.ReviewRepository;
import com.myGallary.entity.Review;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/*

실제 DB 환경에서 테스트 하길 원한다면
@AutoConfigureTestDatabase 를 추가해 주세요
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@WithMockUser(username = "admin", password = "wotjd2487", roles = {"ROLE_ADMIN"})
class ReviewServiceTest {

    private Logger log = LoggerFactory.getLogger(ReviewServiceTest.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void getBoardlist() {
       List<Review> reviews = reviewRepository.findAll();

       assertThat(reviews.get(0).getTitle(),is("[#1] Last Light 후기"));

    }



}