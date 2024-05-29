package onTrack;

import onTrack.models.Task;

import java.util.regex.Pattern;

public class FeedbackGenerator {

    public String generateFeedback(Task task) {
        if (task.getContent() == null || task.getContent().trim().isEmpty()) {
            return "Your task content is empty.";
        }
        
        String content = task.getContent().toLowerCase();

        if (Pattern.compile(Pattern.quote("error"), Pattern.CASE_INSENSITIVE).matcher(content).find()) {
            return "Your task contains errors.";
        }

        if (Pattern.compile(Pattern.quote("missing"), Pattern.CASE_INSENSITIVE).matcher(content).find()) {
            return "Your task is missing some components.";
        }

        if (Pattern.compile(Pattern.quote("incorrect"), Pattern.CASE_INSENSITIVE).matcher(content).find()) {
            return "Your task contains incorrect information.";
        }

        return "Your task is correct.";
    }
}