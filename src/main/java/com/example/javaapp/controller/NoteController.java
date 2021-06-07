package com.example.javaapp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.javaapp.DetailService.NoteDetailService;
import com.example.javaapp.DetailService.UserDetailService;
import com.example.javaapp.Entity.Notes;
import com.example.javaapp.Entity.User;
import com.example.javaapp.Repository.NoteRepository;
import com.example.javaapp.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private NoteRepository noterep;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private NoteDetailService noteDetailService;

    @RequestMapping(value = ("/users"), method = RequestMethod.GET)
    public String viewListNotes(Model model){

        User user = new User();
        Notes note = new Notes();
        List<Notes> listNote = noterep.findAll();
        List<User> lUsers = userDetailService.getAllUser();

        model.addAttribute("User", user);
        model.addAttribute("Note", note);
        model.addAttribute("listNotes", listNote);
        model.addAttribute("listUser", lUsers);

        return "listNotes";
    }

    // add note form
    @RequestMapping(value = ("/addnote"), method = RequestMethod.GET)
    public String addNote(Model model){
        Notes note = new Notes();
        model.addAttribute("newNote", note);
        return "addnote";
    }

    // process add note
    @RequestMapping(value = ("/process_add_note"), method = RequestMethod.POST)
    public String processAddNote(Notes note, Principal principal){
        // // get email user present
        String email = principal.getName();
        // // get date present
        LocalDate localDate = LocalDate.now();
        note.setDateTime(localDate);

        User user = repo.findByEmail(email);

        note.setUser(user);

        List<Notes> lNotes = Arrays.asList(note);
        noterep.save(note);

        user.setListNote(lNotes);

        return "process_add_note";
    }

    // fillter note function
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filterNoteByUser(@RequestParam Long id,Model model){
        User user = new User();
        if(id == 0){
            return "filter";
        }else{
            user = userDetailService.findUserById(id);
        }
        List<Notes> lNotes = user.getListNote();
        model.addAttribute("notes", lNotes);
        return "filter";
    }

    // search note function
    @RequestMapping(value = "/search_note", method = RequestMethod.GET)
    public String searchNote(String title, Model model){

        List<Notes> lNotes = new ArrayList<Notes>();

        lNotes.addAll(noterep.findByTitleOrContentContaining(title, title));

        model.addAttribute("listNote", lNotes);
        return "search_note";
    }

    // my note
    @RequestMapping(value = "/mynote", method = RequestMethod.GET)
    public String myNote(Model model, Principal principal){
        String email = principal.getName();
        User user = repo.findByEmail(email);
        List<Notes> lNotes = user.getListNote();
        model.addAttribute("listNote", lNotes);
        return "mynote";
    }

    // delete note
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable Long id, Model model, Principal principal){
        String email = principal.getName();
        User user = repo.findByEmail(email);
        List<Notes> lNotes = user.getListNote();
        Notes note = noteDetailService.findNoteById(id);
        model.addAttribute("note", note);
        noterep.delete(note);
        model.addAttribute("listNote", lNotes);
        return "mynote";
    }

    // update note form
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateNote(@PathVariable Long id, Model model){
        Notes note = noteDetailService.findNoteById(id);
        model.addAttribute("note", note);
        return "update";
    }

    // update note process
    @RequestMapping(value = "/process_update/{id}", method = RequestMethod.POST)
    public String processUpdate(@PathVariable Long id, Notes note, Model model, Principal principal){
        User user = repo.findByEmail(principal.getName());
        List<Notes> lNotes = user.getListNote();
        noterep.updateNote(note.getTitle(), note.getContent(), id);
        model.addAttribute("listNote", lNotes);
        return "mynote";
    }
}
