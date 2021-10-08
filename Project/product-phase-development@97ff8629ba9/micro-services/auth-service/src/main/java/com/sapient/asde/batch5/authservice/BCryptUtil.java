package com.sapient.asde.batch5.authservice;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
  private BCryptUtil() {
  }

  public static String hash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(4));
  }

  public static boolean verifyHash(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }

}
