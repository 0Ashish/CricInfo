package com.cric.apis.crickinfoBackend.services;


import com.cric.apis.crickinfoBackend.Entities.Cricket;
import com.cric.apis.crickinfoBackend.Repositiries.MatchRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;


@Service
public class MatchServiceImp implements MatchService{

    private MatchRepo matchRepo;

    public MatchServiceImp(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    // match history all matches in database
    @Override
    public List<Cricket> getAllMatches() {
        return this.matchRepo.findAll();
    }


    @Override
    public List<Cricket> getLiveMatches() {
        List<Cricket> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = (Document) Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Cricket match1 = new Cricket();
                match1.setTeamHeading(teamsHeading);
                match1.setMatchVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlingTeam(bowlTeam);
                match1.setBowlingTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchlink(matchLink);
                match1.setResult(textComplete);
                match1.setMatchStatus();


                matches.add(match1);

//                update the match in database
                updatematchInDb(match1);




            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
     private void updatematchInDb(Cricket match1){

//     Cricket match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
//     if(match==null){
//         matchRepo.save(match1);
//     }else{
//         match1.setMatchId(match.getMatchId());
//         matchRepo.save(match1);
//     }
         Cricket match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
         if (match == null) {
             this.matchRepo.save(match1);
         } else {

             match1.setMatchId(match.getMatchId());
             this.matchRepo.save(match1);
         }
     }


    @Override
    public List<List<String>> getPointTable() {

        List<List<String>> pointTable = new ArrayList<>();
        String url = "https://www.cricbuzz.com/cricket-stats/points-table/test/icc-world-test-championship";

       try{
           Document document = Jsoup.connect(url).get();
           Elements table = document.select("table.cb-srs-pnts");
           Elements tableHeads = table.select("thead>tr>*");
           List<String> headers = new ArrayList<>();
           tableHeads.forEach(element -> {
               headers.add(element.text());
           });
           pointTable.add(headers);


           Elements bodyTrs = table.select("tbody>*");
           for (Element tr:bodyTrs
           ) {
               List<String> points = new ArrayList<>();
               Elements tds = tr.select("td");
               for (Element td:tds
               ) {
                   points.add(td.text());
               }
               pointTable.add(points);
           }
       }
       catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }
}
