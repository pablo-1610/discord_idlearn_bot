package fr.pablo.bot.modules.autoevents.objects;

import java.util.ArrayList;
import java.util.List;

public class EventQuestion {
    private String question;
    private List<String> correctAwnser;

    public EventQuestion(String question, List<String> correctAwnser) {
        this.question = question;
        this.correctAwnser = correctAwnser;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getCorrectAwnser() {
        return correctAwnser;
    }

    public void setCorrectAwnser(List<String> correctAwnser) {
        this.correctAwnser = correctAwnser;
    }
}
