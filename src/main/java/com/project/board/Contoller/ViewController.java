package com.project.board.Contoller;

import com.project.board.DTO.posts.PostsResponseDto;
import com.project.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final PostsService postsService;
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }
    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable("id") Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
