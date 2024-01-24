package com.example.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board.repository.Post;
import com.example.board.repository.PostFactory;
import com.example.board.repository.PostRepository;
import com.example.board.validation.GroupOrder;

/**
 * 掲示板のフロントコントローラー.
 */
@Controller
public class BoardController {
	@Autowired
	private PostRepository repository;

	/**
	* 一覧を表示する。
	*
	* @param model モデル
	* @return テンプレート
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("form", PostFactory.newPost());
		model = this.setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	/**
	+   * 一覧を設定する。
	+   *
	+   * @param model モデル
	+   * @return 一覧を設定したモデル
	+   */
	private Model setList(Model model) {
		Iterable<Post> list = repository.findByDeletedFalseOrderByUpdatedDateDesc();
		model.addAttribute("list", list);
		return model;
	}

	/**
	   * 登録する。
	   *
	   * @param form  フォーム
	   * @param model モデル
	   * @return テンプレート
	   */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("form") @Validated(GroupOrder.class) Post form, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			repository.saveAndFlush(PostFactory.createPost(form));
			model.addAttribute("form", PostFactory.newPost());
		}
		model = this.setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	/**
	 * 編集する投稿を表示する
	 *
	 * @param form  フォーム
	 * @param model モデル
	 * @return テンプレート
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ModelAttribute("form") Post form, Model model) {
		Optional<Post> post = repository.findById(form.getId());
		model.addAttribute("form", post);
		model = setList(model);
		model.addAttribute("path", "update");
		return "layout";
	}

	/**
	* 更新する
	*
	* @param form  フォーム
	* @param model モデル
	* @return テンプレート
	*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") @Validated(GroupOrder.class) Post form, BindingResult result, Model model) {
		if (!result.hasErrors()) {
            Optional<Post> post = repository.findById(form.getId());
    		repository.saveAndFlush(PostFactory.updatePost(post.get(), form));
		}
		model.addAttribute("form", PostFactory.newPost());
		model = setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	/**
	   * 削除する
	   *
	   * @param form  フォーム
	   * @param model モデル
	   * @return テンプレート
	   */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("form") Post form, Model model) {
		Optional<Post> post = repository.findById(form.getId());
		repository.saveAndFlush(PostFactory.deletePost(post.get()));
		model.addAttribute("form", PostFactory.newPost());
		model = setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}
}