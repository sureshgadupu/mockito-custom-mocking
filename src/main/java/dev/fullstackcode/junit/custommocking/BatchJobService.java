package dev.fullstackcode.junit.custommocking;

import java.util.List;

public class BatchJobService {

    private final PrivilegedUser privilegedUser;

    public BatchJobService(PrivilegedUser privilegedUser) {
        this.privilegedUser = privilegedUser;
    }

    public void scheduledDeleteTemporaryObjectsJob() {
        List<String> tenants = getTenants();
        tenants.forEach(this::deleteTemporaryObjectsForTenant);
    }

    private void deleteTemporaryObjectsForTenant(String tenant) {
        privilegedUser.modifyUser(user -> user.setTenant(tenant)).run(this::runDeleteTemporaryObjects);
    }

    private void runDeleteTemporaryObjects(ChangeSetContext changeSetContext) {
        System.out.println("runDeleteTemporaryObjects method invoked");
    }

    private List<String> getTenants() {
        // Simulate getting a list of tenants
        return List.of("tenant1", "tenant2");
    }
}