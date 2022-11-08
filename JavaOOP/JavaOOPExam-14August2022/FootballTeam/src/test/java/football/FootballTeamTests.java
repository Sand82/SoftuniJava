package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {

    private FootballTeam team;
    private Footballer player;
    private Footballer playerTwo;

    @Before
    public void setUp() {

        team = new FootballTeam("Test", 10);

        player = new Footballer("Sand");
        playerTwo = new Footballer("Misho");
    }

    @Test
    public void getNameShouldReturnCorrectValue() {

        Assert.assertEquals(team.getName(), "Test");
    }

    @Test (expected = NullPointerException.class)
    public void setNameShouldThrowExceptionWhenNameIsInvalid() {
        FootballTeam footballTeam = new FootballTeam(" ", 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setVacantPositionThrowExceptionWhenNameIsInvalid() {
        FootballTeam footballTeam = new FootballTeam("Test2", -1);
    }



    @Test
    public void getVacantPositionsShouldReturnCorrectValue() {

        Assert.assertEquals(team.getVacantPositions(), 10);
    }

    @Test
    public void getCountShouldReturnCorrectValue() {

        Assert.assertEquals(team.getCount(), 0);
    }

    @Test
    public void addFootballerShouldReturnCorrectValue() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        Assert.assertEquals(team.getCount(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addFootballerShouldThrowExceptionWhenNoPlaceInTheTeam() {
        FootballTeam footballTeam = new FootballTeam("Test2", 1);
        footballTeam.addFootballer(player);

        footballTeam.addFootballer(playerTwo);
    }

    @Test
    public void removeFootballerShouldRemoveCorrectPlayer() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        Assert.assertEquals(team.getCount(), 2);

        team.removeFootballer("Sand");
        Assert.assertEquals(team.getCount(), 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeFootballerShouldThrowExceptionWhenPlayerDoNotExist() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        Assert.assertEquals(team.getCount(), 2);

        team.removeFootballer("Pesho");
    }

    @Test
    public void footballerForSaleShouldReturnCorrectValue() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        Assert.assertTrue(player.isActive());
        Assert.assertEquals(team.footballerForSale("Sand").getName(), "Sand");
        Assert.assertFalse(team.footballerForSale("Sand").isActive());
    }

    @Test (expected = IllegalArgumentException.class)
    public void footballerForSaleShouldThrowExceptionWhenPlayerDoNotExist() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        team.footballerForSale("Gosho");
    }

    @Test
    public void getStatisticsShouldReturnCorrectResult() {

        team.addFootballer(player);
        team.addFootballer(playerTwo);

        String expect = "The footballer Sand, Misho is in the team Test.";

        Assert.assertEquals(team.getStatistics(), expect);
    }
}
