package POJOs;


public class UserTable {

  private long id;
  private String name;
  private long passportId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getPassportId() {
    return passportId;
  }

  public void setPassportId(long passportId) {
    this.passportId = passportId;
  }

  // it is done to print
  @Override
  public String toString() {
    return "UserTable{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", passportId=" + passportId +
            '}';
  }
}
