package com.content.module.posts.services;


import com.content.module.posts.model.PostModel;

import java.util.List;

public interface PostService {

     PostModel post(PostModel postModel);

     PostModel updatePost(PostModel postModel);

     String areasePost(String postId);

     PostModel postById(String postId);


     List<PostModel> AllPosts();
}
