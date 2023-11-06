package com.content.module.posts.dtos;

import com.content.module.posts.model.PostModel;
import lombok.Data;

@Data
public class PostModelDtoWithToken {

    private String id;
    private String title;
    private String shortContent;
    private String content;
    private String date;
    private String imageURL;

    public static PostModel convertFromPostDtoWithToken( PostModelDtoWithToken postModelDtoWithToken){
        PostModel postModel = PostModel.getPostModel();
        postModel.setId(postModelDtoWithToken.getId());
        postModel.setTitle(postModelDtoWithToken.getTitle());
        postModel.setShortContent(postModelDtoWithToken.getShortContent());
        postModel.setContent(postModelDtoWithToken.getContent());
        postModel.setDate(PostModel.convertDate(postModelDtoWithToken.getDate()));
        postModel.setImageURL(postModelDtoWithToken.getImageURL());
        return postModel;
    }
}
