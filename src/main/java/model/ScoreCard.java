package model;

import javax.persistence.*;

@Table(schema = "test_schema", name="score_cards")
@NamedQuery(name = "ScoreCard.findAll", query = "select t from ScoreCard t")
@Entity
public class ScoreCard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long score_card_id;


    private Integer ones;
    private Integer twos;
    private Integer threes;
    private Integer fours;
    private Integer fives;
    private Integer sixes;
    private Integer bonus;
    private Integer three_kind;
    private Integer four_kind;
    private Integer full_house;
    private Integer small_straight;
    private Integer large_straight;
    private Integer yatzy;
    private Integer chance;
    private Integer total;

    public Long getScore_card_id() {
        return score_card_id;
    }

    public Integer getOnes() {
        return ones;
    }

    public void setOnes(Integer ones) {
        this.ones = ones;
    }

    public Integer getTwos() {
        return twos;
    }

    public void setTwos(Integer twos) {
        this.twos = twos;
    }

    public Integer getThrees() {
        return threes;
    }

    public void setThrees(Integer threes) {
        this.threes = threes;
    }

    public Integer getFours() {
        return fours;
    }

    public void setFours(Integer fours) {
        this.fours = fours;
    }

    public Integer getFives() {
        return fives;
    }

    public void setFives(Integer fives) {
        this.fives = fives;
    }

    public Integer getSixes() {
        return sixes;
    }

    public void setSixes(Integer sixes) {
        this.sixes = sixes;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getThree_kind() {
        return three_kind;
    }

    public void setThree_kind(Integer three_kind) {
        this.three_kind = three_kind;
    }

    public Integer getFour_kind() {
        return four_kind;
    }

    public void setFour_kind(Integer four_kind) {
        this.four_kind = four_kind;
    }

    public Integer getFull_house() {
        return full_house;
    }

    public void setFull_house(Integer full_house) {
        this.full_house = full_house;
    }

    public Integer getSmall_straight() {
        return small_straight;
    }

    public void setSmall_straight(Integer small_straight) {
        this.small_straight = small_straight;
    }

    public Integer getLarge_straight() {
        return large_straight;
    }

    public void setLarge_straight(Integer large_straight) {
        this.large_straight = large_straight;
    }

    public Integer getYatzy() {
        return yatzy;
    }

    public void setYatzy(Integer yatzy) {
        this.yatzy = yatzy;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ScoreCard{" +
                "score_card_id=" + score_card_id +
                ", ones=" + ones +
                ", twos=" + twos +
                ", threes=" + threes +
                ", fours=" + fours +
                ", fives=" + fives +
                ", sixes=" + sixes +
                ", bonus=" + bonus +
                ", three_kind=" + three_kind +
                ", four_kind=" + four_kind +
                ", full_house=" + full_house +
                ", small_straight=" + small_straight +
                ", large_straight=" + large_straight +
                ", yatzy=" + yatzy +
                ", chance=" + chance +
                ", total=" + total +
                '}';
    }
}