package POJOs;


public class AkaTable {

  private long id;
  private String value;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "AkaTable{" +
            "id=" + id +
            ", value='" + value + '\'' +
            '}';
  }
}
