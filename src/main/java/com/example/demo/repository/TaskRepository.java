package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByCategoryId(Integer categoryId);

	List<Task> findByDoneTrue();

	List<Task> findByDoneFalse();

	List<Task> findByCategoryIdAndDoneFalse(Integer categoryId);

	@Query("SELECT t.categoryId, COUNT(t) FROM Task t WHERE t.done = false GROUP BY t.categoryId")
	List<Object[]> countTasksByCategory();

	List<Task> findByDoneFalseOrderByIdAsc();

	List<Task> findByCategoryIdAndDoneFalseOrderByIdAsc(Integer categoryId);

	List<Task> findByDoneFalseOrderByClosingDateAsc();

}