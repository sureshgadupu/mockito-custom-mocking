package dev.fullstackcode.junit.custommocking;

import java.util.function.Consumer;

public class ChangeSetContext {
   public <T> void run(Consumer<ChangeSetContext> action) { action.accept(this); }
}