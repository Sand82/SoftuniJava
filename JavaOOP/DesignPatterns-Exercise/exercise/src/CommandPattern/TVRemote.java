package CommandPattern;

public class TVRemote {

    private int volume;
    private String program;

    public TVRemote(int volume, String program) {
        this.volume = volume;
        this.program = program;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
