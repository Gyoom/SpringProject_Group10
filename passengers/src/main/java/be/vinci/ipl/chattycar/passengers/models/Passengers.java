package be.vinci.ipl.chattycar.passengers.models;

import java.util.List;

public class Passengers {
  private List<User> pending;
  private List<User> accepted;
  private List<User> refused;

  public List<User> getPending() {
    return pending;
  }

  public List<User> getAccepted() {
    return accepted;
  }

  public List<User> getRefused() {
    return refused;
  }

  public void setPending(List<User> pending) {
    this.pending = pending;
  }

  public void setAccepted(List<User> accepted) {
    this.accepted = accepted;
  }

  public void setRefused(List<User> refused) {
    this.refused = refused;
  }
}
