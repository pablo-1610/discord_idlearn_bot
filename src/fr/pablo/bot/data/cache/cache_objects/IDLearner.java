package fr.pablo.bot.data.cache.cache_objects;

public class IDLearner {
    private String id;
    private String name;
    private int level;
    private int EXP;
    private int maxEXP;
    private int notions;
    private int actualNotion;
    private int terminatedTutorials;
    private int actualTutorial;
    private int team;

    public IDLearner(String id, String name, int level, int EXP, int maxEXP, int notions, int actualNotion, int terminatedTutorials, int actualTutorial, int team) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.EXP = EXP;
        this.maxEXP = maxEXP;
        this.notions = notions;
        this.actualNotion = actualNotion;
        this.terminatedTutorials = terminatedTutorials;
        this.actualTutorial = actualTutorial;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public int getMaxEXP() {
        return maxEXP;
    }

    public void setMaxEXP(int maxEXP) {
        this.maxEXP = maxEXP;
    }

    public int getNotions() {
        return notions;
    }

    public void setNotions(int notions) {
        this.notions = notions;
    }

    public int getActualNotion() {
        return actualNotion;
    }

    public void setActualNotion(int actualNotion) {
        this.actualNotion = actualNotion;
    }

    public int getTerminatedTutorials() {
        return terminatedTutorials;
    }

    public void setTerminatedTutorials(int terminatedTutorials) {
        this.terminatedTutorials = terminatedTutorials;
    }

    public int getActualTutorial() {
        return actualTutorial;
    }

    public void setActualTutorial(int actualTutorial) {
        this.actualTutorial = actualTutorial;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}
