package com.coravy.hudson.plugins.github;

import static org.junit.Assert.assertEquals;
import hudson.MarkupText;

import org.junit.Test;

public class GithubLinkAnnotatorTest {

    private final static String GITHUB_URL = "http://github.com/juretta/iphone-project-tools/";

    @Test
    public final void testAnnotateStringMarkupText() {
        assertAnnotatedTextEquals("An issue Closes #1 link",
                "An issue Closes <a href='" + GITHUB_URL
                        + "issues/1'>#1</a> link");
        assertAnnotatedTextEquals("An issue Close #1 link",
                "An issue Close <a href='" + GITHUB_URL
                        + "issues/1'>#1</a> link");
        assertAnnotatedTextEquals("An issue closes #123 link",
                "An issue closes <a href='" + GITHUB_URL
                        + "issues/123'>#123</a> link");
        assertAnnotatedTextEquals("An issue close #9876 link",
                "An issue close <a href='" + GITHUB_URL
                        + "issues/9876'>#9876</a> link");

        assertAnnotatedTextEquals("An issue fixes #9876 link",
                "An issue fixes <a href='" + GITHUB_URL
                        + "issues/9876'>#9876</a> link");

        assertAnnotatedTextEquals("An issue fixed #9876 link",
                "An issue fixed <a href='" + GITHUB_URL
                        + "issues/9876'>#9876</a> link");

        assertAnnotatedTextEquals("An issue will fix #9876 link",
                "An issue will fix <a href='" + GITHUB_URL
                        + "issues/9876'>#9876</a> link");
    }

    @Test
    public final void testGHIssueLinks() {
        assertAnnotatedTextEquals("An issue will fix gh-9876 link",
                "An issue will fix <a href='" + GITHUB_URL
                        + "issues/9876'>gh-9876</a> link");
    }

    private void assertAnnotatedTextEquals(final String originalText,
            final String expectedAnnotatedText) {
        MarkupText markupText = new MarkupText(originalText);

        GithubLinkAnnotator annotator = new GithubLinkAnnotator();
        annotator.annotate(new GithubUrl(GITHUB_URL), markupText, null);

        assertEquals(expectedAnnotatedText, markupText.toString());
    }
}
