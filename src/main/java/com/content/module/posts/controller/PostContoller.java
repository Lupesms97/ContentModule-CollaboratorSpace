package com.content.module.posts.controller;

import com.content.module.posts.dtos.PostModelDtoWithToken;
import com.content.module.posts.dtos.PostModelDtoWithoutToken;
import com.content.module.posts.dtos.TokenValidation;
import com.content.module.posts.model.PostModel;
import com.content.module.validation.Validation;
import com.content.module.posts.services.impl.PostServiceImpl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
@Slf4j
public class PostContoller {

    private final PostServiceImpl postService;
    private final Validation validation;

    @PostMapping("post")
    public ResponseEntity<PostModel> post(@RequestBody PostModelDtoWithoutToken postDto){
        PostModel postModel = PostModelDtoWithoutToken.convertFromPostModelDtoWithouttoken(postDto);
        PostModel postModelService = postService.post(postModel);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @PostMapping("atualizationPost")
    public ResponseEntity<PostModel> updatePost(@RequestBody PostModelDtoWithToken postModeldto){
        PostModel postModel = PostModelDtoWithToken.convertFromPostDtoWithToken(postModeldto);
        PostModel postModelService = postService.updatePost(postModel);
        if (postModelService == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postModelService);
    }

    @DeleteMapping ("postDeletion")
    public ResponseEntity<String> deletePost(@RequestParam String postId){
        String response = postService.areasePost(postId);
        if (response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("saveMultiplePosts")
    public ResponseEntity<List<PostModel>> saveMultiplePosts(@RequestBody List<PostModelDtoWithoutToken> postModelDtoList){
        List<PostModel> postModelList = new ArrayList<>();

        for(PostModelDtoWithoutToken postModelDtoWithoutToken : postModelDtoList){
            postModelList.add(PostModelDtoWithoutToken.convertFromPostModelDtoWithouttoken(postModelDtoWithoutToken));
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

    @RequestMapping(method = RequestMethod.POST, value = "/func")
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
                if(firstPost.getTitle().equals(secondPost.getTitle())){
                    parValues.put(firstPost.getId(), secondPost.getId());
                    postService.areasePost(secondPost.getId());

                }
            }
        }
        return ResponseEntity.ok(parValues);
    }
    @GetMapping("allPostsValidation")
    public ResponseEntity<List<PostModel>> AllPostsValidation(@RequestHeader("tokenKey") String tokenKey){
        List<PostModel> postModel;

        try{
            TokenValidation valid = validation.validateToken(tokenKey);
            log.info(valid.message());
            log.info(valid.toString());
            if (valid.message().equals("valid")){
                postModel = postService.AllPosts();
                if (postModel == null){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(postModel, HttpStatus.OK);
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }





}
