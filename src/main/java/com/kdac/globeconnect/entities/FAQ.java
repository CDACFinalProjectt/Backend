package com.kdac.globeconnect.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FAQ {

    private String question;
    private String answer;

    // Constructor to create FAQ items
    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Static method to get a list of hardcoded FAQs
    public static List<FAQ> getFrequentlyAskedQuestions() {
        List<FAQ> faqs = new ArrayList<>();
        
        faqs.add(new FAQ("What is GlobeConnect?", "GlobeConnect is a social platform that allows users to connect and interact with various communities."));
        faqs.add(new FAQ("How do I create a community?", "To create a community, go to the 'Create Community' section, fill out the necessary details, and submit."));
        faqs.add(new FAQ("How can I report inappropriate content?", "You can report inappropriate content by clicking on the 'Report' button available next to each post or comment."));
        faqs.add(new FAQ("How do I change my profile picture?", "Go to your profile settings and click on 'Change Profile Picture' to upload a new image."));
        faqs.add(new FAQ("How can I delete my account?", "If you want to delete your account, please contact our support team through the 'Help' section for assistance."));

        return faqs;
    }
}
