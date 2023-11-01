package com.content.module.posts.services;


import com.content.module.posts.model.PostModel;

import java.util.List;

public interface PostService {

     post(PostModel postModel);

    public PostModel updatePost(PostModel postModel) {
        return null;
    }

    public String areasePost(String postId) {
        return null;
    }

    public PostModel postById(String postId) {
        return null;
    }


    public List<PostModel> AllPosts(){
        return null;
    }
}
