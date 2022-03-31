package Service;

import Entity.Admin;
import Repository.AdminRepository;

public class AdminService {
    private AdminRepository adminRepository = new AdminRepository();

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    public void update(Admin admin) {
        adminRepository.update(admin);
    }

    public Admin login(String username, String password) {
        return adminRepository.login(username, password);
    }
    public Admin findById(Long id) {
        return adminRepository.findById(id);
    }

}
