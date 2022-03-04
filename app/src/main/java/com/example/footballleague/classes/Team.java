package com.example.footballleague.classes;

import java.io.Serializable;

public class Team implements Serializable {
    private String key;
    private String teamName;
    private String uri;
    private int points;
    private String matchesPlayed;
    private String wins;
    private String draws;
    private String loses;
    private String goalsScored;
    private String goalsAgainst;
    private String goalsDifference;
    private String manger;



    public Team(){ }


    public Team(String teamName, String manger, String uri)
    {
        this.key = " ";
        this.teamName = teamName;
        this.uri = uri;
        this.points = 0;
        this.matchesPlayed = "0";
        this.wins = "0";
        this.draws = "0";
        this.loses = "0";
        this.goalsScored = "0";
        this.goalsAgainst = "0";
        this.goalsDifference = "0";
        this.manger=manger;

    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(String matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLoses() {
        return loses;
    }

    public void setLoses(String loses) {
        this.loses = loses;
    }

    public String getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(String goalsScored) {
        this.goalsScored = goalsScored;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(String goalsDifference) {
        this.goalsDifference = goalsDifference;
    }

    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }
}
