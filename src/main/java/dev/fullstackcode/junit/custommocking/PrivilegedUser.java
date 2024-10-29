package dev.fullstackcode.junit.custommocking;

import java.util.function.Consumer;

public class PrivilegedUser {
    <T>  void run(Consumer<PrivilegedUser> action) {
        action.accept(this);
    }

    public ChangeSetContext modifyUser(Consumer<User> user) {
        System.out.println("user" +user);
        user.accept(null);
        return new ChangeSetContext();
    }
}