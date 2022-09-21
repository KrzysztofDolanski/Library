package com.epam.page;


import groovyjarjarantlr4.runtime.misc.Stats;

import java.util.HashSet;
import java.util.Set;

public class Statistician {
//
//    private final Set<Page> evaluatedPages;
//    int numberOfIssues;
//    int numberOfPrs;
//    int numberOfExternalPages;
//    int numberOfErrorPages;
//
//    public Statistician() {
//        evaluatedPages = new HashSet<>();
//    }
//
//    public static Stats evaluate(Page rootPage) {
//        Statistician statistician = new Statistician();
//        statistician.evaluateTree(rootPage);
//        return statistician.result();
//    }
//
//    private void evaluateTree(Page page) {
//        if (page instanceof GitHubPage ghPage)
//            ghPage.subtree().forEach(this::evaluatePage);
//        else
//            evaluatePage(page);
//    }
//
//
//    public void evaluatePage(Page page) {
//        if (evaluatedPages.contains(page))
//            return;
//        evaluatedPages.add(page);
//
//        switch (page){
//            case ErrorPage __ -> numberOfErrorPages++;
//            case ExternalPage __ -> numberOfExternalPages++;
//            case GitHubPrPage __ -> numberOfPrs++;
//            case GitHubIssuePage __ -> numberOfIssues++;
//        }
//    }
//
//    private Stats result(){
//        return new Stats(numberOfIssues, numberOfPrs, numberOfExternalPages, numberOfErrorPages);
//    }
//
//    public record Stats(int numberOfIssues, int numberOfPrs, int numberOfExternalPages, int numberOfErrorPages) {
//
//    }
}
