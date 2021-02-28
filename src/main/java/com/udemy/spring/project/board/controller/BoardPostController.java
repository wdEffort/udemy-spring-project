package com.udemy.spring.project.board.controller;

import com.udemy.spring.project.board.service.BoardPostService;
import com.udemy.spring.project.board.validator.BoardPostValidator;
import com.udemy.spring.project.board.vo.BoardPostVO;
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
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String getBoardPostViewPage(@PathVariable("id") Integer postId, Model model) throws Exception {
        BoardPostVO boardPost = boardPostService.read(postId);

        model.addAttribute("boardPost", boardPost);

        return "board/view";
    }

    /**
     * 글 수정 화면
     *
     * @param postId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String getBoardPostModifyPage(@PathVariable("id") Integer postId, Model model) throws Exception {
        BoardPostVO boardPost = boardPostService.read(postId);

        model.addAttribute("vo", boardPost);

        return "board/modify";
    }

    /**
     * 글 수정
     *
     * @param vo
     * @param result
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("vo") BoardPostVO vo, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
        BoardPostValidator boardPostValidator = new BoardPostValidator();
        boardPostValidator.validate(vo, result);

        if (result.hasErrors()) {
            return "board/modify";
        }

        boardPostService.update(vo);

        redirectAttributes.addFlashAttribute("message", "글 수정이 완료되었습니다.");

        return "redirect:/board/view/" + vo.getPostId();
    }

    /**
     * 글 삭제
     *
     * @param vo
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(BoardPostVO vo, RedirectAttributes redirectAttributes) throws Exception {
        boardPostService.delete(vo.getPostId());

        redirectAttributes.addFlashAttribute("message", "글 삭제가 완료되었습니다.");

        return "redirect:/board/list";
    }

    /**
     * 글 목록 화면
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getBoardPostList(Model model) throws Exception {
        List<BoardPostVO> boardPostList = boardPostService.list();

        model.addAttribute("boardPostList", boardPostList);

        return "board/list";
    }
}
