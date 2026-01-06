package Views.Customer;

import DAO.KhachHangDAO;
import Models.KhachHang;

public class SessionManager {
    private static String currentUserId;
    private static String currentCustomerId;
    private static KhachHang currentCustomer;
    
    private static final KhachHangDAO khachHangDAO = new KhachHangDAO();
    
    public static void setCurrentUser(String userId) {
        currentUserId = userId;
        // Load the customer info based on user ID
        currentCustomer = khachHangDAO.selectByUserId(userId);
        if (currentCustomer != null) {
            currentCustomerId = currentCustomer.getKhachHangId();
        }
    }
    
    public static String getCurrentUserId() {
        return currentUserId;
    }
    
    public static String getCurrentCustomerId() {
        return currentCustomerId;
    }
    
    public static KhachHang getCurrentCustomer() {
        return currentCustomer;
    }
    
    public static void clearSession() {
        currentUserId = null;
        currentCustomerId = null;
        currentCustomer = null;
    }
}