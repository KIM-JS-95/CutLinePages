package com.myGallary.service;


import com.myGallary.Repository.ReviewRepository;
import com.myGallary.entity.Review;
import com.myGallary.entity.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private static final int BLOCK_PAGE_NUM_COUNT =5;
    private static final int PAGE_POST_COUNT =4;

    @Autowired
    private ReviewRepository reviewRepository;

    // 겔러리 모든 데이터 출력
    @Transactional
    public List<ReviewDTO> getBoardlist(Integer pageNum) {

        Page<Review> page = reviewRepository
                .findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT,
                        Sort.by(Sort.Direction.ASC,
                                "createDate")));


        List<Review> boards = page.getContent();

        List<ReviewDTO> boardDtoList = new ArrayList<>();

        for(Review board : boards){
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList  = new Integer[BLOCK_PAGE_NUM_COUNT];

        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3) ? 1: curPageNum-2;

        for(int val = curPageNum, i=0; val <= blockLastPageNum; val++, i++){
            pageList[i] = val;
        }

        return pageList;
    }

    @Transactional
    private Long getBoardCount() {
        return reviewRepository.count();
    }


    public Integer[] getSearchList(Integer curPageNum, String title){
        Integer[] pageList  = new Integer[BLOCK_PAGE_NUM_COUNT];
        Double postsTotalCount = Double.valueOf(this.getSearchCount(title));
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;
        curPageNum = (curPageNum<=3) ? 1: curPageNum-2;
        for(int val = curPageNum, i=0; val <= blockLastPageNum; val++, i++){
            pageList[i] = val;
        }
        return pageList;
    }

    @Transactional
    private Long getSearchCount(String title) {
        return reviewRepository.countByTitle(title);
    }


    // 겔러리 한가지 데이터 출력
    public Optional<Review> findIndex(Long index) {
        return reviewRepository.findById(index);
    }


    /* -------------------------------------------------------   CRUD API ----------------------------------------------------------*/

    // 수정
    public void update(Long id, Review gallary) {
        Review reviews = reviewRepository.findById(id).orElseThrow(()
               -> new IllegalArgumentException("정보가 없어요!"));

        reviews.setTitle(gallary.getTitle());
        reviews.setContent(gallary.getContent());
        reviews.setLink(gallary.getLink());

        reviewRepository.save(reviews);
    }

    // 삭제
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    // 조회
    public void view(Long id) {
        reviewRepository.findById(id);
    }

    // 검색
    public List<Review> search(String title) {
        return reviewRepository.findByTitleIgnoreCaseContaining(title);
    }


    // 게시글 저장
    public Review create(Review review){
        review.setUsername("admin");
        return reviewRepository.save(review);
    }


    private ReviewDTO convertEntityToDto(Review board) {
        return ReviewDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getUsername())
                .createDate(board.getCreateDate())
                .link(board.getLink())
                .build();
    }


}
