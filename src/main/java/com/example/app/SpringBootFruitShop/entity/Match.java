package com.example.app.SpringBootFruitShop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "time")
    String img;
    @Column(name = "participants")
    String name;
    @Column(name = "article")
    String article;
    @Column(name = "description")
    String descr;
    @Column(name = "price")
    Double price;
}
// id
// start_time
// umpire - арбитр
// tournament - турнир
// event - событие/разряд в рамках которого играется матч
// stage - это фаза ивента (отборочная, основная, финальная и тд)
// court - номер корта
// team_size - 1/2/ или больше
// team_competition - командное соревнование
// participants - сами участники
// teams - команды в общем смысле, от имени которых выступают участники
// scores: до скольки игра base_scores, до скольки идет продолжение final_scores, колво выиграных геймов(сетов) game_goal, возможность ничьей tie_ava
// warmup время разминки, произвольное значение секунд
//
