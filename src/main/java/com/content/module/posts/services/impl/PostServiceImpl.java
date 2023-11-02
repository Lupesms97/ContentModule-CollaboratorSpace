package com.content.module.posts.services.impl;

import com.content.module.posts.exepctionsHandles.NotCompleted;
import com.content.module.posts.exepctionsHandles.PostNotFound;
import com.content.module.posts.model.PostModel;
import com.content.module.posts.respositories.PostRepository;
import com.content.module.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostModel post(PostModel postModel) {
        try {
            return postRepository.save(postModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PostModel updatePost(PostModel postModel) {
        PostModel newPost;
        Optional<PostModel> postModelOptional = postRepository.findById(postModel.getId());
        try {
            if (postModelOptional.isPresent()){
                newPost = postModelOptional.get();

                newPost.setContent(postModel.getContent());
                newPost.setTitle(postModel.getTitle());
                newPost.setShortContent(postModel.getShortContent());
                newPost.setDate(postModel.getDate());
                newPost.setImageURL(postModel.getImageURL());

                postRepository.save(newPost);

                return newPost;

            }else {
                log.atError().log("Post not found");
                throw new PostNotFound("Post not found");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String areasePost(String postId) {
        try {
            postRepository.deleteById(UUID.fromString(postId));
            return "Post deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PostModel postById(String postId) {
        Optional<PostModel> postModelOptional = postRepository.findById(UUID.fromString(postId));
        if (postModelOptional.isPresent()){
            return postModelOptional.get();
        }else {
            log.atError().log("Post not found");
            throw new PostNotFound("Post not found");
        }
    }

    @Override
    public List<PostModel> AllPosts() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<PostModel> saveMulti(List<PostModel> postModelList) {
        try {
            for(PostModel postModel: postModelList){
                PostModel.convertDate(postModel.getDate().toString());
            }
            postRepository.saveAll(postModelList);
            return postModelList;

        } catch (Exception e) {
            throw new NotCompleted("Not completed");
        }
    }
}
