package pl.comp.model;

public enum Difficulty {
    Easy(33), Medium(25), Hard(17);

    public final int value;

    Difficulty(int value) {
        this.value = value;
    }
}
