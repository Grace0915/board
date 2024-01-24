package com.example.board.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.board.validation.Group1;
import com.example.board.validation.Group2;

import lombok.Data;

/**
 * 投稿.
 */
@Entity
@Table(name = "post")
@Data
public class Post {

	/** ID */
	@Id
	@Column
	@NotNull
	private String id = null;

	/** 投稿者 */
	@Column(length = 20, nullable = false)
	@NotEmpty(groups = Group1.class)
	@Size(min = 1, max = 20, groups = Group2.class)
	private String author = null;

	/** タイトル */
	@Column(length = 20, nullable = false)
	@NotEmpty(groups = Group1.class)
	@Size(min = 1, max = 20, groups = Group2.class)
	private String title = null;

	/** 内容 */
	@Column(length = 1000, nullable = false)
	@NotEmpty(groups = Group1.class)
	@Size(min = 1, max = 1000, groups = Group2.class)
	private String body = null;

	/** 登録日時 */
	private Date createdDate = null;

	/** 更新日時 */
	private Date updatedDate = null;

	/** 削除済 */
	private boolean deleted = false;

}