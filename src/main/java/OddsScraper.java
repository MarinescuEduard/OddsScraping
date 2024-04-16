
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class OddsScraper {
    public static void main(String[] args) {
        List<MatchModel> matchesList = new ArrayList<>();

        HttpResponse<String> response = Unirest.get("https://www.unibet.ro/sportsbook-feeds/views/filter/esports/all/matches?includeParticipants=true&useCombined=true&ncid=1713275428")
                .header("Cookie", "INGRESSCOOKIE_SPORTSBOOK_FEEDS=5372f216f1d72bbfbc3050d588c4fb9c|e6f03e039bb9fba9ad84e4dd980ef8c9; INGRESSCOOKIE_APIGATEWAY=88423c68ca3eaaa51f8a7ffbff40e0be|cfa05ea48f7ba1e9a8f8d10007d08d5e; clientId=polopoly_desktop")
                .asString();
        JSONObject urlBody = new JSONObject(response.getBody());

        JSONArray allGroupsJsonArray = urlBody.getJSONObject("layout").getJSONArray("sections").getJSONObject(1).getJSONArray("widgets").getJSONObject(0).getJSONObject("matches").getJSONArray("groups");

        try {
            for (int i = 0; i < allGroupsJsonArray.length(); i++) {
                String currentSport = allGroupsJsonArray.getJSONObject(i).getString("sport");

                JSONArray allSubGroupsJsonArray = allGroupsJsonArray.getJSONObject(i).getJSONArray("subGroups");

                for (int j = 0; j < allSubGroupsJsonArray.length(); j++) {
                    String matchesSubgroup = allSubGroupsJsonArray.getJSONObject(j).getString("englishName");

                    JSONArray currentSubgroup = allSubGroupsJsonArray.getJSONObject(j).getJSONArray("events");

                    for (int z = 0; z < currentSubgroup.length(); z++) {
                        JSONObject selectedMatch = currentSubgroup.getJSONObject(z);

                        MatchModel addMatch = new MatchModel();
                        addMatch.setSportGroup(currentSport);
                        addMatch.setSportTournament(matchesSubgroup);
                        addMatch.setHomeTeam(selectedMatch.getJSONObject("event").getString("homeName"));
                        addMatch.setAwayTeam(selectedMatch.getJSONObject("event").getString("awayName"));

                        try {
                            JSONArray possibleOutcomes = selectedMatch.getJSONObject("mainBetOffer").getJSONArray("outcomes");
                            addMatch.setHomeOdds(possibleOutcomes.getJSONObject(0).getDouble("oddsDecimal"));
                            addMatch.setAwayOdds(possibleOutcomes.getJSONObject(1).getDouble("oddsDecimal"));

                            matchesList.add(addMatch);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        for (MatchModel currentMatch : matchesList) {
            System.out.println(currentMatch);
        }
    }
}
