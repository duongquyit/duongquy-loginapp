package com.example.javaapp.Repository;


import java.util.List;

import com.example.javaapp.Entity.Notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NoteRepository extends JpaRepository<Notes,Long> {
    public Notes findByTitle(String title);

    public List<Notes> findByTitleOrContentContaining(String title, String content);

    @Transactional
    @Modifying
    @Query("update Notes n set n.title = ?1, n.content = ?2 where n.id = ?3")
    public void updateNote(String title, String content, Long id);
}