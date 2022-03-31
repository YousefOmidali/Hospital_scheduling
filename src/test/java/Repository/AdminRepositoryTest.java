package Repository;

import Entity.Admin;
import org.junit.jupiter.api.AfterEach;
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
        adminRepository.delete(admin);
    }

    @Test
    void delete() {
        admin = new Admin(null, "b", "b");

        adminRepository.save(admin);
        adminRepository.delete(admin);

        assertNull(adminRepository.findById(admin.getId()));
    }

    @Test
    void update() {
        admin = new Admin(null, "b", "b");

        adminRepository.save(admin);
        admin.setUserName("bbbbbb");
        adminRepository.update(admin);

        assertEquals(adminRepository.findById(admin.getId()).getUserName(), admin.getUserName());
        adminRepository.delete(admin);
    }

    @Test
    void findById() {
        admin = new Admin(null, "b", "b");

        adminRepository.save(admin);

        assertEquals(adminRepository.findById(admin.getId()).getUserName(), admin.getUserName());
        adminRepository.delete(admin);
    }

    @Test
    void login() {
        admin = new Admin(null, "b", "b");

        adminRepository.save(admin);

        assertEquals(admin.getUserName(), adminRepository.login("b", "b").getUserName());
        adminRepository.delete(admin);
    }
}