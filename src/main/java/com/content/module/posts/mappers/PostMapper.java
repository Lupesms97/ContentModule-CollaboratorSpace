package com.content.module.posts.mappers;

import com.content.module.posts.dtos.PostModelDtoWithToken;
import com.content.module.posts.dtos.PostModelDtoWithoutToken;
import com.content.module.posts.model.PostModel;

public class PostMapper {

    public static PostModel transformWithToken(PostModelDtoWithToken postModelDtoWithToken){
        PostModel postModel = PostModel.getPostModel();
        postModel.setId(postModelDtoWithToken.id());
        postModel.setTitle(postModelDtoWithToken.title());
        postModel.setShortContent(postModelDtoWithToken.shortContent());
        postModel.setContent(postModelDtoWithToken.content());
        postModel.setDate(PostModel.convertDate(postModelDtoWithToken.date()));
        postModel.setImageURL(postModelDtoWithToken.imageURL());
        return postModel;
    }
    public static PostModel transformWithoutToken(PostModelDtoWithoutToken postModelDtoWithoutToken){
        PostModel postModel = PostModel.getPostModel();
        postModel.setTitle(postModelDtoWithoutToken.title());
        postModel.setShortContent(postModelDtoWithoutToken.shortContent());
        postModel.setContent(postModelDtoWithoutToken.content());
        postModel.setDate(PostModel.convertDate(postModelDtoWithoutToken.date()));
        postModel.setImageURL(postModelDtoWithoutToken.imageURL());
        return postModel;
    }
}
