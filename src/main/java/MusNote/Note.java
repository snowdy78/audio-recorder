package MusNote;

import java.util.*;

public class Note {

    public enum NoteName {
        C(0), Db(1), D(2), Eb(3), E(4), F(5), Gb(6), G(7), Ab(8), A(9), Bb(10), B(11), Undefined(-1);
        // dieses 2, 4, 7, 9, 11
        public static final int count = 12;
        private final int value;

        private NoteName(int value) {
            this.value = value++;
        }

        public int toInteger() {
            return value;
        }

        public String toDieseString() {
            switch (value % 12) {
                case 0:
                    return "C";
                case 1:
                    return "C#";
                case 2:
                    return "D";
                case 3:
                    return "D#";
                case 4:
                    return "E";
                case 5:
                    return "F";
                case 6:
                    return "F#";
                case 7:
                    return "G";
                case 8:
                    return "G#";
                case 9:
                    return "A";
                case 10:
                    return "A#";
                case 11: 
                    return "B";
            }
            return "Undefined";
        }
    }
    public static final float C_note_freq = 16.35f;
    public static final float semitonium_up = (float) Math.pow(2, 1.f / 12.f);
    public static final float semitonium_down = 1.f / semitonium_up;
    private NoteName id;
    private int octave;
    private float frequency;

    public Note(float frequency) {
        this.octave = Tuner.getOctaveBy(frequency);
        this.id = Tuner.getNameBy(frequency, octave);
        this.frequency = frequency;
    }
    public Note(NoteName name, int octave) {
        this.id = name;
        this.octave = octave;
        this.frequency = (float) 
                (C_note_freq * Math.pow(semitonium_up, name.toInteger())
                * Math.pow(2., octave));
    }
    public int getOctave() {
        return this.octave;
    }

    public float getFrequency() {
        return this.frequency;
    }

    public NoteName getId() {
        return this.id;
    }

    public Note tune(int distance) {
        return new Note(this.frequency * (float) Math.pow(semitonium_up, distance));
    }

    public Note down() {
        return new Note(this.frequency * semitonium_down);
    }

    public Note up() {
        return new Note(this.frequency * semitonium_up);
    }

    // returns true, if note id is the same compare other note
    public boolean isSimilar(Note other) {
        return other.id == this.id;
    }

    // returns true, if frequencies of notes are same
    public boolean isEqual(Note other) {
        return this.frequency == other.frequency;
    }

}
