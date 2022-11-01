package CommandPattern;

public class Main {
    public static void main(String[] args) {
        TVRemote tvRemote = new TVRemote(12, "Sand Show");

        VolumeUpCommand volumeUpCommand = new VolumeUpCommand(tvRemote);
        VolumeDownCommand volumeDownCommand = new VolumeDownCommand(tvRemote);
        volumeDownCommand.execute();
        volumeDownCommand.execute();
        volumeDownCommand.execute();
        volumeUpCommand.execute();
        volumeUpCommand.execute();
        volumeUpCommand.execute();
        volumeUpCommand.execute();
        volumeUpCommand.execute();
        volumeUpCommand.execute();

    }
}
