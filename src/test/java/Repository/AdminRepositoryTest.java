package Repository;

import Entity.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {
    private AdminRepository adminRepository;
    private Admin admin;

    @BeforeEach
    public void beforeEach() {
        adminRepository = new AdminRepository();
    }

    @Test
    void save() {
        admin = new Admin(null, "b", "b");

        adminRepository.save(admin);

        assertEquals(admin.getUserName(), adminRepository.findById(admin.getId()).getUserName());
    }

    @Test
    void login() {
    }
}