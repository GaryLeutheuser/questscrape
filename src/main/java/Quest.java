import java.util.LinkedList;

public class Quest {
  private String name;
  private String url;
  LinkedList<Quest> requiredQuests;

  public Quest(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public String getUrl() {
    return this.url;
  }

  public LinkedList<Quest> getRequiredQuests() {
    return this.requiredQuests;
  }

  public String toString() {
    return name + " [" + url + "]";
  }

}
