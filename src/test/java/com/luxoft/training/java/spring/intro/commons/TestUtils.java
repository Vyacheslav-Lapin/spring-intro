package com.luxoft.training.java.spring.intro.commons;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//@UtilityClass
public final class TestUtils {

  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String TEST_RESOURCES_PATH = "./src/test/resources/";

  @Contract(value = " -> fail", pure = true)
  private TestUtils() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  @NotNull
  @Contract(pure = true)
  private static String fromPrintStream(@NotNull Consumer<PrintStream> printStreamConsumer) {
    val out = new ByteArrayOutputStream();
    @Cleanup val printStream = new PrintStream(out);
    printStreamConsumer.accept(printStream);
    return new String(out.toByteArray()).intern();
  }

  @NotNull
  @SneakyThrows
  @Contract(value = "_ -> new", pure = true)
  public static String fromSystemOutPrint(@NotNull Runnable task) {
    return fromPrintStream(printStream -> {
      PrintStream realOut = System.out;
      System.setOut(printStream);
      task.run();
      System.setOut(realOut);
    });
  }

  @NotNull
  @Contract(pure = true)
  public static String fromSystemOutPrintln(@NotNull Runnable runnable) {
    String s = fromSystemOutPrint(runnable);
    return s.endsWith(LINE_SEPARATOR) ?
             s.substring(0, s.length() - LINE_SEPARATOR.length()) :
             s;
  }

  @NotNull
  @Contract(pure = true)
  public static String toTestResourceFilePath(@NotNull String fileName) {
    return TEST_RESOURCES_PATH + fileName;
  }
}
