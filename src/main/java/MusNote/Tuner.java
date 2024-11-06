package MusNote;

import static MusNote.Note.C_note_freq;
import static MusNote.Note.semitonium_down;
import static MusNote.Note.semitonium_up;
import java.util.ArrayList;

public class Tuner {
    public Tuner() {
        
    }
    public boolean isSimilarNotes(Note.NoteName note_id, float frequency) {
        return note_id == getNameBy(frequency, getOctaveBy(frequency));
    }
    public float getSideFrequency(Note note) {
        Note clear_note = new Note(note.getId(), note.getOctave());
        return note.getFrequency() - clear_note.getFrequency();
    }
    
    public static Note roundNote(float frequency) {
        int octave = getOctaveBy(frequency);
        Note.NoteName note_name = getNameBy(frequency, octave);
        return new Note(note_name, octave);
    }

    public static Note.NoteName getNameBy(float frequency, int octave) {
        float half_semitonium_down = (float) Math.sqrt(semitonium_down);
        float note = getFrequencyOf_C_NoteBy(octave) * half_semitonium_down;
        for (int note_number = 0; note_number < Note.NoteName.count; note_number++) {
            float next_note = note * semitonium_up;
            if (frequency >= note && frequency <= next_note) {
                return Note.NoteName.values()[note_number];
            }
            note *= semitonium_up;
        }
        return Note.NoteName.Undefined;
    }

    public static ArrayList<Note> getOctaveNotes(int octave) {
        ArrayList<Note> note_freqs = new ArrayList<>();
        float note = getFrequencyOf_C_NoteBy(octave);
        for (int i = 0; i < Note.NoteName.count; i++) {
            note_freqs.add(new Note(note));
            note *= semitonium_up;
        }
        return note_freqs;
    }

    // returns frequency of C note of specific octave
    public static float getFrequencyOf_C_NoteBy(int octave) {
        return C_note_freq * (float) Math.pow(2., octave);
    }

    public static int getOctaveBy(float note_frequency) {
        float octave_freq = C_note_freq;
        var octave = 0;
        for (;
                octave_freq < note_frequency;
                octave++, octave_freq *= 2) {
        }
        return octave;
    }
}
