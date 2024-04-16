package controller;

import model.MatchModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MatchController {
    public void populateMatchesList(JSONObject urlBody, List<MatchModel> matchesList) {
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
                            addMatch.setHomeOdds(0.00);
                            addMatch.setAwayOdds(0.00);
                        }
                    }
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public void showMatchList(List<MatchModel> matchesList) {
        for (MatchModel currentMatch : matchesList) {
            System.out.println(currentMatch);
        }
    }
}
