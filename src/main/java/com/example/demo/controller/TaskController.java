package com.example.demo.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	HttpSession session;

	@Autowired
	User user;

	// タスク一覧ページを表示（未完了のタスクのみ）
	@GetMapping("/tasks")
	public String index(@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "closing_date", required = false) Integer closing_date,
			@RequestParam(name = "dateAsc", required = false) String dateAsc,
			Model model) {

		// カテゴリー一覧を取得してビューに渡す
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		// カテゴリーで絞り込みしつつ、ID昇順で未完了タスクを取得
		List<Task> taskList;

		if (categoryId != null) {
			taskList = taskRepository.findByCategoryIdAndDoneFalseOrderByIdAsc(categoryId);
		} else {
			taskList = taskRepository.findByDoneFalseOrderByIdAsc();
		}

		if (dateAsc != null) {
			taskList = taskRepository.findByDoneFalseOrderByClosingDateAsc();
		}

		for (Task tasks : taskList) {
			long days = ChronoUnit.DAYS.between(LocalDate.now(), tasks.getClosingDate());

			if (days < 0) {
				tasks.setCloseDate("past");
			} else if (days == 0) {
				tasks.setCloseDate("today");
			} else if (days == 1) {
				tasks.setCloseDate("tomorrow");
			} else if (days == 2 || days == 3) {
				tasks.setCloseDate("threedays");
			} else {
				tasks.setCloseDate("someday");
			}
		}
		model.addAttribute("tasks", taskList);
		model.addAttribute("user", user);

		// tasks.html を表示
		return "tasks";
	}

	// タスク新規作成画面を表示
	@GetMapping("/tasks/add")
	public String create(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "addTask";
	}

	// タスク新規作成処理
	@PostMapping("/tasks/add")
	public String store(
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "closing_date", defaultValue = "") LocalDate closing_date,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		ArrayList<String> errors = new ArrayList<>();

		if (title.isEmpty()) {
			errors.add("タイトルは必須です");
		}

		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("categories", categoryRepository.findAll());

			return "addTask";
		}

		Task task = new Task(title, categoryId, closing_date, memo);
		taskRepository.save(task);

		return "redirect:/tasks";
	}

	// タスク編集画面の表示
	@GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		// IDに対応するタスクを取得して画面に渡す
		Task task = taskRepository.findById(id).get();
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("task", task);
		model.addAttribute("categories", categories);

		return "editTask";
	}

	// タスク編集の保存処理
	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "closing_date", defaultValue = "") LocalDate closing_date,
			@RequestParam(name = "memo", defaultValue = "") String memo) {

		// 対象タスクを取得して値を更新
		Task task = taskRepository.findById(id).get();
		task.setTitle(title);
		task.setCategoryId(categoryId);
		task.setClosingDate(closing_date);
		task.setMemo(memo);

		taskRepository.save(task);

		return "redirect:/tasks";
	}

	// タスクの削除処理
	@PostMapping("/tasks/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		taskRepository.deleteById(id);
		return "redirect:/tasks";
	}

	// 完了済みタスク一覧を表示
	@GetMapping("/tasks/completed")
	public String doneList(Model model) {
		List<Task> doneTasks = taskRepository.findByDoneTrue();
		model.addAttribute("tasks", doneTasks);
		return "doneList";
	}

	// タスクを完了状態にする（チェックボックスで完了）
	@PostMapping("/tasks/{id}/complete")
	public String markAsDone(@PathVariable("id") Integer id) {
		Task task = taskRepository.findById(id).get();
		if (task != null) {
			task.setDone(true);
			taskRepository.save(task);
		}
		return "redirect:/tasks";
	}

	// タスクを未完了に戻す（「戻す」ボタン）
	@PostMapping("/tasks/{id}/undo")
	public String undoDone(@PathVariable("id") Integer id) {
		Task task = taskRepository.findById(id).get();
		if (task != null) {
			task.setDone(false);
			taskRepository.save(task);
		}
		return "redirect:/tasks/completed";
	}
}
