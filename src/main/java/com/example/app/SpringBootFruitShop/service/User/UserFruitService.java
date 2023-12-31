package com.example.app.SpringBootFruitShop.service.User;

import com.example.app.SpringBootFruitShop.entity.Match;
import com.example.app.SpringBootFruitShop.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserFruitService {

    @Autowired
    FruitRepository repository;

    public List<Match> getAll() {
        Iterable<Match> iterable = repository.findAll();
        List<Match> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(match -> new Match(match.getId(),
                                match.getImg(),
                                match.getName(),
                                match.getArticle(),
                                match.getDescr(),
                                match.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }
}
