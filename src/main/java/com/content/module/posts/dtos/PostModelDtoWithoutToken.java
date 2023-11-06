package com.content.module.posts.dtos;

import com.content.module.posts.model.PostModel;
import lombok.Data;

@Data
public class PostModelDtoWithoutToken {

    private String title;
    private String shortContent;
    private String content;
    private String date;
    private String imageURL;

    public static PostModel convertFromPostModelDtoWithouttoken(PostModelDtoWithoutToken postModelDtoWithoutToken){
        PostModel postModel = PostModel.getPostModel();
        postModel.setTitle(postModelDtoWithoutToken.getTitle());
        postModel.setShortContent(postModelDtoWithoutToken.getShortContent());
        postModel.setContent(postModelDtoWithoutToken.getContent());
        postModel.setDate(PostModel.convertDate(postModelDtoWithoutToken.getDate()));
        postModel.setImageURL(postModelDtoWithoutToken.getImageURL());
        return postModel;
    }

}
