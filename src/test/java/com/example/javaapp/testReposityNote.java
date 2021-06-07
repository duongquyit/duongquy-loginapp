package com.example.javaapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import com.example.javaapp.Entity.Notes;
import com.example.javaapp.Repository.NoteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class testReposityNote {

    @Autowired
    private NoteRepository noterep;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateNote(){
        Notes note = new Notes();
        LocalDate localDate = LocalDate.now();
        // DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        

        note.setTitle("Test note");
        note.setDateTime(localDate);
        note.setContent("content of note");

        Notes saveNotes = noterep.save(note);
        Notes exiNotes = entityManager.find(Notes.class, saveNotes.getId());
        assertThat(exiNotes.getTitle()).isEqualTo(note.getTitle());
    }
    
}
