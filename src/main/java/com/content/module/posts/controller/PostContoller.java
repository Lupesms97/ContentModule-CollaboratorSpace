package com.content.module.posts.controller;

import com.content.module.posts.model.PostModel;
import com.content.module.posts.services.impl.PostServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostContoller {

    private final PostServiceImpl postService;

    @PostMapping("post")
    public ResponseEntity<PostModel> post(@RequestBody PostModel postModel){
        PostModel postModelService = postService.post(postModel);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @PostMapping("AtualizationPost")
    public ResponseEntity<PostModel> updatePost(@RequestBody PostModel postModel){
        PostModel postModelService = postService.updatePost(postModel);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @PostMapping("deletePost")
    public ResponseEntity<String> deletePost(@RequestParam String postId){
        String response = postService.areasePost(postId);
        if (response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("saveMultiplePosts")
    public ResponseEntity<List<PostModel>> saveMultiplePosts(@RequestBody List<PostModel> postModel){
        List<PostModel> postModelService = postService.saveMulti(postModel);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @GetMapping("ById")
    public ResponseEntity<PostModel> postById(@RequestParam String postId){
        try {
            PostModel postModel = postService.postById(postId);
            return ResponseEntity.ok(postModel);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("AllPosts")
    public ResponseEntity<Iterable<PostModel>> AllPosts(){
        Iterable<PostModel> postModel = postService.AllPosts();
        if (postModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModel);
    }




}
