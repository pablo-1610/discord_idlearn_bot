package fr.pablo.bot.data.cache.cache_objects;

public class Team {
    private int id;
    private int pts;

    public Team(int id, int pts) {
        this.id = id;
        this.pts = pts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
