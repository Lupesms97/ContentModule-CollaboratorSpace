package com.content.module.posts.controller;

import com.content.module.posts.model.PostModel;
import com.content.module.posts.services.impl.PostServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("atualizationPost")
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
    public ResponseEntity<List<PostModel>> saveMultiplePosts(@RequestBody List<PostModel> postModelList){
        for(PostModel postModel : postModelList){
            postModel.setDate(PostModel.convertDate(postModel.getDate().toString()));
        }
        List<PostModel> postModelService = postService.saveMulti(postModelList);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @GetMapping("idKey")
    public ResponseEntity<PostModel> postById(@RequestParam String postId){
        try {
            PostModel postModel = postService.postById(postId);
            return ResponseEntity.ok(postModel);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("allPosts")
    public ResponseEntity<List<PostModel>> AllPosts(){
        List<PostModel> postModel = postService.AllPosts();
        if (postModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModel);
    }

    @PostMapping("optFunc")
    public ResponseEntity<?> optFunc(@RequestParam String func, @RequestParam String postId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "deletion, byId");
        try {
            return switch (func) {
                case "deletion" -> deletePost(postId);
                case "byId" -> postById(postId);
                default -> ResponseEntity.badRequest().headers(new HttpHeaders(headers)).build();
            };
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("findForEqualsId")
    public ResponseEntity<HashMap<String, String>> findForEqualsId(){

        HashMap<String, String> parValues = new HashMap<>();
        List<PostModel> postModel = postService.AllPosts();
        for(int j = 0; j < postModel.size(); j++){
            PostModel firstPost = postModel.get(j);

            for(int i = j+1; i < postModel.size(); i++){
                PostModel secondPost = postModel.get(i);
                if(firstPost.getId().equals(secondPost.getId())){
                    parValues.put(firstPost.getId(), secondPost.getId());
                }
            }
        }
        return ResponseEntity.ok(parValues);
    }




}
