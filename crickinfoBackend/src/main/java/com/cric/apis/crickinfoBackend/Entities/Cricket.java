package com.cric.apis.crickinfoBackend.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="cric-matches")
public class Cricket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    private String teamHeading;
    private String matchVenue;
    private String battingTeam;
    private String battingTeamScore;
    private String bowlingTeam;
    private String bowlingTeamScore;
    private String liveText;
    private String matchlink;
    private String result;
    @Enumerated
    private MatchStatus status;
    private Date date = new Date();


    public Cricket() {
    }
    public Cricket(int matchId, String teamHeading, String matchVenue, String battingTeam, String battingTeamScore, String bowlingTeam, String bowlingTeamScore, String liveText, String matchlink, String result, MatchStatus status) {
        this.matchId = matchId;
        this.teamHeading = teamHeading;
        this.matchVenue = matchVenue;
        this.battingTeam = battingTeam;
        this.battingTeamScore = battingTeamScore;
        this.bowlingTeam = bowlingTeam;
        this.bowlingTeamScore = bowlingTeamScore;
        this.liveText = liveText;
        this.matchlink = matchlink;
        this.result = result;
        this.status = status;
    }

//     setting the matchStatus according to result
    public void setMatchStatus(){
        if(result.isBlank()){
            this.status = MatchStatus.LIVE;
        }else{
            this.status = MatchStatus.COMPELETED;
        }
    }


    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTeamHeading() {
        return teamHeading;
    }

    public void setTeamHeading(String teamheading) {
        this.teamHeading = teamheading;
    }

    public String getMatchVenue() {
        return matchVenue;
    }

    public void setMatchVenue(String matchVenue) {
        this.matchVenue = matchVenue;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getBattingTeamScore() {
        return battingTeamScore;
    }

    public void setBattingTeamScore(String battingTeamScore) {
        this.battingTeamScore = battingTeamScore;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }



    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public String getBowlingTeamScore() {
        return bowlingTeamScore;
    }

    public void setBowlingTeamScore(String bowlingTeamScore) {
        this.bowlingTeamScore = bowlingTeamScore;
    }

    public String getLiveText() {
        return liveText;
    }

    public void setLiveText(String liveTest) {
        this.liveText = liveTest;
    }

    public String getMatchlink() {
        return matchlink;
    }

    public void setMatchlink(String matchlink) {
        this.matchlink = matchlink;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }






}
