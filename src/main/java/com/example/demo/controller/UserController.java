package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;

@Controller
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	User user;

	@Autowired
	private com.example.demo.model.User sessionUser;

	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {

		session.invalidate();

		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// 入力チェック
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			model.addAttribute("message", "メールアドレスとパスワードを入力してください");
			return "login";
		}

		Optional<Account> accountOpt = accountRepository.findByEmailAndPassword(email, password);

		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();

			sessionUser.setName(account.getName());
			sessionUser.setEmail(account.getEmail());

			return "redirect:/tasks";
		} else {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
	}

	@GetMapping("/signin")
	public String account() {
		return "accountForm";
	}

	@PostMapping("/signin")
	public String store(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("passwordConfirm") String passwordConfirm,
			Model model) {

		ArrayList<String> errors = new ArrayList<>();

		if (name.isEmpty()) {
			errors.add("名前は必須です");
		}

		if (email.isEmpty()) {
			errors.add("メールアドレスは必須です");
		}

		if (password.isEmpty()) {
			errors.add("パスワードは必須です");
		}

		if (passwordConfirm.isEmpty()) {
			errors.add("確認用パスワードは必須です");
		}

		if (accountRepository.existsByEmail(email)) {
			errors.add("登録済みのメールアドレスです");
		}

		if (!password.equals(passwordConfirm)) {
			errors.add("パスワードが一致していません");
		}

		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "accountForm";
		}

		Account newAccount = new Account(email, name, password);

		model.addAttribute("name", name);
		model.addAttribute("email", email);

		accountRepository.save(newAccount);

		return "redirect:/login";
	}

}
