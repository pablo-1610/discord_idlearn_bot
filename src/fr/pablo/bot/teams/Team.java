package fr.pablo.bot.teams;

public enum Team {
    RED(742336596132757574L,742336823631544441L,"❤️", 742335636253704224L),
    BLUE(742337283101032478L,742337447505035415L,"\uD83D\uDC99", 742335879070089226L),
    YELLOW(742337569370538025L,742337624664178779L,"\uD83D\uDC9B", 742335741589192766L),
    GREEN(742338122767138917L,742338215046021170L,"\uD83D\uDC9A", 742335792516432032L);

    private long announceChannelID;
    private long chatChannelID;
    private String prefix;
    private long roleID;

    Team(long announceChannelID, long chatChannelID, String prefix, long roleID) {
        this.announceChannelID = announceChannelID;
        this.chatChannelID = chatChannelID;
        this.prefix = prefix;
        this.roleID = roleID;
    }

    public long getAnnounceChannelID() {
        return announceChannelID;
    }

    public void setAnnounceChannelID(long announceChannelID) {
        this.announceChannelID = announceChannelID;
    }

    public long getChatChannelID() {
        return chatChannelID;
    }

    public void setChatChannelID(long chatChannelID) {
        this.chatChannelID = chatChannelID;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }
}
