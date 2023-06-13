package by.mlechka.composite.component;

public class LetterCount {
    private int vowelsCount;
    private int consonantsCount;

    public LetterCount(int vowelsCount, int consonantsCount) {
        this.vowelsCount = vowelsCount;
        this.consonantsCount = consonantsCount;
    }

    public int getVowelsCount() {
        return vowelsCount;
    }

    public int getConsonantsCount() {
        return consonantsCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LetterCount{");
        sb.append("vowels amount=").append(vowelsCount);
        sb.append(", consonants amount=").append(consonantsCount);
        sb.append('}');
        return sb.toString();
    }
}