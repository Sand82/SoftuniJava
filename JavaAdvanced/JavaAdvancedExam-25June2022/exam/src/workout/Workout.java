package workout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Workout {
    private String type;
    private int exerciseCount;
    private List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        if (this.exercises.size() < this.exerciseCount) {
            this.exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle) {
        Exercise exercise = getExercise(name, muscle);

        if (exercise == null) {
            return false;
        }

        exercises.remove(exercise);
        return true;
    }

    public Exercise getExercise(String name, String muscle) {
        return exercises
                .stream()
                .filter(e -> e.getName().equals(name) && e.getMuscle().equals(muscle))
                .findFirst()
                .orElse(null);
    }

    public Exercise getMostBurnedCaloriesExercise() {

        return exercises.stream()
                .max(Comparator.comparingInt(e -> e.getBurnedCalories()))
                .stream()
                .findFirst().orElse(null);

    }

    public int getExerciseCount() {
        return exercises.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Workout type: %s", type));
        sb.append(System.lineSeparator());

        for (Exercise exercise : exercises) {
            sb.append(String.format("Exercise: %s", exercise));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
