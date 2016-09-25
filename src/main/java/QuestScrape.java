import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.*;

import java.io.IOException;

public class QuestScrape {
  public static void main(String[] args) throws IOException {
    final String questListURL = "http://runescape.wikia.com/wiki/List_of_quests/Full";

    Document questList = Jsoup.connect(questListURL).get();

    Elements tableRows = questList.getElementsByTag("tr");

    // System.out.println("There are " + (tableRows.size() - 8) + " quests in RuneScape.");

    LinkedList<Quest> quests = new LinkedList<Quest>();

    // Initially, store all quest names and URLs. Then, the URLs can be iterated
    // over later to retrieve the requirements and rewards of each.
    for (int i = 8; i < tableRows.size(); i++) {
      Element questElement = tableRows.get(i).child(0);
      String questName = questElement.text();
      String questUrl = questElement.child(0).attr("abs:href");

      quests.add(new Quest(questName, questUrl));
    }

    // for (Quest q : quests)
    //   System.out.println(q);

    // While this should be looped to run on all quests, to save testing
    // time and RS wiki bandwidth, I'm going to only test on 1 quest.
    // Quest quest = quests.get(87);

    for (Quest quest : quests) {
      Document questDoc = Jsoup.connect(quest.getUrl()).get();
      Elements els = questDoc.getElementsByClass("questdetails-info");
      Elements reqts = els.get(4).children();

      // Get list of quest reqs
      System.out.println(quest);
      System.out.println("Requirements:");
      Elements questReqs = questDoc.getElementsByClass("questreq");
      if (questReqs.size() != 0) {
        Elements questReqElements = questReqs.select("li");
        System.out.println(questReqElements.get(1).text());
      }
      System.out.println("\n");

      // if (reqts.size() > 0 && reqts.get(0).children().size() != 0) {
      //   System.out.println("There are quest requirements!");
      // } else {
      //   System.out.println("No quests required.");
      // }
      //
      // for (Element e : reqts)
      //   System.out.println(e.text());
    }
  }
}
