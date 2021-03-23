package no.hvl.dat109.WebYatzy.dao;

import no.hvl.dat109.WebYatzy.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> findAll();
}
