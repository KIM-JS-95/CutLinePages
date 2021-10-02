package com.myGallary.service;


import com.myGallary.Repository.GallaryRepository;
import com.myGallary.entity.Gallary;
import com.myGallary.entity.GallaryDto;
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
public class GallaryService {

    private static final int BLOCK_PAGE_NUM_COUNT =5;
    private static final int PAGE_POST_COUNT =4;

    @Autowired
    private GallaryRepository gallaryRepository;

    // 겔러리 모든 데이터 출력
    @Transactional
    public List<GallaryDto> getBoardlist(Integer pageNum) {

        Page<Gallary> page = gallaryRepository
                .findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT,
                        Sort.by(Sort.Direction.ASC,
                                "createDate")));


        List<Gallary> boards = page.getContent();

        List<GallaryDto> boardDtoList = new ArrayList<>();

        for(Gallary board : boards){
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
        return gallaryRepository.count();
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
        return gallaryRepository.countByTitle(title);
    }




    private GallaryDto convertEntityToDto(Gallary board) {
        return GallaryDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .link(board.getLink())
                .username(board.getUsername())
                .createDate(board.getCreateDate())
                .build();
    }

    // 겔러리 한가지 데이터 출력
    public Optional<Gallary> findIndex(Long index) {
        return gallaryRepository.findById(index);
    }

    // 게시글 저장
    public Gallary create(Gallary gallary) {
       return gallaryRepository.save(gallary);
    }

    // 수정
    public void update(Long id, GallaryDto gallary) {
       Gallary gallarys = gallaryRepository.findById(id).orElseThrow(()
               -> new IllegalArgumentException("정보가 없어요!"));

       gallarys.setTitle(gallary.getTitle());
       gallarys.setContent(gallary.getContent());
       gallarys.setLink(gallary.getLink());

       gallaryRepository.save(gallarys);
    }

    // 삭제
    public void delete(Long id) {
        gallaryRepository.deleteById(id);
    }

    // 조회
    public void view(Long id) {
        gallaryRepository.findById(id);
    }

    // 검색
    public List<Gallary> search(String title) {
        return gallaryRepository.findByTitle(title);
    }


}
