package lab.aop;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AopLog {

  static StringBuffer value = new StringBuffer();

  public static void append(String str) {
    value.append(str);
  }

  @NotNull
  @Contract(pure = true)
  public static String getStringValue() {
    return value.toString();
  }

  public static void clear() {
    value = new StringBuffer();
  }
}
