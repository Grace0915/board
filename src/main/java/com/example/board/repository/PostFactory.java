package com.example.board.repository;

import java.util.Date;
import java.util.UUID;

/**
 * 投稿のファクトリークラス.
 */
public class PostFactory {

    /** 非公開 */
    private PostFactory() {
    }

    /**
    * 新規の投稿を生成する
    *
    * @return 新規の投稿
    */
    public static Post newPost() {
        Post post = new Post();
        return post;
    }

    /**
    * 入力内容を設定した投稿を生成する
    *
    * @param post 投稿
    * @return 投稿
    */
    public static Post createPost(Post post) {
        String id = UUID.randomUUID().toString();
        post.setId(id);
        Date current = new Date();
        post.setCreatedDate(current);
        post.setUpdatedDate(current);
        return post;
    }

    /**
    * 更新内容を設定した投稿を生成する
    *
    * @param post 投稿
    * @return 投稿
    */
    public static Post updatePost(Post post, Post form) {
        post.setAuthor(form.getAuthor());
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        Date current = new Date();
        post.setUpdatedDate(current);
        return post;
    }

    /**
    * 削除内容を設定した投稿を生成する
    *
    * @param post 投稿
    * @return 投稿
    */
    public static Post deletePost(Post post) {
        post.setDeleted(true);
        Date current = new Date();
        post.setUpdatedDate(current);
        return post;
    }

}