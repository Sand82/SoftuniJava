package commandPattern;

public class VolumeUpCommand implements Command {

    private TVRemote tvRemote;

    public VolumeUpCommand(TVRemote tvRemote) {
        this.tvRemote = tvRemote;
    }

    @Override
    public void execute() {
        int oldVolume = tvRemote.getVolume();
        int newVolume = oldVolume + 1;
        tvRemote.setVolume(newVolume);
        System.out.println("New volume is " + newVolume);

    }
}
