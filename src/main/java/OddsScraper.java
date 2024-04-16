import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class OddsScraper {
    public static void main(String[] args) {
        MatchController matchController = new MatchController();
        List<MatchModel> matchesList = new ArrayList<>();

        HttpResponse<String> response = Unirest.get("https://www.unibet.ro/sportsbook-feeds/views/filter/esports/all/matches?includeParticipants=true&useCombined=true&ncid=1713275428")
                .header("Cookie", "INGRESSCOOKIE_SPORTSBOOK_FEEDS=5372f216f1d72bbfbc3050d588c4fb9c|e6f03e039bb9fba9ad84e4dd980ef8c9; INGRESSCOOKIE_APIGATEWAY=88423c68ca3eaaa51f8a7ffbff40e0be|cfa05ea48f7ba1e9a8f8d10007d08d5e; clientId=polopoly_desktop")
                .asString();
        JSONObject urlBody = new JSONObject(response.getBody());

        matchController.populateMatchesList(urlBody,matchesList);
        matchController.showMatchList(matchesList);
    }
}
