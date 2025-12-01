/*package com.example.library.service;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();
    private BorrowService borrowService;
    private AdminService adminService;

    // ✅ Constructor افتراضي (عشان تستخدمه في التست بدون injection)
    public UserService() {
    }

    // ✅ Constructor مخصص (لـ sprint4 integration)
    public UserService(BorrowService borrowService, AdminService adminService) {
        this.borrowService = borrowService;
        this.adminService = adminService;
    }

    // تسجيل مستخدم جديد
    public void registerUser(String userId) {
        users.add(new User(userId));
    }

    // ✅ حذف مستخدم مع التحقق من الشروط
    public boolean unregisterUser(String userId) {
        if (adminService == null || !adminService.isLoggedIn()) {
            System.out.println("Only admin can unregister users.");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) return false;

        // ✅ تحقق من وجود كتب متأخرة أو غرامات
        boolean hasOverdue = borrowService != null &&
                borrowService.getOverdueBooksByUser(userId) != null &&
                !borrowService.getOverdueBooksByUser(userId).isEmpty();

        boolean hasFine = user.getFineBalance() > 0;

        if (hasOverdue || hasFine) {
            System.out.println("Cannot unregister: User has overdue books or unpaid fines.");
            return false;
        }

        return users.remove(user);
    }

    // البحث عن مستخدم بالـ ID
    public User getUserById(String userId) {
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // جميع المستخدمين
    public List<User> getAllUsers() {
        return users;
    }

    // إضافة مستخدم موجود مسبقًا
    public void addUser(User user) {
        users.add(user);
    }
}
*/
package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService{

    private List<User> users = new ArrayList<>();
    private BorrowService borrowService;
    private AdminService adminService;

    public UserService() {}
    public UserService(UserRepository repo){
        this.users = repo.getAllUsers();
    }

    public UserService(BorrowService borrowService, AdminService adminService){
        this.borrowService = borrowService;
        this.adminService = adminService;
    }

    public void registerUser(String userId){
        users.add(new User(userId));
    }

    public boolean unregisterUser(String userId){
        if (adminService == null || !adminService.isLoggedIn()){
            System.out.println("Only admin can unregister users.");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) return false;

        boolean hasOverdue = borrowService != null &&
                !borrowService.getOverdueBooksByUser(userId).isEmpty();

        boolean hasFine = user.getFineBalance() > 0;

        if (hasOverdue || hasFine){
            System.out.println("Cannot unregister: User has overdue books or unpaid fines.");
            return false;
        }

        return users.remove(user);
    }

    public User getUserById(String userId){
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
