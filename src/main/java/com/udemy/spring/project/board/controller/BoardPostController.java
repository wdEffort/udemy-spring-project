package com.udemy.spring.project.board.controller;

import com.udemy.spring.project.board.service.BoardPostService;
import com.udemy.spring.project.board.validator.BoardPostValidator;
import com.udemy.spring.project.board.vo.BoardPostVO;
import com.udemy.spring.project.utils.PagingMaker;
import com.udemy.spring.project.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardPostController {

    @Autowired
    private BoardPostService boardPostService;

    /**
     * 글 작성 화면
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getBoardPostWritePage(@ModelAttribute("vo") BoardPostVO vo) {
        return "board/write";
    }

    /**
     * 글 등록
     *
     * @param vo
     * @param result
     * @param redirectAttributes Redirect 할 때 한번만 사용할 수 있는 데이터를 같이 전달할 수 있게 해주는 객체
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@ModelAttribute("vo") BoardPostVO vo,
                        BindingResult result,
                        RedirectAttributes redirectAttributes) throws Exception {
        BoardPostValidator boardPostValidator = new BoardPostValidator();
        boardPostValidator.validate(vo, result);

        if (result.hasErrors()) {
            return "board/write";
        }

        boardPostService.insert(vo);

        redirectAttributes.addFlashAttribute("message", "글 등록이 완료되었습니다.");

        return "redirect:/board/list";
    }

    /**
     * 글 보기 화면
     *
     * @param postId
     * @param searchCriteria SearchCriteria Model에 담아서 전달해서 현재 페이지 번호와 한 페이지당 데이터 출력 개수, 검색 파라미터를 유지한다.
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String getBoardPostViewPage(@PathVariable("id") Integer postId,
                                       @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                                       Model model) throws Exception {
        BoardPostVO boardPost = boardPostService.read(postId);

        if (boardPost == null) {
            throw new NullPointerException("존재하지 않는 게시글입니다.");
        }

        model.addAttribute("boardPost", boardPost);

        return "board/view";
    }

    /**
     * 글 수정 화면
     *
     * @param postId
     * @param model
     * @param searchCriteria
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String getBoardPostModifyPage(@PathVariable("id") Integer postId,
                                         @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                                         Model model) throws Exception {
        BoardPostVO boardPost = boardPostService.read(postId);

        if (boardPost == null) {
            throw new NullPointerException("존재하지 않는 게시글입니다.");
        }

        model.addAttribute("vo", boardPost);

        return "board/modify";
    }

    /**
     * 글 수정
     *
     * @param vo
     * @param result
     * @param searchCriteria     PageCriteria를 Model에 담아서 전달해서 현재 페이지 번호와 한 페이지당 데이터 출력 개수, 검색 파라미터를 유지한다.
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("vo") BoardPostVO vo,
                         BindingResult result,
                         @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                         RedirectAttributes redirectAttributes) throws Exception {
        BoardPostValidator boardPostValidator = new BoardPostValidator();
        boardPostValidator.validate(vo, result);

        if (result.hasErrors()) {
            return "board/modify";
        }

        boardPostService.update(vo);

        redirectAttributes.addAttribute("page", searchCriteria.getPage()); // URL Query String으로 붙게 된다.
        redirectAttributes.addAttribute("numPerPage", searchCriteria.getNumPerPage());
        redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
        redirectAttributes.addAttribute("searchKeyword", searchCriteria.getSearchKeyword());
        redirectAttributes.addFlashAttribute("message", "글 수정이 완료되었습니다.");

        return "redirect:/board/view/" + vo.getPostId();
    }

    /**
     * 글 삭제
     *
     * @param vo
     * @param searchCriteria     PageCriteria를 Model에 담아서 전달해서 현재 페이지 번호와 한 페이지당 데이터 출력 개수, 검색 파라미터를 유지한다.
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(BoardPostVO vo,
                         @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                         RedirectAttributes redirectAttributes) throws Exception {
        boardPostService.delete(vo.getPostId());

        redirectAttributes.addAttribute("page", searchCriteria.getPage()); // URL Query String으로 붙게 된다.
        redirectAttributes.addAttribute("numPerPage", searchCriteria.getNumPerPage());
        redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
        redirectAttributes.addAttribute("searchKeyword", searchCriteria.getSearchKeyword());
        redirectAttributes.addFlashAttribute("message", "글 삭제가 완료되었습니다."); // 리다이렉트 되는 페이지에서 딱 한번 사용할 데이터를 설정한다.

        return "redirect:/board/list";
    }

    /**
     * 글 목록 화면
     *
     * @param searchCriteria 검색 및 페이징 처리를 위한 객체(스프링 MVC에서는 메소드 파라미터 타입에 해당하는 객체를 기본 생성자를 이용해서 생성한 후 인자로 전달한다.)
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getBoardPostList(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception {
        List<BoardPostVO> boardPostList = boardPostService.listCriteria(searchCriteria);

        PagingMaker pagingMaker = new PagingMaker();
        pagingMaker.setPageCriteria(searchCriteria);
        pagingMaker.setTotalCount(boardPostService.count(searchCriteria));

        model.addAttribute("boardPostList", boardPostList);
        model.addAttribute("pagingMaker", pagingMaker);

        return "board/list";
    }
}
