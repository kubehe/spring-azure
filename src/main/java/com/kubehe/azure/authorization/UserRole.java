package com.kubehe.azure.authorization;

public enum UserRole {
  ADMIN("ADMIN"),
  REGULAR("REGULAR");

  private final String value;

  private UserRole(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
