public class MatchModel {
    private String homeTeam;
    private String awayTeam;
    private String sportGroup;
    private Double homeOdds;
    private Double awayOdds;
    private String sportTournament;

    public String getSportTournament() {
        return sportTournament;
    }

    public void setSportTournament(String sportTournament) {
        this.sportTournament = sportTournament;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getSportGroup() {
        return sportGroup;
    }

    public void setSportGroup(String sportGroup) {
        this.sportGroup = sportGroup;
    }

    public Double getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(Double homeOdds) {
        this.homeOdds = homeOdds;
    }

    public Double getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(Double awayOdds) {
        this.awayOdds = awayOdds;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", sportGroup='" + sportGroup + '\'' +
                ", homeOdds=" + homeOdds +
                ", awayOdds=" + awayOdds +
                ", sportTournament='" + sportTournament + '\'' +
                '}';
    }
}
